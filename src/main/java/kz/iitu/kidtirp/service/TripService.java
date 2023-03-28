package kz.iitu.kidtirp.service;

import kz.iitu.kidtirp.model.dto.request.TripFilter;
import kz.iitu.kidtirp.model.dto.request.TripRequest;
import kz.iitu.kidtirp.model.entity.Trip;
import kz.iitu.kidtirp.model.entity.Driver;
import kz.iitu.kidtirp.model.entity.Parent;
import kz.iitu.kidtirp.model.entity.User;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import kz.iitu.kidtirp.repository.TripRepository;
import kz.iitu.kidtirp.repository.DriverRepository;
import kz.iitu.kidtirp.repository.ParentRepository;
import kz.iitu.kidtirp.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TripService {

    TripRepository tripRepository;
    DriverRepository driverRepository;
    UserRepository userRepository;
    ParentRepository parentRepository;

    public Trip createTrip(TripRequest request) {
        Driver driver = driverRepository.findById(request.getDriverId()).orElseThrow();
        User user = userRepository.findById(request.getChildId()).orElseThrow();
        Parent parent = parentRepository.findById(request.getParentId()).orElseThrow();
        Trip trip = new Trip(request.getFromPlace(), request.getToPlace(), null, null, TripStatus.NEW, driver, user, parent, false);
        return tripRepository.save(trip);
    }

    public Trip updateTrip(TripRequest request) {
        Trip trip = tripRepository.findById(request.getId()).orElseThrow();
        trip.setLastModifiedDate(LocalDateTime.now());
        Driver driver = driverRepository.findById(request.getDriverId()).orElseThrow();
        User user = userRepository.findById(request.getChildId()).orElseThrow();
        Parent parent = parentRepository.findById(request.getParentId()).orElseThrow();
        trip = new Trip(request.getFromPlace(), request.getToPlace(), request.getStartTime(), request.getEndTime(), request.getStatus(), driver, user, parent, true);
        return tripRepository.saveAndFlush(trip);
    }

    public Trip getTrip(Long id) {
        return tripRepository.findById(id).orElseThrow();
    }

    public void deleteTrip(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        tripRepository.delete(trip);
    }


    public Trip acceptTrip(Long TripId, Boolean accept) {
        Trip trip = tripRepository.findById(TripId).orElseThrow();
        trip.setLastModifiedDate(LocalDateTime.now());
        trip.setStatus(accept ? TripStatus.ACCEPTED : TripStatus.DECLINED);
        return tripRepository.save(trip);
    }

    public Trip startOrWaitTrip(Long TripId, TripStatus status) {
        Trip trip = tripRepository.findById(TripId).orElseThrow();
        trip.setLastModifiedDate(LocalDateTime.now());
        trip.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        trip.setStatus(status);
        return tripRepository.save(trip);
    }

    public Trip changeStatus(Long TripId, TripStatus status) {
        Trip trip = tripRepository.findById(TripId).orElseThrow();
        trip.setLastModifiedDate(LocalDateTime.now());
        trip.setStatus(status);
        return tripRepository.save(trip);
    }

    public Trip endTrip(Long TripId) {
        Trip trip = tripRepository.findById(TripId).orElseThrow();
        trip.setLastModifiedDate(LocalDateTime.now());
        trip.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        trip.setStatus(TripStatus.END);
        return tripRepository.save(trip);
    }

    public Trip orderOneTimeDrive(TripRequest request) {
        Driver driver = driverRepository.findById(request.getDriverId()).orElseThrow();
        User user = userRepository.findById(request.getChildId()).orElseThrow();
        Parent parent = parentRepository.findById(request.getParentId()).orElseThrow();
        Trip trip = new Trip(request.getFromPlace(), request.getToPlace(), null, null, TripStatus.NEW, driver, user, parent,false);
        trip.setOneTimeDrive(true);
        return tripRepository.save(trip);
    }

    public List<Trip> getAllTripParent(Long parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        return tripRepository.findAllByParent(parent);
    }

    public List<Trip> getAllTripChild(Long childId) {
        User child = userRepository.findById(childId).orElseThrow();
        return tripRepository.findAllByChild(child);
    }

    public List<Trip> getAllDrive() {
        return tripRepository.findAll();
    }

    public Page<Trip> getDrivesPageable(PageRequest pageRequest, TripFilter filter) {
        return null;
    }

}
