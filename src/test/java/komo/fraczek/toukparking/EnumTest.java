package komo.fraczek.toukparking;


import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EnumTest {

    //    @Test  -> error!!!
    @ParameterizedTest
    @EnumSource(ParkingStatus.class)
    public void testParkingStatusEnum(ParkingStatus parkingStatus) {
        assertNotNull(parkingStatus);
    }

    @ParameterizedTest
    @EnumSource(DriverType.class)
    public void testDriverTypeEnum(DriverType driverType) {
        assertNotNull(driverType);
    }


}
