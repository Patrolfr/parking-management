package komo.fraczek.toukparking.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DummyCurrencyRateProviderService implements CurrencyRateProviderService {

    @Override
    public BigDecimal getCurrencyRate(String givenCurrency) {
        return givenCurrency.equals("PLN") ? BigDecimal.ONE : BigDecimal.ONE;
    }

}
