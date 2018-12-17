package komo.fraczek.toukparking.domain;


import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class ParkingMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

//    Holds vehicle registration number
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

//    counstructors, getters/setter

    public ParkingMeter(String numberPlate, DriverType driverType) {
        this.numberPlate = numberPlate;
        this.driverType = driverType;

        this.parkingStatus = ParkingStatus.OCCUPIED;
        this.parkingCode = ParkingCodeGenerator.getCode();
        this.startedAt = LocalDateTime.now();
    }

    public ParkingMeter() {
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

    public LocalDateTime getStoppedAt() {
        return stoppedAt;
    }

    public void setStoppedAt(LocalDateTime stoppedAt) {
        this.stoppedAt = stoppedAt;
    }

    @Override
    public String toString() {
        return "ParkingMeter{" +
                "Id=" + Id +
                ", numberPlate='" + numberPlate + '\'' +
                ", parkingCode='" + parkingCode + '\'' +
                ", startedAt=" + startedAt +
                ", stoppedAt=" + stoppedAt +
                ", parkingStatus=" + parkingStatus +
                ", driverType=" + driverType +
                '}';
    }
}
