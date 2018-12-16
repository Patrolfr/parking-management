package komo.fraczek.toukparking.domain;


import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class ParkingMeter {

    @Id
    @GeneratedValue
    private Long Id;

//    Holds registration number of vehicle
    private String numberPlate;

//    Holds parking ticket code generated for driver
    private String parkingCode;

//    Beggining/end of the activity
    private LocalDateTime startedAt, stoppedAt;

//    Parking activity status
    @Enumerated(EnumType.STRING)
    private ParkingStatus parkingStatus;

//    Driver type
    @Enumerated(EnumType.STRING)
    private DriverType driverType;


//    +1 becouse first hour also counts,
//    maybe substract 10 mins to give driver some extra time(?)
    public int parkingTimeInHours() {
        return (int) Duration.between(startedAt, stoppedAt).toHours() + 1;
    }


//
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

    public LocalDateTime getStoppedAt() {
        return stoppedAt;
    }

    public void setStoppedAt(LocalDateTime stoppedAt) {
        this.stoppedAt = stoppedAt;
    }
}
