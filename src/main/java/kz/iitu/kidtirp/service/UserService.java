package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.exceptions.ObjectNotFoundException;
import kz.iitu.kidtirp.exceptions.UserAlreadyExistsException;
import kz.iitu.kidtirp.model.dto.request.LoginRequest;
import kz.iitu.kidtirp.model.dto.request.SignupRequest;
import kz.iitu.kidtirp.model.dto.response.JwtResponse;
import kz.iitu.kidtirp.model.dto.response.MessageResponse;
import kz.iitu.kidtirp.model.entity.enums.ERole;
import kz.iitu.kidtirp.model.entity.Role;
import kz.iitu.kidtirp.model.entity.User;
import kz.iitu.kidtirp.repository.RoleRepository;
import kz.iitu.kidtirp.repository.UserRepository;
import kz.iitu.kidtirp.security.jwt.JwtUtils;
import kz.iitu.kidtirp.security.service.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    AuthenticationManager authenticationManager;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, RoleRepository roleRepository,
                       PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails, roles));
    }

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(false, "Ошибка: Пользователь с таким логином уже существует!"));
        }

        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));
        user.setFullName(signUpRequest.getFullName());

        setRoles(signUpRequest, user);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse(true, "Пользователь успешно зарегистрирован!"));
    }

    public User registerChild(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return userRepository.findByUsername(signUpRequest.getUsername()).orElseThrow();
        }

        User user = new User(signUpRequest.getUsername(), signUpRequest.getPassword() != null ? encoder.encode(signUpRequest.getPassword()) : encoder.encode("123456"));
        user.setFullName(signUpRequest.getFullName());
        Role role = roleRepository.findByName(ERole.CHILD).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName == null || currentPrincipalName.equals("")) {
            return ResponseEntity.ok(new MessageResponse(false, "Current User not found"));
        }

        return ResponseEntity.ok(new MessageResponse(true, currentPrincipalName));
    }

    public boolean exitsUser(String username) {
        return userRepository.existsByUsername(username);
    }


    private void setRoles(SignupRequest signUpRequest, User user) {
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.PARENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "parent" -> {
                        Role modRole = roleRepository.findByName(ERole.PARENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    case "child" -> {
                        Role modRole = roleRepository.findByName(ERole.CHILD)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                }
            });
        }
        user.setRoles(roles);
    }

    public User createUser(SignupRequest requestDto) {
        if (!userRepository.existsByUsername(requestDto.getUsername())) {
            User user = new User(requestDto.getUsername(), encoder.encode(requestDto.getPassword()));
            user.setFullName(requestDto.getFullName());

            setRoles(requestDto, user);
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(String.format("User with username %s already exists", requestDto.getUsername()), "500");
        }
    }

    public User updateUser(SignupRequest requestDto) {
        User user = userRepository.findById(requestDto.getId()).orElse(null);
        if (user != null) {
            user.setUsername(requestDto.getUsername());
            user.setPassword(encoder.encode(requestDto.getPassword()));
            user.setFullName(requestDto.getFullName());
            setRoles(requestDto, user);
            return userRepository.save(user);
        } else {
            throw new ObjectNotFoundException(String.format("User with id %s already exists", requestDto.getId()), "404");
        }
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Error: User is not found.", "404"));
        userRepository.delete(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersByRole(ERole role) {
        Role modRole = roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        return userRepository.findAllByRoles(modRole.getId());
    }

}
