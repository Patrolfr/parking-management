package komo.fraczek.toukparking.charge;

import komo.fraczek.toukparking.domain.DriverType;

class RatioProviderFactory {

    static AbstractRatioProvider getProvider(DriverType driverType){
        switch (driverType){
            case REGULAR: return new RegularRatioProvider();
            case DISABLED: return new DisabledRatioProvider();
            default: return new RegularRatioProvider();
        }
    }

}
