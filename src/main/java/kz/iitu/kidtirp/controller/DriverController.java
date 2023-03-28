package kz.iitu.kidtirp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.iitu.kidtirp.model.dto.request.DriverRequest;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import kz.iitu.kidtirp.service.ApplicationService;
import kz.iitu.kidtirp.service.TripService;
import kz.iitu.kidtirp.service.DriverService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/driver")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DriverController {

    DriverService driverService;
    ApplicationService applicationService;
    TripService tripService;

    @PostMapping("/application/create")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createApplication(@RequestParam Long driverId) {
        return ResponseEntity.ok(applicationService.createApplication(driverId));
    }

    @PostMapping("/profile/create")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createProfile(@RequestBody DriverRequest request) {
        return ResponseEntity.ok(driverService.createDriver(request));
    }

    @PostMapping("/profile/update")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateProfile(@RequestBody DriverRequest request) {
        return ResponseEntity.ok(driverService.updateDriver(request));
    }

    @PostMapping("/area/select/{driverId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> selectArea(@PathVariable Long driverId, @RequestParam String area) {
        return ResponseEntity.ok(driverService.selectArea(driverId, area));
    }

    @GetMapping("/shedule/{driverId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getSheduleDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.getAllDrives(driverId));
    }

    @GetMapping("/shedule/new/{driverId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getSheduleDriverStatusNew(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.getAllNewDrives(driverId));
    }

    @PatchMapping("/drive/apply/{driveId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> applyDrive(@PathVariable Long driveId, @RequestParam boolean accept) {
        return ResponseEntity.ok(tripService.acceptTrip(driveId, accept));
    }

    @PostMapping("/drive/start/{driveId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> startDrive(@PathVariable Long driveId, @RequestParam String status) {
        return ResponseEntity.ok(tripService.startOrWaitTrip(driveId, TripStatus.valueOf(status)));
    }

    @PostMapping("/drive/end/{driveId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> startDrive(@PathVariable Long driveId) {
        return ResponseEntity.ok(tripService.endTrip(driveId));
    }

    @PostMapping("/drive/change/status/{driveId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> changeStatus(@PathVariable Long driveId, @RequestParam String status) {
        return ResponseEntity.ok(tripService.changeStatus(driveId, TripStatus.valueOf(status)));
    }

    @GetMapping("/drive/warning")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> driveWarning() {
        return ResponseEntity.ok("вы сбились с пути");
    }

    @GetMapping("/salary/{driverId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getSalary(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.getDriver(driverId));
    }

}
