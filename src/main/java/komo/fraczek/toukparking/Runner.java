package komo.fraczek.toukparking;

import komo.fraczek.toukparking.charge.ChargeCalculator;
import komo.fraczek.toukparking.charge.Math;
import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.service.BillRepository;
import komo.fraczek.toukparking.service.MeterRepository;
import komo.fraczek.toukparking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.apache.commons.lang3.time.DateUtils.round;


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

        BigDecimal v = parkingService.calculateDailyIncome(localDate);

        List<ParkingMeter> all = meterRepository.findAll();

        all.forEach(System.out::println);

        System.out.println(v);

        System.out.println(billRepository.findByNumberPlateAndParkingStatus("WEQ2123", ParkingStatus.FINISHED).get().toString());

//        System.out.println("********");
//        IntStream.range(2,7).mapToDouble(val -> Math.countGeomSeriesTerm.apply((double) val, 1.2)).forEach(System.out::println);
//        System.out.println("********");
//        IntStream.range(1,7).mapToDouble(val -> Math.calculateGeometricSeriesSum(val, 1.2)).forEach(System.out::println);
//        System.out.println("********");

    }

}
