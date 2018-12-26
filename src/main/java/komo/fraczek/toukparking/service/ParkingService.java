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
import java.util.Optional;

import static komo.fraczek.toukparking.domain.ParkingStatus.OCCUPIED;

@Service
public class ParkingService {

    private static final Logger logger = LoggerFactory.getLogger(ParkingService.class);

    private MeterRepository meterRepository;

    private BillRepository billRepository;

    @Autowired
    public void setMeterRepository(MeterRepository meterRepository) {
        this.meterRepository = meterRepository;
    }

    @Autowired
    public void setBillRepository(BillRepository billRepository){
        this.billRepository = billRepository;
    }


    public String initParkingActivity(String numberPlate, DriverType driverType) {
//        check if the the parking meter can be started for given vehicle number plate
        if(billRepository.findByNumberPlateAndParkingStatus(numberPlate, OCCUPIED).isPresent()){
           logger.error("Given number plate vehicle already exists in database and hasn't stopped the parking meter.");
            throw new PlateNumAlreadyExistsException(numberPlate);
        }

//        prepare parking bill
        ParkingBill parkingBill = billRepository.save(new ParkingBill(driverType, numberPlate));

//        start parking meter
        ParkingMeter newParkingMeter = meterRepository.save(new ParkingMeter(parkingBill));;

//        return parking code
        return newParkingMeter.getParkingCode();
    }


    public ParkingBill finishParkingActivity(String parkingCode) {
//        get the parking meter
        Optional<ParkingMeter> meterByCode = meterRepository.findByParkingCode(parkingCode);
//        verify if the meter is present
        if (!meterByCode.isPresent()) {
            logger.warn("Parking meter is not present. Throwing exception.");
            throw new ParkingCodeNotFoundException(parkingCode);
        }

        ParkingMeter meter = meterByCode.get();
        ParkingBill parkingBill = meter.getParkingBill();

//        verify if the meter can be stopped
        if (ParkingStatus.FINISHED.equals(parkingBill.getParkingStatus())){
            logger.warn("Parking meter has been already stopped. Throwing exception.");
            throw new ParkingCodeNotFoundException(parkingCode);
        }

//        stop
        meter.setStoppedAt(LocalDateTime.now());
//        issue the bill
        parkingBill.setParkingTimeInHours(meter.calculateParkingTimeInHours());
        parkingBill.setDate(meter.getStoppedAt().toLocalDate());
        parkingBill.setParkingStatus(ParkingStatus.FINISHED);
//        calculate the fee
        parkingBill.setParkingFee(ChargeCalculator.calculateCharge(parkingBill.getParkingTimeInHours(), parkingBill.getDriverType()));

        meterRepository.save(meter);
        return billRepository.save(parkingBill);
}


    public ParkingBill getBillByNumberPlateOrThrowEx(String numberPlate) {
//        get the bill
        Optional<ParkingBill> byNumberPlate = billRepository.findByNumberPlateAndParkingStatus(numberPlate, OCCUPIED);
//        verify if the bill is present
        if (!byNumberPlate.isPresent()) {
            logger.warn("Parking bill for plate number '" + numberPlate + "' does not exists. Throwing exception.");
            throw new PlateNumNotFoundException(numberPlate);
        }

        return byNumberPlate.get();
    }


    public double calculateDailyIncome(LocalDate localDate) {
//        sum all of the fees from given day
        return billRepository.findByDate(localDate)
                                .stream()
                                .mapToDouble(ParkingBill::getParkingFee)
                                .sum();
    }


}
