package komo.fraczek.toukparking.domain;

import javax.persistence.*;
import java.math.BigDecimal;
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

    //    Vehicle registration number
    private String numberPlate;

    private int parkingTimeInHours;

    //    Bill issue date
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    private BigDecimal parkingFee;


    public ParkingBill(DriverType driverType, String numberPlate) {
        this.driverType = driverType;
        this.numberPlate = numberPlate;
        this.parkingStatus = ParkingStatus.OCCUPIED;
    }

    
    public ParkingBill() {
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
