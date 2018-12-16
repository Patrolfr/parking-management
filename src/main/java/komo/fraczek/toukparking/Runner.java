package komo.fraczek.toukparking;

import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.service.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    ParkingRepository parkingRepository;

    @Override
    public void run(String... args) throws Exception {

        ParkingMeter parkingMeter = new ParkingMeter();
        parkingMeter.setStartedAt(LocalDateTime.of(2018,12,15, 16, 56));
        parkingMeter.setStoppedAt(LocalDateTime.now());
        parkingMeter.setParkingCode("KOD-123");
        parkingMeter.setNumberPlate("ahaso12093123");
        parkingMeter.setDriverType(DriverType.REGULAR);
        parkingMeter.setParkingStatus(ParkingStatus.FINISHED);
//        parkingRepository.save(parkingMeter);


    }

}
