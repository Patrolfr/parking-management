package komo.fraczek.toukparking.service;

import java.math.BigDecimal;

public interface CurrencyRateProviderService {

    public BigDecimal getCurrencyRate(String givenCurrency);
}
