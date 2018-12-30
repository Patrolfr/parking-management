package komo.fraczek.toukparking;

import komo.fraczek.toukparking.service.BillRepository;
import komo.fraczek.toukparking.service.MeterRepository;
import komo.fraczek.toukparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


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

//        List<ParkingMeter> all = meterRepository.findAll();
//        all.forEach(System.out::println);

    }

}
