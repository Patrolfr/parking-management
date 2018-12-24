package komo.fraczek.toukparking;

import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingMeter;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.exception.ParkingCodeNotFoundException;
import komo.fraczek.toukparking.exception.PlateNumNotFoundException;
import komo.fraczek.toukparking.service.BillRepository;
import komo.fraczek.toukparking.service.MeterRepository;
import komo.fraczek.toukparking.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static komo.fraczek.toukparking.domain.DriverType.REGULAR;
import static komo.fraczek.toukparking.domain.ParkingStatus.FINISHED;
import static komo.fraczek.toukparking.domain.ParkingStatus.OCCUPIED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ParkingServiceTest {

    private static final LocalDateTime DATETIME1 = LocalDateTime.of(2010, 12, 18, 15, 15);

    private static final LocalDateTime DATE2 = LocalDateTime.of(2010, 12, 18, 18, 18);

    private static final String NUMBER_PLATE = "ABCD1234";

    private static final String PARKING_CODE = "XYZ-123";

    private static final int TIME_HOURS = 5;

    private ParkingService parkingService;

    private MeterRepository meterRepositoryMock;

    private BillRepository billRepositoryMock;

    @BeforeEach
    public void setUp() {
        parkingService = new ParkingService();
        meterRepositoryMock = mock(MeterRepository.class);
        billRepositoryMock = mock(BillRepository.class);
        parkingService.setMeterRepository(meterRepositoryMock);
        parkingService.setBillRepository(billRepositoryMock);
    }


    @Test
    public void when_getBillByNumberPlateOrThrowEx_returns_ParkingBill() {
//        prepare
//        create the bill
        ParkingBill bill = new ParkingBill(REGULAR, OCCUPIED, NUMBER_PLATE, 0, null, 0);
//        arrange
        when(billRepositoryMock.findByNumberPlateAndParkingStatus(NUMBER_PLATE, OCCUPIED)).thenReturn(Optional.of(bill));
//        perform
        ParkingBill returned = parkingService.getBillByNumberPlateOrThrowEx(NUMBER_PLATE);
//        verify
        assertEquals(returned.getNumberPlate(), bill.getNumberPlate());
        assertEquals(returned.getDriverType(), bill.getDriverType());
        assertNull(returned.getDate());
        assertEquals(0, bill.getParkingTimeInHours());
        assertEquals(0.0, bill.getParkingFee());
    }

    @Test
    public void when_getBillByNumberPlateOrThrowEx_throws_Exception() {
        when(billRepositoryMock.findByNumberPlateAndParkingStatus(any(String.class), any(ParkingStatus.class))).thenReturn(Optional.empty());
        assertThrows(PlateNumNotFoundException.class, () -> parkingService.getBillByNumberPlateOrThrowEx("FAKE_NUMBER_PLATE"));
    }

    @Test
    public void when_finishParkingActivity_returns_ParkingBill() {
//        prepare
//        create the bill
        ParkingBill bill = new ParkingBill(REGULAR, OCCUPIED, NUMBER_PLATE, 0, null, 0.0);
//        create the meter
        ParkingMeter meter = new ParkingMeter(bill);
//        set start time 2 hourse 31 minutes before stop time, expect 3 hours bill
        meter.setStartedAt(LocalDateTime.now().minusHours(2).minusMinutes(31));
//        arrange
        when(meterRepositoryMock.findByParkingCode(PARKING_CODE)).thenReturn(Optional.of(meter));
        when(billRepositoryMock.save(bill)).thenReturn(bill);
//        execute
        ParkingBill returnedBill = parkingService.finishParkingActivity(PARKING_CODE);
//        verify
        assertEquals(returnedBill.getDriverType(), bill.getDriverType());
        assertEquals(returnedBill.getParkingStatus(), FINISHED);
        assertEquals(returnedBill.getNumberPlate(), bill.getNumberPlate());
        assertEquals(returnedBill.getDate(), meter.getStoppedAt().toLocalDate());
        assertEquals(returnedBill.getParkingTimeInHours(), 3);
        assertNotEquals(bill.getParkingStatus(), 0.0);
    }

    @Test
    public void when_finishParkingActivity_throws_Exception() {
        when(meterRepositoryMock.findByParkingCode(any(String.class))).thenReturn(Optional.empty());
        assertThrows(ParkingCodeNotFoundException.class, () -> parkingService.finishParkingActivity("FAKE_PARKING_CODE"));
    }


}