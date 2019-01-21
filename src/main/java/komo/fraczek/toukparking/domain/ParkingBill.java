package komo.fraczek.toukparking.domain;

import komo.fraczek.toukparking.charge.ChargeCalculator;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.EnumType;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
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
    private BigDecimal parkingFee;


    public ParkingBill() {
    }

    public void issue(ParkingMeter parkingMeter){
        this.parkingTimeInHours = parkingMeter.calculateParkingTimeInHours();
        this.date = parkingMeter.getStoppedAt().toLocalDate();
        this.parkingStatus = ParkingStatus.FINISHED;
    }

    public static ParkingBill createOccupied(DriverType driverType, String numberPlate){
        ParkingBill parkingBill = new ParkingBill();
        parkingBill.setDriverType(driverType);
        parkingBill.setNumberPlate(numberPlate);
        return parkingBill;
    }

    public ParkingBill(DriverType driverType, ParkingStatus parkingStatus, String numberPlate, int parkingTimeInHours, LocalDate date, BigDecimal parkingFee) {
        this.driverType = driverType;
        this.parkingStatus = parkingStatus;
        this.numberPlate = numberPlate;
        this.parkingTimeInHours = parkingTimeInHours;
        this.date = date;
        this.parkingFee = parkingFee;
    }

    public int getParkingTimeInHours() {
        return parkingTimeInHours;
    }

    public void setParkingTimeInHours(int parkingTimeInHours) {
        this.parkingTimeInHours = parkingTimeInHours;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public BigDecimal getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(BigDecimal parkingFee) {
        this.parkingFee = parkingFee;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ParkingStatus getParkingStatus() {
        return parkingStatus;
    }

    public void setParkingStatus(ParkingStatus parkingStatus) {
        this.parkingStatus = parkingStatus;
    }

    @Override
    public String toString() {
        return "ParkingBill{" +
                "Id=" + Id +
                ", driverType=" + driverType +
                ", parkingStatus=" + parkingStatus +
                ", numberPlate='" + numberPlate + '\'' +
                ", parkingTimeInHours=" + parkingTimeInHours +
                ", date=" + date +
                ", parkingFee=" + parkingFee +
                '}';
    }
}
