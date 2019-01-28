package komo.fraczek.toukparking.charge;

import komo.fraczek.toukparking.domain.DriverType;

import java.math.BigDecimal;

import static komo.fraczek.toukparking.domain.DriverType.REGULAR;

public class ChargeCalculator {

    public static BigDecimal calculateCharge(int numberOfHours, DriverType driverType){

        AbstractRatioProvider ratioProvider = RatioProviderFactory.getProvider(driverType);

        return driverType.equals(REGULAR)
                ? Math.calculateGeometricSeriesSum(numberOfHours, ratioProvider.getRatio()).add(BigDecimal.ONE)
                : Math.calculateGeometricSeriesSum(numberOfHours, ratioProvider.getRatio());
    }
}
