package komo.fraczek.toukparking.service;

import komo.fraczek.toukparking.domain.ParkingBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<ParkingBill, Long> {

    List<ParkingBill> findByDate(LocalDate localDate);

}
