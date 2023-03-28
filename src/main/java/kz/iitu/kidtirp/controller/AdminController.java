package kz.iitu.kidtirp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.iitu.kidtirp.model.dto.request.TripFilter;
import kz.iitu.kidtirp.model.dto.request.TripRequest;
import kz.iitu.kidtirp.model.dto.request.SignupRequest;
import kz.iitu.kidtirp.model.dto.request.TariffPlanRequest;
import kz.iitu.kidtirp.model.dto.response.MessageResponse;
import kz.iitu.kidtirp.model.entity.enums.ERole;
import kz.iitu.kidtirp.service.ApplicationService;
import kz.iitu.kidtirp.service.TripService;
import kz.iitu.kidtirp.service.TariffPlansService;
import kz.iitu.kidtirp.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminController {

    UserService userService;
    TripService tripService;
    ApplicationService applicationService;
    TariffPlansService tariffPlansService;

    @PutMapping("/user/create")
    @Operation(summary = "createUser", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createUser(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/user/update")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateUser(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @DeleteMapping("/user/delete/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse(true, "User successfully deleted!"));
    }

    @GetMapping("/user/all")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/user/role/{role}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getAllUsersByRole(@PathVariable ERole role) {
        return ResponseEntity.ok(userService.getAllUsersByRole(role));
    }

    @PutMapping("/trip/create")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createTrip(@RequestBody TripRequest request) {
        return ResponseEntity.ok(tripService.createTrip(request));
    }

    @GetMapping("/trip/update")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateTrip(@RequestBody TripRequest request) {
        return ResponseEntity.ok(tripService.updateTrip(request));
    }

    @DeleteMapping("/trip/delete/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.ok(new MessageResponse(true, "Drive successfully deleted!"));
    }

    @PostMapping("/app/apply/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> applyApplication(@PathVariable Long id, @RequestParam Boolean apply) {
        return ResponseEntity.ok(applicationService.applyApplication(id, apply));
    }

    @PostMapping("/trip/all")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> driveTable() {
        return ResponseEntity.ok(tripService.getAllDrive());
    }

    @PostMapping("/trip/filter")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> driveTableFilter(@RequestBody TripFilter filter, @RequestParam int page, @RequestParam int size) {
        PageRequest request = PageRequest.of(page, size);
        return ResponseEntity.ok(tripService.getDrivesPageable(request, filter));
    }

    @PostMapping("/driver/salary")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> paySalaryDriver(@RequestParam Long driverId, @RequestParam Double amount) {
        return ResponseEntity.ok("");
    }

    @PutMapping("/tariff/create")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createTariff(@RequestBody TariffPlanRequest request) {
        return ResponseEntity.ok(tariffPlansService.createTariffPlan(request));
    }

    @PostMapping("/tariff/update")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateTariffPlan(@RequestBody TariffPlanRequest request) {
        return ResponseEntity.ok(tariffPlansService.updateTariffPlan(request));
    }
}
