package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.exceptions.ObjectNotFoundException;
import kz.iitu.kidtirp.model.dto.request.SignupRequest;
import kz.iitu.kidtirp.model.entity.Driver;
import kz.iitu.kidtirp.model.entity.Parent;
import kz.iitu.kidtirp.model.entity.User;
import kz.iitu.kidtirp.repository.DriverRepository;
import kz.iitu.kidtirp.repository.ParentRepository;
import kz.iitu.kidtirp.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ParentService {

    UserRepository userRepository;
    ParentRepository parentRepository;
    DriverRepository driverRepository;
    UserService userService;

    public Parent registerParent(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found with id = " + userId, "404"));
        Parent parent = new Parent();
        parent.setUser(user);
        return parentRepository.save(parent);
    }


    public Parent addChild(SignupRequest request, Long parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() -> new ObjectNotFoundException("Parent not found with id = " + parentId, "404"));
        User child = userService.registerChild(request);
        parent.getChildren().add(child);
        return parentRepository.save(parent);
    }

    public Parent selectDriver(Long driverId, Long parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(() -> new ObjectNotFoundException("Parent not found with id = " + parentId, "404"));
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        parent.setDriver(driver);
        return parentRepository.save(parent);
    }

    public Parent payDrive() {
        return null;
    }

}
