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


    //     BiFunction caluculates value of given geometric series element
    //     first term value is hard-coded.
    static final BiFunction<Integer, Double, BigDecimal> countGeomSeriesTerm = (noOfEl, ratio) -> noOfEl == 1
            ? BigDecimal.valueOf(2)
            : BigDecimal.valueOf(ratio).multiply(Math.countGeomSeriesTerm.apply(noOfEl - 1, ratio));


    //     sumTerms sums up values of given number of geometric series terms
    static final BigDecimal calculateGeometricSeriesSum(int noOfTerms, double ratio) {
        return IntStream.range(1, noOfTerms) //index
                .mapToObj(in -> Math.countGeomSeriesTerm.apply(in, ratio)) //count value
                .reduce(BigDecimal.ZERO, BigDecimal::add); //sum
    }

}
