package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.model.dto.request.DriverRequest;
import kz.iitu.kidtirp.model.entity.Trip;
import kz.iitu.kidtirp.model.entity.Driver;
import kz.iitu.kidtirp.model.entity.User;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import kz.iitu.kidtirp.repository.TripRepository;
import kz.iitu.kidtirp.repository.DriverRepository;
import kz.iitu.kidtirp.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class DriverService {

    DriverRepository driverRepository;
    UserRepository userRepository;
    TripRepository tripRepository;

    public Driver createDriver(DriverRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Driver driver = new Driver(user, request.getBirthDate(), request.getArea(), request.getPhotoUrl(), request.getExperience(), 0.0, 0.0);
        return driverRepository.save(driver);
    }

    public Driver updateDriver(DriverRequest request) {
        Driver driver = driverRepository.findById(request.getId()).orElseThrow();
        driver.setArea(request.getArea());
        driver.setBirthDate(request.getBirthDate());
        driver.setSalary(request.getSalary());
        driver.setPhotoUrl(request.getPhotoUrl());
        driver.setSalary(request.getSalary());
        driver.setExperience(request.getExperience());
        return driverRepository.save(driver);
    }

    public Driver selectArea(Long driverId, String area) {
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        driver.setArea(area);
        return driverRepository.save(driver);
    }

    public Driver getDriver(Long id) {
        return driverRepository.findById(id).orElseThrow();
    }

    public List<Trip> getAllDrives(Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        return tripRepository.findAllByDriver(driver);
    }

    public List<Trip> getAllNewDrives(Long driverId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow();
        return tripRepository.findAllByDriverAndStatus(driver, TripStatus.NEW);
    }

}
