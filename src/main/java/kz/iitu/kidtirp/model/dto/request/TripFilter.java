package kz.iitu.kidtirp.model.dto.request;

import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripFilter {
    Long driverId;
    TripStatus status;

}
