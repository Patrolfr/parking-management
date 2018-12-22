package komo.fraczek.toukparking.service;

import komo.fraczek.toukparking.charge.ChargeCalculator;
import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.exception.ParkingCodeNotFoundException;
import komo.fraczek.toukparking.exception.PlateNumNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

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

        ParkingMeter newParkingMeter = new ParkingMeter(numberPlate, driverType);
        parkingRepository.save(newParkingMeter);

        return newParkingMeter.getParkingCode();
    }


    public ParkingBill finishParkingActivity(String parkingCode) {

        Optional<ParkingMeter> meterByCode = parkingRepository.findByParkingCode(parkingCode);
        if (!meterByCode.isPresent()) {
            throw new ParkingCodeNotFoundException(parkingCode);
        }

        ParkingMeter meter = meterByCode.get();

        meter.setStoppedAtDate(LocalDate.now());
        meter.setStoppedAtTime(LocalTime.now());
        meter.setParkingStatus(ParkingStatus.FINISHED);
        //calculate
        parkingRepository.save(meter);

//        bill
        double fee = ChargeCalculator.calculateCharge(meter.calculateParkingTimeInHours(), meter.getDriverType());

        ParkingBill parkingBill = new ParkingBill(meter.getDriverType(), meter.calculateParkingTimeInHours(), meter.getStoppedAtDate(), fee);

        return parkingBill;
    }


    public ParkingMeter getMeterByNumberPlateOrThrowEx(String numberPlate) {
        Optional<ParkingMeter> byNumberPlate = parkingRepository.findByNumberPlate(numberPlate);

        if (!byNumberPlate.isPresent()) {
            logger.warn("Parking meter for plate number '" + numberPlate + "' does not exists.");
            throw new PlateNumNotFoundException(numberPlate);
        }

        return byNumberPlate.get();
    }


    public double calculateDailyIncome(LocalDate localDate) {

        return billRepository.findByDate(localDate)
                                .stream()
                                .mapToDouble(ParkingBill::getParkingFee)
                                .sum();
    }


}
