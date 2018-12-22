package komo.fraczek.toukparking;

import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.service.ParkingRepository;
import komo.fraczek.toukparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    ParkingService parkingService;

    @Override
    public void run(String... args) throws Exception {


        LocalDate localDate = LocalDate.of(2018, 12, 05);

        double v = parkingService.calculateDailyIncome(localDate);

        List<ParkingMeter> all = parkingRepository.findAll();

        all.forEach(System.out::println);

        System.out.println(v);

    }

}
