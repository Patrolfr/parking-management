package komo.fraczek.toukparking;

import komo.fraczek.toukparking.charge.ChargeCalculator;
import komo.fraczek.toukparking.domain.DriverType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class ChargeCalculatorTest {

//    Fees calculated by https://www.symbolab.com/solver/geometric-sequence-calculator


    @ParameterizedTest
    @CsvSource({"1, 1.0",
            "2, 3.0",
            "3, 6.0",
            "4, 10.5",
            "5, 17.25",
            "6, 27.375",
            "7, 42.5625"})
    public void test_calculateCharge_for_RegularDriverType(int day, double charge) {
        Assertions.assertEquals(
                BigDecimal.valueOf(charge).setScale(3, RoundingMode.CEILING),
                ChargeCalculator.calculateCharge(day, DriverType.REGULAR).setScale(3, RoundingMode.CEILING));
    }

    @ParameterizedTest
    @CsvSource({"1, 0.0",
            "2, 2.0",
            "3, 4.4",
            "4, 7.28",
            "5, 10.736",
            "6, 14.8832",
            "7, 19.85984"})
    public void test_calculateCharge_for_DisabledDriverType(int day, double charge) {
        Assertions.assertEquals(
                BigDecimal.valueOf(charge).setScale(3, RoundingMode.CEILING),
                ChargeCalculator.calculateCharge(day, DriverType.DISABLED).setScale(3, RoundingMode.CEILING));
    }


}
