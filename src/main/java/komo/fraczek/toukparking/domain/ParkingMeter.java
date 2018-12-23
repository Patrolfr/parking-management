package komo.fraczek.toukparking.domain;


import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class ParkingMeter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //    Holds parking ticket code generated for driver
    private String parkingCode;


    //    Beggining of the activity
    private LocalDateTime startedAt;

    //   atribute used in JpaRepository getByStoppedAtDate method for convenient daily income calculation
    private LocalDateTime stoppedAt;


    @OneToOne
    private ParkingBill parkingBill;


//    +1 becouse first hour also counts,
//    maybe substract 10 mins to give driver some extra time(?)
    public int calculateParkingTimeInHours() {
        return (int) Duration.between(startedAt, stoppedAt).toHours() + 1;
    }


    public ParkingMeter(String numberPlate, DriverType driverType) {

//        this.parkingFee = 0.0;
//        this.parkingStatus = ParkingStatus.OCCUPIED;
        this.parkingCode = ParkingCodeGenerator.getCode();
        this.startedAt = LocalDateTime.now();
    }


//    counstructors, getters/setter

    public ParkingMeter() {
    }

    public ParkingMeter(String numberPlate, String parkingCode, LocalDateTime startedAt, ParkingStatus parkingStatus, DriverType driverType) {
//        this.numberPlate = numberPlate;
        this.parkingCode = parkingCode;
        this.startedAt = startedAt;
//        this.parkingStatus = parkingStatus;
//        this.driverType = driverType;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }


//    public ParkingStatus getParkingStatus() {
//        return parkingStatus;
//    }

//    public void setParkingStatus(ParkingStatus parkingStatus) {
//        this.parkingStatus = parkingStatus;
//    }

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
//                ", parkingStatus=" + parkingStatus +
                ", parkingCode='" + parkingCode + '\'' +
                ", startedAt=" + startedAt +
                ", stoppedAt=" + stoppedAt +
                ", parkingBill=" + parkingBill +
                '}';
    }
}
