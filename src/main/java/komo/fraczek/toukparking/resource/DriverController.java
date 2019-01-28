package komo.fraczek.toukparking.resource;

import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DriverController {

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    private final ParkingService parkingService;

    //    there is no need of passing driver type, REGULAR is default
    @PostMapping(path = {"/start_parking_meter/{numberPlate}/{driverType}", "/start_parking_meter/{numberPlate}" })
    public ResponseEntity<String> startParkingMeter(@PathVariable String numberPlate,
                                    @PathVariable(required = false, name = "driverType") DriverType givenDriverType) {

        logger.trace("Method call startParkingMeter with PathVariable numberPlate: " + numberPlate);

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
