package komo.fraczek.toukparking.resource;


import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.service.MeterRepository;
import komo.fraczek.toukparking.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class OperatorController {

    private static final Logger logger = LoggerFactory.getLogger(OperatorController.class);

    @Autowired
    ParkingService parkingService;


    @GetMapping(path = "/parking_bill/{numberPlate}")
    public ResponseEntity<ParkingBill> retrieveParkingBillByNumberPlate(@PathVariable String numberPlate) {
        logger.trace("Method call retrieveParkingBillByNumberPlate with PathVariable: " + numberPlate);

        return new ResponseEntity<>(parkingService.getBillByNumberPlateOrThrowEx(numberPlate), HttpStatus.OK);
    }

    @GetMapping(path = "daily_income/{dateString}")
    public ResponseEntity<BigDecimal> getDailyIncome(@PathVariable String dateString){
        logger.trace("Method call getDailyIncome with PathVariable: " + dateString);

        return new ResponseEntity<>(parkingService.calculateDailyIncome(LocalDate.parse(dateString)), HttpStatus.OK );
    }
}
