package komo.fraczek.toukparking.charge;

import komo.fraczek.toukparking.domain.DriverType;

import java.math.BigDecimal;

import static komo.fraczek.toukparking.domain.DriverType.REGULAR;

public class ChargeCalculator {

    public static BigDecimal calculateCharge(int numberOfHours, DriverType driverType){

        BigDecimal ratioRegular = new BigDecimal("1.5");
        BigDecimal ratioDisabled = new BigDecimal("1.2");

        return driverType.equals(REGULAR)
                ? Math.calculateGeometricSeriesSum(numberOfHours, ratioRegular).add(BigDecimal.ONE)
                : Math.calculateGeometricSeriesSum(numberOfHours, ratioDisabled);
    }
}
