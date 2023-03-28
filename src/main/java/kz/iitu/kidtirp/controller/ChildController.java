package kz.iitu.kidtirp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import kz.iitu.kidtirp.service.TripService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/child")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ChildController {

    TripService tripService;

    @GetMapping("/schedule/{childId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> schedule(@PathVariable Long childId) {
        return ResponseEntity.ok(tripService.getAllTripChild(childId));
    }
}
