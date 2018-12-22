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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //    Parking activity status
    @Enumerated(EnumType.STRING)
    private ParkingStatus parkingStatus;

    //    Driver type
    @Enumerated(EnumType.STRING)
    private DriverType driverType;

    //    Holds vehicle registration number
    private String numberPlate;

    //    Holds parking ticket code generated for driver
    private String parkingCode;

    //    Beggining of the activity
    private LocalDateTime startedAt;

    //   atribute used in JpaRepository getByStoppedAtDate method for convenient daily income calculation
    private LocalDate stoppedAtDate;
    private LocalTime stoppedAtTime;

//    @Nullable
//    @Transient
//    private double parkingFee;



//    +1 becouse first hour also counts,
//    maybe substract 10 mins to give driver some extra time(?)
    public int calculateParkingTimeInHours() {
        return (int) Duration.between(startedAt, LocalDateTime.of(stoppedAtDate, stoppedAtTime)).toHours() + 1;
    }


    public ParkingMeter(String numberPlate, DriverType driverType) {
        this.numberPlate = numberPlate;
        this.driverType = driverType;

//        this.parkingFee = 0.0;
        this.parkingStatus = ParkingStatus.OCCUPIED;
        this.parkingCode = ParkingCodeGenerator.getCode();
        this.startedAt = LocalDateTime.now();
    }


//    counstructors, getters/setter

    public ParkingMeter() {
    }

    public ParkingMeter(String numberPlate, String parkingCode, LocalDateTime startedAt, ParkingStatus parkingStatus, DriverType driverType) {
        this.numberPlate = numberPlate;
        this.parkingCode = parkingCode;
        this.startedAt = startedAt;
        this.parkingStatus = parkingStatus;
        this.driverType = driverType;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public ParkingStatus getParkingStatus() {
        return parkingStatus;
    }

    public void setParkingStatus(ParkingStatus parkingStatus) {
        this.parkingStatus = parkingStatus;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
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

    public LocalDate getStoppedAtDate() {
        return stoppedAtDate;
    }

    public void setStoppedAtDate(LocalDate stoppedAtDate) {
        this.stoppedAtDate = stoppedAtDate;
    }

    public LocalTime getStoppedAtTime() {
        return stoppedAtTime;
    }

    public void setStoppedAtTime(LocalTime stoppedAtTime) {
        this.stoppedAtTime = stoppedAtTime;
    }

//    public double getParkingFee() {
//        return parkingFee;
//    }
//
//    public void setParkingFee(double parkingFee) {
//        this.parkingFee = parkingFee;
//    }

    @Override
    public String toString() {
        return "ParkingMeter{" +
                "Id=" + Id +
                ", numberPlate='" + numberPlate + '\'' +
                ", parkingCode='" + parkingCode + '\'' +
                ", startedAt=" + startedAt +
                ", stoppedAt=" + stoppedAtDate + " " + stoppedAtTime +
                ", parkingStatus=" + parkingStatus +
                ", driverType=" + driverType +
                '}';
    }

}
