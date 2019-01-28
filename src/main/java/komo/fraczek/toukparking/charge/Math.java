package komo.fraczek.toukparking.charge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Math {

    //     caluculates value of given geometric series element
    static final BigDecimal countGeomSeriesTerm2(int numberOfElement, BigDecimal ratio){
        return numberOfElement == 1
                ? BigDecimal.valueOf(2)
                : ratio.multiply(Math.countGeomSeriesTerm2(numberOfElement - 1, ratio));
    }

    //     sumTerms sums up values of given number of geometric series terms
    static BigDecimal calculateGeometricSeriesSum(int noOfTerms, BigDecimal ratio) {
        return IntStream.range(1, noOfTerms) //index
                .mapToObj(in -> Math.countGeomSeriesTerm2(in, ratio)) //count value
                .reduce(BigDecimal.ZERO, BigDecimal::add); //sum
    }

}
