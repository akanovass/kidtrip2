package kz.iitu.kidtirp.model.dto.request;

import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class TripRequest {

    private Long id;
    private String fromPlace;
    private String toPlace;
    private Timestamp startTime;
    private Timestamp endTime;
    private TripStatus status;
    private Long driverId;
    private Long parentId;
    private Long childId;
    private boolean rescheduled = false;
}
