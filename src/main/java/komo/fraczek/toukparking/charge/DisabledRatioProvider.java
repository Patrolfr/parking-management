package komo.fraczek.toukparking.charge;

import java.math.BigDecimal;

class DisabledRatioProvider extends AbstractRatioProvider{

    @Override
    BigDecimal getRatio() {
        return new BigDecimal("1.2");
    }
}
