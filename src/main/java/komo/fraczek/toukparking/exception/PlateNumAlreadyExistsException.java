package komo.fraczek.toukparking.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PlateNumAlreadyExistsException extends RuntimeException {
    public PlateNumAlreadyExistsException(String arg) { super(arg);}
}
