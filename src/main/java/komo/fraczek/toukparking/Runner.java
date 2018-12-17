package komo.fraczek.toukparking;

import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.service.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    ParkingRepository parkingRepository;

    @Override
    public void run(String... args) throws Exception {


        Optional<ParkingMeter> meterTaken = parkingRepository.findByNumberPlate("WDZ2123");
        System.out.println("meterTaken.isPresent: " + meterTaken.isPresent());


        if (meterTaken.isPresent() ) System.out.println(meterTaken.get().toString());
    }

}
