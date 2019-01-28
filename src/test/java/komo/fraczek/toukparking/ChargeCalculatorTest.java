package komo.fraczek.toukparking;

import komo.fraczek.toukparking.charge.ChargeCalculator;
import komo.fraczek.toukparking.domain.DriverType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;


class ChargeCalculatorTest {

//    Fees calculated by https://www.symbolab.com/solver/geometric-sequence-calculator

    @ParameterizedTest
    @CsvSource({"1, 1.0, REGULAR",
            "2, 3.0, REGULAR",
            "3, 6.0, REGULAR",
            "4, 10.5, REGULAR",
            "5, 17.25, REGULAR",
            "6, 27.375, REGULAR",
            "7, 42.5625, REGULAR",
            "1, 0.0, DISABLED",
            "2, 2.0, DISABLED",
            "3, 4.4, DISABLED",
            "4, 7.28, DISABLED",
            "5, 10.736, DISABLED",
            "6, 14.8832, DISABLED",
            "7, 19.85984, DISABLED"})
    void test_calculateCharge_for_RegularDriverType(int day, BigDecimal charge, DriverType type) {
        Assertions.assertEquals(
                charge.setScale(3, RoundingMode.CEILING),
                ChargeCalculator.calculateCharge(day, type).setScale(3, RoundingMode.CEILING));
    }


}
