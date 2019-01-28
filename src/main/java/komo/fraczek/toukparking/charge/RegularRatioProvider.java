package komo.fraczek.toukparking.charge;

import java.math.BigDecimal;

public class RegularRatioProvider extends AbstractRatioProvider{

    @Override
    BigDecimal getRatio() {
        return new BigDecimal("1.5");
    }
}
