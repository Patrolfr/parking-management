package komo.fraczek.toukparking.domain;

import java.time.LocalDateTime;

public class ParkingMeter {

    private ParkingStatus parkingStatus;

    private String numberPlate;

    private String parkingCode;

    private LocalDateTime startedAt;

    private LocalDateTime stoppedAt;


    //or method count parking time to payment service.
    public int parkingTimeInHours(){

//        stoppedAt - startedAt;
        return 1;
    }

}
