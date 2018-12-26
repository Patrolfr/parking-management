package komo.fraczek.toukparking.resource;

import komo.fraczek.toukparking.charge.CurrencyRateProviderService;
import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.charge.ChargeCalculator;
import komo.fraczek.toukparking.charge.DummyCurrencyRateProviderService;
import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    ParkingService parkingService;

    CurrencyRateProviderService currencyService;

    @Autowired
    public void setParkingService(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @Autowired
    public void setCurrencyService(CurrencyRateProviderService currencyService) {
        this.currencyService = currencyService;
    }

    //    there is no need of passing driver type, REGULAR is default
    @PostMapping(path = {"/start_parking_meter/{numberPlate}/{driverType}", "/start_parking_meter/{numberPlate}" })
    public ResponseEntity<String> startParkingMeter(@PathVariable String numberPlate,
                                    @PathVariable(required = false, name = "driverType") DriverType givenDriverType) {

        logger.info("numberPlate: " + numberPlate +"; givenDriverType: " + givenDriverType);

        String parkingCode = givenDriverType == null ?
                parkingService.initParkingActivity(numberPlate, DriverType.REGULAR) :
                parkingService.initParkingActivity(numberPlate, givenDriverType);
        return new ResponseEntity<>(parkingCode, HttpStatus.CREATED);
    }

    @PostMapping(path = "/stop_parking_meter/{parkingCode}")
    public ResponseEntity<ParkingBill>  stopParkingMeter(@PathVariable String parkingCode){

        return new ResponseEntity<>(parkingService.finishParkingActivity(parkingCode), HttpStatus.ACCEPTED);
    }

}
