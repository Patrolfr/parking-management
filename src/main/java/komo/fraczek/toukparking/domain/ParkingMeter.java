package komo.fraczek.toukparking.domain;


import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    //    maybe substract 10 mins to give driver some extra time(?)
    //    +1 becouse first hour also counts,
    public int calculateParkingTimeInHours() {
        return (int) Duration.between(startedAt, stoppedAt).toHours() + 1;
    }

    public ParkingMeter(ParkingBill parkingBill) {
        this.parkingBill = parkingBill;
        this.startedAt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.parkingCode = ParkingCodeGenerator.getCode();
    }

    //   default counstructor, getters/setter
    public ParkingMeter() {
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
