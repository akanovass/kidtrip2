package kz.iitu.kidtirp.model.entity;

import kz.iitu.kidtirp.model.dto.request.TariffPlanRequest;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "tariff_plans")
@Getter
@Setter
@NoArgsConstructor
public class TariffPlans extends AbstractAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private int month;

    public void saveRequest(TariffPlanRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.price = request.getPrice();
        this.month = request.getMonthAmount();
    }
}
