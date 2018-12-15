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


		System.out.println("Hello in parking app;");

		ParkingMeter parkingMeter = new ParkingMeter();
		parkingMeter.setStartedAt(LocalDateTime.of(2018,12,15, 16, 56));
        parkingMeter.setStoppedAt(LocalDateTime.now());

        System.out.println(ParkingCodeGenerator.getCode());
        System.out.println(ParkingCodeGenerator.getCode());
        System.out.println(ParkingCodeGenerator.getCode());
        System.out.println(ParkingCodeGenerator.getCode());

        System.out.println(parkingMeter.getStartedAt());
        System.out.println(parkingMeter.getStoppedAt());
        System.out.println(parkingMeter.parkingTimeInHours());

	}

}

