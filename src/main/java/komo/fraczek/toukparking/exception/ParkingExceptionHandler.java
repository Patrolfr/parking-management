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

@RestController
@ControllerAdvice
public class ParkingExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ParkingExceptionHandler.class);

    @ExceptionHandler(PlateNumNotFoundException.class)
    public final ResponseEntity<Object> handleParkingMeterNotFoundException(PlateNumNotFoundException ex, WebRequest request){
        logger.trace("Handling PlateNumNotFoundException.");

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), "Number plate " + ex.getMessage() + " not found in database.", "Whoops.. this vehicle may have been parked by the cheater ;<", request.getDescription(false));

        return new ResponseEntity( response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParkingCodeNotFoundException.class)
    public final ResponseEntity<Object> handleParkingCodeNotFoundException(ParkingCodeNotFoundException ex, WebRequest request){
        logger.trace("Handling ParkingCodeNotFoundException.");

        ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), "Parking code " + ex.getMessage() + " is invalid. Please try again.", "Whoops.. forgot your parking code? Can't help you ;<", request.getDescription(false));

        return new ResponseEntity( response, HttpStatus.NOT_FOUND);
    }



}
