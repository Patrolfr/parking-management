package komo.fraczek.toukparking.charge;

import org.springframework.stereotype.Component;

@Component
public class DummyCurrencyRateProviderService {

    public double getCurrencyRate(String givenCurrency){
        return givenCurrency.equals("PLN") ? 1.0 : 1.0;
    }

}
