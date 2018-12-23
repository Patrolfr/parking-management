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

    //    Parking activity status
    @Enumerated(EnumType.STRING)
    private ParkingStatus parkingStatus;


    //    Holds vehicle registration number
    private String numberPlate;

    private int parkingTimeInHours;

    private LocalDate date;

    private double parkingFee;

    public ParkingBill() {
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
