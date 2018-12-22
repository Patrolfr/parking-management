package komo.fraczek.toukparking.domain;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class ParkingBill {


    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //    Driver type
    @Enumerated(EnumType.STRING)
    private DriverType driverType;

    private int parkingTimeInHours;

    private LocalDate date;

    private double parkingFee;

    public ParkingBill() {
    }

    public ParkingBill(DriverType driverType, int parkingTimeInHours, LocalDate date, double parkingFee) {
        this.driverType = driverType;
        this.parkingTimeInHours = parkingTimeInHours;
        this.date = date;
        this.parkingFee = parkingFee;
    }

    public ParkingBill(DriverType driverType, int parkingTimeInHours, double parkingFee) {
        this.driverType = driverType;
        this.parkingTimeInHours = parkingTimeInHours;
        this.parkingFee = parkingFee;
    }

    public ParkingBill(int parkingTimeInHours, DriverType driverType) {
        this.parkingTimeInHours = parkingTimeInHours;
        this.driverType = driverType;
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

    public double getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(double parkingFee) {
        this.parkingFee = parkingFee;
    }

}
