package komo.fraczek.toukparking;


import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.exception.ParkingCodeNotFoundException;
import komo.fraczek.toukparking.exception.PlateNumNotFoundException;
import komo.fraczek.toukparking.service.ParkingRepository;
import komo.fraczek.toukparking.service.ParkingService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingServiceTest {

    private static final LocalDateTime DATE1 = LocalDateTime.of(2010,12,18,15,15);

    private static final LocalDateTime DATE2 = LocalDateTime.of(2010,12,18,18,18);

    private static final String NUMBER_PLATE = "ABCD1234";

    private static final String PARKING_CODE = "XYZ-123";


    private ParkingService parkingService;

    private ParkingRepository parkingRepositoryMock;

    @Before
    public void setUp(){
        parkingService = new ParkingService();

        parkingRepositoryMock = mock(ParkingRepository.class);
        parkingService.setParkingRepository(parkingRepositoryMock);
    }


    @Test
    public void when_getMeterByNumberPlateOrThrowEx_returns_ParkingMeter(){

        ParkingMeter meter = new ParkingMeter(NUMBER_PLATE, PARKING_CODE, DATE1, ParkingStatus.OCCUPIED, DriverType.REGULAR);

        when(parkingRepositoryMock.findByNumberPlate(NUMBER_PLATE)).thenReturn(Optional.of(meter));

        ParkingMeter returned = parkingService.getMeterByNumberPlateOrThrowEx(NUMBER_PLATE);

        assertEquals(returned.getNumberPlate(), meter.getNumberPlate());
        assertEquals(returned.getParkingCode(), meter.getParkingCode());
        assertEquals(returned.getDriverType(), meter.getDriverType());
        assertEquals(returned.getStartedAt(), meter.getStartedAt());
        assertNull(returned.getStoppedAt());
    }

    @Test(expected = PlateNumNotFoundException.class)
    public void when_getMeterByNumberPlateOrThrowEx_throws_Exception(){

        when(parkingRepositoryMock.findByNumberPlate(any(String.class))).thenReturn(Optional.empty());

        parkingService.getMeterByNumberPlateOrThrowEx("FAKE_NUMBER_PLATE");
    }




    @Test
    public void when_finishParkingActivity_returns_ParkingBill(){

//        when(parkingRepositoryMock.findByParkingCode(any(String.class))).thenReturn(Optional.empty());

//        parkingService.finishParkingActivity("FAKE_PARKING_CODE");
    }


    @Test(expected = ParkingCodeNotFoundException.class)
    public void when_finishParkingActivity_throws_Exception(){

        when(parkingRepositoryMock.findByParkingCode(any(String.class))).thenReturn(Optional.empty());

        parkingService.finishParkingActivity("FAKE_PARKING_CODE");
    }




}
