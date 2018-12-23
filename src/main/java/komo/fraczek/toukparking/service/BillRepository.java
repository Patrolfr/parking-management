package komo.fraczek.toukparking.service;

import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<ParkingBill, Long> {


    Optional<ParkingBill> findByNumberPlate(String s);

    Optional<ParkingBill> findByNumberPlateAndParkingStatus(String numberplate, ParkingStatus parkingStatus);

    List<ParkingBill> findByDate(LocalDate localDate);


}
