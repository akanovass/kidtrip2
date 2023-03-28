package kz.iitu.kidtirp.model.dto.request;

import lombok.Data;

@Data
public class TariffPlanRequest {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private int monthAmount;
}
