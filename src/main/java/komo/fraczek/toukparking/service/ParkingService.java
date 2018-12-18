package komo.fraczek.toukparking.service;

import komo.fraczek.toukparking.charge.ParkingBill;
import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.exception.ParkingCodeNotFoundException;
import komo.fraczek.toukparking.exception.PlateNumNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ParkingService {

    private static final Logger logger = LoggerFactory.getLogger(ParkingService.class);


    @Autowired(required = true)
    private ParkingRepository parkingRepository;


    public String initParkingActivity(String numberPlate, DriverType driverType){

        ParkingMeter newParkingMeter = new ParkingMeter(numberPlate, driverType);
        parkingRepository.save(newParkingMeter);

        return newParkingMeter.getParkingCode();
    }

    public ParkingBill finishParkingActivity(String parkingCode){

        Optional<ParkingMeter> meterByCode = parkingRepository.findByParkingCode(parkingCode);
        if( !meterByCode.isPresent()) {
            throw new ParkingCodeNotFoundException(parkingCode);
        }

        ParkingMeter meter = meterByCode.get();

        meter.setStoppedAt(LocalDateTime.now());
        meter.setParkingStatus(ParkingStatus.FINISHED);
        parkingRepository.save(meter);

        return new ParkingBill(meter.parkingTimeInHours(), meter.getNumberPlate(), meter.getDriverType());
    }

    public ParkingMeter getMeterByNumberPlateOrThrowEx(String numberPlate){
        Optional<ParkingMeter> byNumberPlate = parkingRepository.findByNumberPlate(numberPlate);

        if( !byNumberPlate.isPresent()) {
            logger.warn("Parking meter for plate number '" + numberPlate + "' does not exists.");
            throw new PlateNumNotFoundException(numberPlate);
        }

        return byNumberPlate.get();
    }



//    /**
//     *  Method should be used only by unit tests.
//     *  @param parkingRepository
//     */
    public void setParkingRepository(ParkingRepository parkingRepository){
        this.parkingRepository = parkingRepository;
    }

}
