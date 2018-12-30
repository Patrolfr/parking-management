package komo.fraczek.toukparking.domain;


import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Entity
public class ParkingMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //    Holds parking ticket code generated for driver
    private String parkingCode;


    //    Beggining of the activity
    private LocalDateTime startedAt;

    //   Ending of the activity
    private LocalDateTime stoppedAt;


    @OneToOne
    private ParkingBill parkingBill;


    //    +1 becouse first hour also counts,
//    maybe substract 10 mins to give driver some extra time(?)
    public int calculateParkingTimeInHours() {
        return (int) Duration.between(startedAt, stoppedAt).toHours() + 1;
    }

    public ParkingMeter(ParkingBill parkingBill) {
        this.parkingBill = parkingBill;
        this.startedAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.parkingCode = ParkingCodeGenerator.getCode();
    }

    //    counstructors, getters/setter
    public ParkingMeter() {
    }

    public ParkingMeter(String parkingCode, LocalDateTime startedAt, LocalDateTime stoppedAt, ParkingBill parkingBill) {
        this.parkingCode = parkingCode;
        this.startedAt = startedAt;
        this.stoppedAt = stoppedAt;
        this.parkingBill = parkingBill;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getParkingCode() {
        return parkingCode;
    }

    public void setParkingCode(String parkingCode) {
        this.parkingCode = parkingCode;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getStoppedAt() {
        return stoppedAt;
    }

    public void setStoppedAt(LocalDateTime stoppedAt) {
        this.stoppedAt = stoppedAt;
    }

    public ParkingBill getParkingBill() {
        return parkingBill;
    }

    public void setParkingBill(ParkingBill parkingBill) {
        this.parkingBill = parkingBill;
    }

    @Override
    public String toString() {
        return "ParkingMeter{" +
                "Id=" + Id +
                ", parkingCode='" + parkingCode + '\'' +
                ", startedAt=" + startedAt +
                ", stoppedAt=" + stoppedAt +
                ", parkingBill=" + parkingBill +
                '}';
    }
}
