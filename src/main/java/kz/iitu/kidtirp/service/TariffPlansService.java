package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.model.dto.request.TariffPlanRequest;
import kz.iitu.kidtirp.model.entity.Parent;
import kz.iitu.kidtirp.model.entity.TariffPlans;
import kz.iitu.kidtirp.repository.ParentRepository;
import kz.iitu.kidtirp.repository.TariffPlansRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TariffPlansService {

    TariffPlansRepository tariffPlansRepository;
    ParentRepository parentRepository;
    public TariffPlans createTariffPlan(TariffPlanRequest request) {
        TariffPlans tariffPlans = new TariffPlans();
        tariffPlans.saveRequest(request);
        return tariffPlansRepository.save(tariffPlans);
    }

    public TariffPlans updateTariffPlan(TariffPlanRequest request) {
        TariffPlans tariffPlans = tariffPlansRepository.findById(request.getId()).orElseThrow();
        tariffPlans.saveRequest(request);
        return tariffPlansRepository.save(tariffPlans);
    }

    public Parent selectTariffPlan(Long parentId, Long tariffId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        TariffPlans tariffPlans = tariffPlansRepository.findById(tariffId).orElseThrow();
        parent.setTariff(tariffPlans);
        return parentRepository.save(parent);
    }

}
