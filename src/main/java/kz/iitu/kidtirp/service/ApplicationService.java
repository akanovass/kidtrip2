package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.model.entity.Application;
import kz.iitu.kidtirp.model.entity.enums.ApplicationStatus;
import kz.iitu.kidtirp.repository.ApplicationRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ApplicationService {
    ApplicationRepository applicationRepository;

    public Application createApplication(Long driveId) {
        Application application = new Application();
        application.setDriverId(driveId);
        application.setStatus(ApplicationStatus.NEW);
        return applicationRepository.save(application);
    }

    public Application applyApplication(Long id, boolean apply) {
        Application application = applicationRepository.findById(id).orElseThrow();
        application.setStatus(apply ? ApplicationStatus.ACCEPTED : ApplicationStatus.DECLINED);
        application.setLastModifiedBy("admin");
        application.setLastModifiedDate(LocalDateTime.now());
        return applicationRepository.save(application);
    }
}
