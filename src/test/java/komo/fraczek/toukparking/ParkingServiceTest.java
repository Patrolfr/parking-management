package komo.fraczek.toukparking;

import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.exception.ParkingCodeNotFoundException;
import komo.fraczek.toukparking.exception.PlateNumNotFoundException;
import komo.fraczek.toukparking.service.ParkingRepository;
import komo.fraczek.toukparking.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ParkingServiceTest {

    private static final LocalDateTime DATE1 = LocalDateTime.of(2010, 12, 18, 15, 15);

    private static final LocalDateTime DATE2 = LocalDateTime.of(2010, 12, 18, 18, 18);

    private static final String NUMBER_PLATE = "ABCD1234";

    private static final String PARKING_CODE = "XYZ-123";

    private ParkingService parkingService;

    private ParkingRepository parkingRepositoryMock;


    @BeforeEach
    public void setUp() {
        parkingService = new ParkingService();
        parkingRepositoryMock = mock(ParkingRepository.class);
        parkingService.setParkingRepository(parkingRepositoryMock);
    }


//    @Test
    public void when_getMeterByNumberPlateOrThrowEx_returns_ParkingMeter() {
        ParkingMeter meter = new ParkingMeter(NUMBER_PLATE, PARKING_CODE, DATE1, ParkingStatus.OCCUPIED, DriverType.REGULAR);

//        when(parkingRepositoryMock.findByNumberPlate(NUMBER_PLATE)).thenReturn(Optional.of(meter));

//        ParkingMeter returned = parkingService.getMeterByNumberPlateOrThrowEx(NUMBER_PLATE);

//        assertTrue(true);
//        assertEquals(returned.getNumberPlate(), meter.getNumberPlate());
//        assertEquals(returned.getParkingCode(), meter.getParkingCode());
//        assertEquals(returned.getDriverType(), meter.getDriverType());
//        assertEquals(returned.getStartedAt(), meter.getStartedAt());
////        assertNull(returned.getStoppedAt());
//        assertNull(returned.getStoppedAtDate());
//        assertNull(returned.getStoppedAtTime());
    }


    @Test
    public void when_getMeterByNumberPlateOrThrowEx_throws_Exception() {
//        when(parkingRepositoryMock.findByNumberPlate(any(String.class))).thenReturn(Optional.empty());
//        assertThrows(PlateNumNotFoundException.class, () -> parkingService.getMeterByNumberPlateOrThrowEx("FAKE_NUMBER_PLATE"));
    }


    @Test
    public void when_finishParkingActivity_returns_ParkingBill() {
//        make start time 2 hourse 31 minutes before stop time, expect 3 hours bill
        LocalDateTime startDate = LocalDateTime.now().minusHours(2).minusMinutes(31);
        ParkingMeter meter = new ParkingMeter(NUMBER_PLATE, PARKING_CODE, startDate, ParkingStatus.OCCUPIED, DriverType.REGULAR);

        when(parkingRepositoryMock.findByParkingCode(PARKING_CODE)).thenReturn(Optional.of(meter));

        ParkingBill parkingBill = parkingService.finishParkingActivity(PARKING_CODE);

        assertEquals(parkingBill.getParkingTimeInHours(), 3);
//        assertEquals(parkingBill.getVehicleNumberPlate(), meter.getNumberPlate());
//        assertEquals(parkingBill.getDriverType(), meter.getDriverType());
    }

    @Test
    public void when_finishParkingActivity_throws_Exception() {
        when(parkingRepositoryMock.findByParkingCode(any(String.class))).thenReturn(Optional.empty());
        assertThrows(ParkingCodeNotFoundException.class, () -> parkingService.finishParkingActivity("FAKE_PARKING_CODE"));
    }




}
