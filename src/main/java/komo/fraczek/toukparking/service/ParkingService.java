package komo.fraczek.toukparking.service;

import komo.fraczek.toukparking.charge.ChargeCalculator;
import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.exception.ParkingCodeNotFoundException;
import komo.fraczek.toukparking.exception.PlateNumAlreadyExistsException;
import komo.fraczek.toukparking.exception.PlateNumNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static komo.fraczek.toukparking.domain.ParkingStatus.OCCUPIED;

@Service
public class ParkingService {

    private static final Logger logger = LoggerFactory.getLogger(ParkingService.class);

    private ParkingRepository parkingRepository;

    private BillRepository billRepository;

    @Autowired
    public void setParkingRepository(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Autowired
    public void setBillRepository(BillRepository billRepository){
        this.billRepository = billRepository;
    }


    public String initParkingActivity(String numberPlate, DriverType driverType) {

//        check if vehicle number plate makes sens
        if(billRepository.findByNumberPlateAndParkingStatus(numberPlate, OCCUPIED).isPresent()){
           logger.error("Given number plate vehicle already exists in database and hasn't stopped meter.");
            throw new PlateNumAlreadyExistsException(numberPlate);
        }

        ParkingBill parkingBill = new ParkingBill();
        parkingBill.setDriverType(driverType);
        parkingBill.setNumberPlate(numberPlate);
        parkingBill.setParkingStatus(OCCUPIED);
        billRepository.save(parkingBill);

        ParkingMeter newParkingMeter = new ParkingMeter(numberPlate, driverType);
        newParkingMeter.setParkingBill(parkingBill);
        parkingRepository.save(newParkingMeter);

        return newParkingMeter.getParkingCode();
    }


    public ParkingBill finishParkingActivity(String parkingCode) {
//        verify if is present
        Optional<ParkingMeter> meterByCode = parkingRepository.findByParkingCode(parkingCode);
        if (!meterByCode.isPresent()) {
            logger.warn("Parking meter is not present. Throwing exception.");
            throw new ParkingCodeNotFoundException(parkingCode);
        }

        ParkingMeter meter = meterByCode.get();
        ParkingBill parkingBill = meter.getParkingBill();

//        verify if can be finished
        if (ParkingStatus.FINISHED.equals(parkingBill.getParkingStatus())){
            logger.warn("Parking meter has been already stopped. Throwing exception.");
            throw new ParkingCodeNotFoundException(parkingCode);
        }

//        finish
        meter.setStoppedAt(LocalDateTime.now());
//        issue the bill
        parkingBill.setParkingTimeInHours(meter.calculateParkingTimeInHours());
        parkingBill.setDate(meter.getStoppedAt().toLocalDate());
        parkingBill.setParkingStatus(ParkingStatus.FINISHED);
//        calculate fee
        parkingBill.setParkingFee(ChargeCalculator.calculateCharge(parkingBill.getParkingTimeInHours(), parkingBill.getDriverType()));

        parkingRepository.save(meter);
        return billRepository.save(parkingBill);
}


    public ParkingBill getBillByNumberPlateOrThrowEx(String numberPlate) {
//        get the bill
        Optional<ParkingBill> byNumberPlate = billRepository.findByNumberPlateAndParkingStatus(numberPlate, OCCUPIED);
//        verify if is present
        if (!byNumberPlate.isPresent()) {
            logger.warn("Parking bill for plate number '" + numberPlate + "' does not exists.");
            throw new PlateNumNotFoundException(numberPlate);
        }

        return byNumberPlate.get();
    }


    public double calculateDailyIncome(LocalDate localDate) {
//        sum all the fees from specified day
        return billRepository.findByDate(localDate)
                                .stream()
                                .mapToDouble(ParkingBill::getParkingFee)
                                .sum();
    }


}
