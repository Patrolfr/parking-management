package komo.fraczek.toukparking;

import komo.fraczek.toukparking.domain.ParkingCodeGenerator;
import komo.fraczek.toukparking.domain.ParkingMeter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class ToukparkingApplication {

	public static void main(String[] args) {

		SpringApplication.run(ToukparkingApplication.class, args);
//		System.out.println("Hello in toukparikng REST");
	}

}

