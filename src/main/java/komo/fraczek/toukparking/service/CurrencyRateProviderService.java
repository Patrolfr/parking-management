package komo.fraczek.toukparking.service;

import java.math.BigDecimal;

public interface CurrencyRateProviderService {

    BigDecimal getCurrencyRate(String givenCurrency);
}
