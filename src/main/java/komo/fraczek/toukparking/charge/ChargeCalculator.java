package komo.fraczek.toukparking.charge;

import komo.fraczek.toukparking.domain.DriverType;

import java.math.BigDecimal;

import static komo.fraczek.toukparking.domain.DriverType.REGULAR;

public class ChargeCalculator {

    public static BigDecimal calculateCharge(int numberOfHours, DriverType driverType){

        double ratioRegular = 1.5000000;
        double ratioDisabled = 1.2000000;

        return driverType.equals(REGULAR)
                ? Math.calculateGeometricSeriesSum(numberOfHours, ratioRegular).add(BigDecimal.ONE)
                : Math.calculateGeometricSeriesSum(numberOfHours, ratioDisabled);
    }
}
