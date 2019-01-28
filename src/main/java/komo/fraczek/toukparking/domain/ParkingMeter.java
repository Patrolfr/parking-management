package komo.fraczek.toukparking.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParkingMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //    Holds parking ticket code generated for driver
    private String parkingCode;

    private LocalDateTime startedAt;

    private LocalDateTime stoppedAt;

    @OneToOne
    private ParkingBill parkingBill;

    //    +1 becouse first hour also counts,
    int calculateParkingTimeInHours() {
        return (int) Duration.between(startedAt, stoppedAt).toHours() + 1;
    }

    public void stop(){
        this.stoppedAt = LocalDateTime.now();
    }

    public static ParkingMeter createStarted(ParkingBill parkingBill){
        ParkingMeter parkingMeter = new ParkingMeter();
        parkingMeter.parkingBill = parkingBill;
        parkingMeter.startedAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        parkingMeter.parkingCode = ParkingCodeGenerator.getCode();
        return parkingMeter;
    }
}
