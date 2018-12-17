package komo.fraczek.toukparking.resource;


import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.service.ParkingRepository;
import komo.fraczek.toukparking.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperatorController {

    private static final Logger logger = LoggerFactory.getLogger(OperatorController.class);

    //temporary
    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    ParkingService parkingService;


    @GetMapping(path = "/parking_meter/{numberPlate}")
    public ParkingMeter retrievePMeterByPlateNumber(@PathVariable String numberPlate) {
        logger.trace("Method call retrievePMeterByPlateNumber with PathVariable: " + numberPlate);

        return parkingService.getMeterByNumberPlateOrThrowEx(numberPlate);
    }

    //get with param not pathvariable?

}
