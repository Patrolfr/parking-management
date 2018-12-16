package komo.fraczek.toukparking.service;

import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingCodeGenerator;
import komo.fraczek.toukparking.domain.ParkingMeter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

    private static final Logger logger = LoggerFactory.getLogger(ParkingService.class);


    @Autowired(required = true)
    private ParkingRepository parkingRepository;


    private String initParkingActivity(String numberPlate, DriverType driverType){
        logger.trace("Method call...");

        ParkingMeter parkingMeter = new ParkingMeter();
        parkingMeter.setParkingCode(ParkingCodeGenerator.getCode());

//        ParkingMeter.
        //create ParkingMeterFor specyfied

        return "XCH997 - przykładowy parkingCode";
    }






}
