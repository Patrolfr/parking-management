package komo.fraczek.toukparking.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@RestController
@ControllerAdvice
public class ParkingExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ParkingExceptionHandler.class);

    @ExceptionHandler(PlateNumNotFoundException.class)
    public final ResponseEntity<Object> handleParkingMeterNotFoundException(PlateNumNotFoundException ex, WebRequest request){
        logger.trace("Handling PlateNumNotFoundException.");

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                "Whoops.. this vehicle may have been parked by the cheater ;<",
                "Number plate " + ex.getMessage() + " not found in database.",
                request.getDescription(false));

        return new ResponseEntity( response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlateNumAlreadyExistsException.class)
    public final ResponseEntity<Object> handlePlateNumAlreadyExistsException(PlateNumAlreadyExistsException ex, WebRequest request){
        logger.trace("Handling PlateNumAlreadyExistsException.");

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                "Whoops.. this vehicle driver hasn't stopped parking meter yet.",
                "Vehicle with number plate " + ex.getMessage() + " already exists in database.",
                request.getDescription(false));

        return new ResponseEntity( response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ParkingCodeNotFoundException.class)
    public final ResponseEntity<Object> handleParkingCodeNotFoundException(ParkingCodeNotFoundException ex, WebRequest request){
        logger.trace("Handling ParkingCodeNotFoundException.");

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                "Whoops.. forgot your parking code? Can't help you ;<",
                "Parking code " + ex.getMessage() + " is invalid. Please try again.",
                request.getDescription(false));

        return new ResponseEntity( response, HttpStatus.NOT_FOUND);
    }



}
