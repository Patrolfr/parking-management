package komo.fraczek.toukparking.service;

import komo.fraczek.toukparking.domain.ParkingMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingMeter, Long> {


}
