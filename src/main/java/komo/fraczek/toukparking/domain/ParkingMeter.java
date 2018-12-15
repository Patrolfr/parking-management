package komo.fraczek.toukparking.domain;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingMeter {

    private ParkingStatus parkingStatus;

    private String numberPlate;

    private String parkingCode;

    private LocalDateTime startedAt, stoppedAt;


    // +1 becouse first hour also counts,
    // maybe substract 10 mins to give driver some extra time(?)
    public int parkingTimeInHours() {
        return (int) Duration.between(startedAt, stoppedAt).toHours() + 1;
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
