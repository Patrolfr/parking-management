package komo.fraczek.toukparking.charge;

import komo.fraczek.toukparking.domain.DriverType;

public class ParkingBill {

    private int parkingTimeInHours;

    private String vehicleNumberPlate;

    private DriverType driverType;

    private double parkingFee;

    public ParkingBill() {
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
