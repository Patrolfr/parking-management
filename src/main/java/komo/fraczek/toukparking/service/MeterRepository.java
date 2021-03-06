package komo.fraczek.toukparking.service;

import komo.fraczek.toukparking.domain.ParkingMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.security.cert.PKIXRevocationChecker;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeterRepository extends JpaRepository<ParkingMeter, Long> {

    Optional<ParkingMeter> findByParkingCode(String s);

}
