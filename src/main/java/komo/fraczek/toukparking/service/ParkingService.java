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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static komo.fraczek.toukparking.domain.ParkingStatus.OCCUPIED;

@Service
public class ParkingService {

    private static final Logger logger = LoggerFactory.getLogger(ParkingService.class);

    private MeterRepository meterRepository;

    private BillRepository billRepository;

    private CurrencyRateProviderService currencyService;

//    @Autowired
    public ParkingService(MeterRepository meterRepository, BillRepository billRepository, CurrencyRateProviderService currencyService) {
        this.meterRepository = meterRepository;
        this.billRepository = billRepository;
        this.currencyService = currencyService;
    }

    public String initParkingActivity(String numberPlate, DriverType driverType) {
//        check if the the parking meter can be started for given vehicle number plate
        if (billRepository.existsByNumberPlateAndParkingStatus(numberPlate, OCCUPIED)) {
            logger.warn("Given number plate vehicle already exists in database and hasn't stopped the parking meter. Throwing exception.");
            throw new PlateNumAlreadyExistsException(numberPlate);
        }

//        prepare parking bill
        ParkingBill parkingBill = billRepository.save(ParkingBill.createOccupied(driverType, numberPlate));
//        start parking meter
        ParkingMeter newParkingMeter = meterRepository.save(ParkingMeter.createStarted(parkingBill));
//        return parking code
        return newParkingMeter.getParkingCode();
    }


    public ParkingBill finishParkingActivity(String parkingCode) {

        ParkingMeter meter = meterRepository.findByParkingCode(parkingCode).orElseThrow(() -> new ParkingCodeNotFoundException(parkingCode));

        ParkingBill parkingBill = meter.getParkingBill();

//        verify if the meter can be stopped
        if (ParkingStatus.FINISHED.equals(parkingBill.getParkingStatus())) {
            logger.warn("Parking meter has been already stopped. Throwing exception.");
            throw new ParkingCodeNotFoundException(parkingCode);
        }

        meter.stop();
        parkingBill.issue(meter);

        BigDecimal fee = ChargeCalculator.calculateCharge(parkingBill.getParkingTimeInHours(), parkingBill.getDriverType());

        BigDecimal currencyRate = currencyService.getCurrencyRate("PLN");
        parkingBill.setParkingFee(fee.multiply(currencyRate).setScale(2, RoundingMode.CEILING));

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


    public BigDecimal calculateDailyIncome(LocalDate localDate) {
//        sum all of the fees from given day
        return billRepository.findByDate(localDate)
                .stream()
                .map(ParkingBill::getParkingFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
