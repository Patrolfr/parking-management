package komo.fraczek.toukparking.domain;

import komo.fraczek.toukparking.domain.DriverType;

public class ParkingBill {

    private String vehicleNumberPlate;

    private DriverType driverType;

    private int parkingTimeInHours;

    private double parkingFee;

    public ParkingBill() {
    }


    public ParkingBill(String vehicleNumberPlate, DriverType driverType, int parkingTimeInHours, double parkingFee) {
        this.vehicleNumberPlate = vehicleNumberPlate;
        this.driverType = driverType;
        this.parkingTimeInHours = parkingTimeInHours;
        this.parkingFee = parkingFee;
    }

    public ParkingBill(int parkingTimeInHours, String vehicleNumberPlate, DriverType driverType) {
        this.parkingTimeInHours = parkingTimeInHours;
        this.vehicleNumberPlate = vehicleNumberPlate;
        this.driverType = driverType;
    }

    public int getParkingTimeInHours() {
        return parkingTimeInHours;
    }

    public void setParkingTimeInHours(int parkingTimeInHours) {
        this.parkingTimeInHours = parkingTimeInHours;
    }

    public String getVehicleNumberPlate() {
        return vehicleNumberPlate;
    }

    public void setVehicleNumberPlate(String vehicleNumberPlate) {
        this.vehicleNumberPlate = vehicleNumberPlate;
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
