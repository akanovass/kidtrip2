package kz.iitu.kidtirp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.iitu.kidtirp.model.dto.request.TripRequest;
import kz.iitu.kidtirp.model.dto.request.SignupRequest;
import kz.iitu.kidtirp.service.TripService;
import kz.iitu.kidtirp.service.ParentService;
import kz.iitu.kidtirp.service.TariffPlansService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/parent")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ParentController {

    ParentService parentService;
    TariffPlansService tariffPlansService;
    TripService tripService;

    @PostMapping("/register/{userId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> registerParent(@PathVariable Long userId) {
        return ResponseEntity.ok(parentService.registerParent(userId));
    }

    @PutMapping("/add/child/{parentId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> addChildren(@PathVariable Long parentId,
                                         @RequestBody SignupRequest request) {
        return ResponseEntity.ok(parentService.addChild(request, parentId));
    }

    @PutMapping("/tariff/{parentId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> selectTariff(@PathVariable Long parentId,
                                          @RequestParam Long tariffId) {
        return ResponseEntity.ok(tariffPlansService.selectTariffPlan(parentId, tariffId));
    }

    @PostMapping("/drive/create")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createDrive(@RequestBody TripRequest request) {
        return ResponseEntity.ok(tripService.createTrip(request));
    }

    @GetMapping("/drive/list/{parentId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> getDriveList(@PathVariable Long parentId) {
        return ResponseEntity.ok(tripService.getAllTripParent(parentId));
    }

    @PostMapping("drive/onetime/order")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> createOneTimeDrive(@RequestBody TripRequest request) {
        return ResponseEntity.ok(tripService.orderOneTimeDrive(request));
    }

    @PostMapping("/select/driver/{parentId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> selectDriver(@PathVariable Long parentId,
                                          @RequestParam Long driverId) {
        return ResponseEntity.ok(parentService.selectDriver(driverId, parentId));
    }
}
