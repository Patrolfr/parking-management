package komo.fraczek.toukparking;

import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.service.BillRepository;
import komo.fraczek.toukparking.service.MeterRepository;
import komo.fraczek.toukparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    MeterRepository meterRepository;

    @Autowired
    ParkingService parkingService;

    @Autowired
    BillRepository billRepository;

    @Override
    public void run(String... args) throws Exception {


        LocalDate localDate = LocalDate.of(2018, 12, 15);

        double v = parkingService.calculateDailyIncome(localDate);

        List<ParkingMeter> all = meterRepository.findAll();

        all.forEach(System.out::println);

        System.out.println(v);

        System.out.println(billRepository.findByNumberPlateAndParkingStatus("WEQ2123", ParkingStatus.FINISHED).get().toString());

    }

}
