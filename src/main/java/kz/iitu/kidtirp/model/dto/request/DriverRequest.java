package kz.iitu.kidtirp.model.dto.request;

import kz.iitu.kidtirp.model.entity.Trip;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DriverRequest {

    private Long id;
    private Long userId;
    private Date birthDate;
    private String area;
    private String photoUrl;
    private Integer experience;
    private Double salary;
    private List<Trip> trips;
}
