package komo.fraczek.toukparking.resource;


import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.charge.ChargeCalculator;
import komo.fraczek.toukparking.charge.DummyCurrencyRateProviderService;
import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    ParkingService parkingService;

    @Autowired
    DummyCurrencyRateProviderService currencyService;


    //there is no need of passing driver type, REGULAR is default
    @PostMapping(path = {"/startMeter/{numberPlate}/{driverType}", "/startMeter/{numberPlate}" })
    public String startParkingMeter(@PathVariable String numberPlate, @PathVariable(required = false, name = "driverType") DriverType
                                    givenDriverType) {

        logger.info("numberPlate: " + numberPlate +"; givenDriverType: " + givenDriverType);

        return givenDriverType == null ? parkingService.initParkingActivity(numberPlate, DriverType.REGULAR) : parkingService.initParkingActivity(numberPlate, givenDriverType);
    }

    @PostMapping(path = "/stopMeter/{parkingCode}")
    public ParkingBill stopParkingMeter(@PathVariable String parkingCode){

        ParkingBill parkingBill = parkingService.finishParkingActivity(parkingCode);

        double feeInPLN = ChargeCalculator.calculateCharge(parkingBill.getParkingTimeInHours(), parkingBill.getDriverType());

        // posibillity of implementing real currency rate service... with BigDecimal(?)
        parkingBill.setParkingFee(feeInPLN * currencyService.getCurrencyRate("PLN"));

        return parkingBill;
    }

    //start parking meter using request params (???),


}
