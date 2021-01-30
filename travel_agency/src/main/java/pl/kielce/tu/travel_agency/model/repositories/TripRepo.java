package pl.kielce.tu.travel_agency.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.travel_agency.model.entities.Trip;

import java.util.Date;
import java.util.List;

@Repository
public interface TripRepo extends JpaRepository<Trip, Long> {

    @Query("SELECT t FROM Trip t WHERE t.name LIKE %:name% OR t.duration = :duration OR t.startingDate = :date")
    List<Trip> findByCriteria(Date date, int duration, String name);
}
