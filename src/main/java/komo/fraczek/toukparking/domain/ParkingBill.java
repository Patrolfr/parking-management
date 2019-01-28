package komo.fraczek.toukparking.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import static komo.fraczek.toukparking.domain.ParkingStatus.OCCUPIED;

@Entity
@Getter
@AllArgsConstructor //just for tests
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParkingBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    private DriverType driverType;

    @Enumerated(EnumType.STRING)
    private ParkingStatus parkingStatus;

    private String numberPlate;

    private int parkingTimeInHours;

    private LocalDate date;

    //    Fee grows so fast that after few days it exceedes default DECIMAL(19, 2) range..
    @Column(name = "PARKING_FEE", columnDefinition = "DECIMAL(30, 2)")
    @Setter
    private BigDecimal parkingFee;


    public void issue(ParkingMeter parkingMeter){
        this.parkingTimeInHours = parkingMeter.calculateParkingTimeInHours();
        this.date = parkingMeter.getStoppedAt().toLocalDate();
        this.parkingStatus = ParkingStatus.FINISHED;
    }

    public static ParkingBill createOccupied(DriverType driverType, String numberPlate){
        ParkingBill parkingBill = new ParkingBill();
        parkingBill.driverType = driverType;
        parkingBill.numberPlate = numberPlate;
        parkingBill.parkingStatus = OCCUPIED;
        return parkingBill;
    }
}
