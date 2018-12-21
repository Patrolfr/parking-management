package komo.fraczek.toukparking.charge;

import komo.fraczek.toukparking.domain.DriverType;

public class ChargeCalculator {

    public static double calculateCharge(int numberOfHours, DriverType driverType){

//        Math..

        return 1.35 * numberOfHours;
    }
}
