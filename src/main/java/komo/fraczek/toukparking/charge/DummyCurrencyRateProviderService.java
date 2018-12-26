package komo.fraczek.toukparking.charge;

import org.springframework.stereotype.Component;

@Component
public class DummyCurrencyRateProviderService implements CurrencyRateProviderService{

    @Override
    public double getCurrencyRate(String givenCurrency){
        return givenCurrency.equals("PLN") ? 1.0 : 1.0;
    }

}
