package komo.fraczek.toukparking;


import com.google.gson.*;
import komo.fraczek.toukparking.charge.DummyCurrencyRateProviderService;
import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.domain.ParkingStatus;
import komo.fraczek.toukparking.resource.DriverController;
import komo.fraczek.toukparking.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static komo.fraczek.toukparking.domain.DriverType.REGULAR;
import static komo.fraczek.toukparking.domain.ParkingStatus.FINISHED;
import static komo.fraczek.toukparking.domain.ParkingStatus.OCCUPIED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DriverController.class)
public class DriverControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(DriverControllerTest.class);

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParkingService parkingServiceMock;

    @MockBean
    DummyCurrencyRateProviderService currencyRateServiceMock;

    private static final String NUMBER_PLATE = "ABCD1234";

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void test_startParkingMeter() throws Exception{
        logger.trace("Method call test_startParkingMeter()");
//        prepare
        String parkingCodeStub = "ABC-123";
        String numberPlateStub = "XZYK123";
        ResponseEntity<String> responseEntityStub = new ResponseEntity<>(parkingCodeStub, HttpStatus.CREATED);
//        arrange
        when(parkingServiceMock.initParkingActivity(any(String.class), any(DriverType.class))).thenReturn(parkingCodeStub);
//        execute
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post("/start_parking_meter/" + numberPlateStub))
                .andReturn();
//        verify status
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus(), "Incorrect Response Status");
//        verify number of method calls
        verify(parkingServiceMock).initParkingActivity(any(String.class), any(DriverType.class));
//        verify content
        String resultParkingCode = mvcResult.getResponse().getContentAsString();
        assertEquals(resultParkingCode, parkingCodeStub);
    }

    @Test
    public void test_stopParkingMeter() throws Exception{
//        prepare
//        Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-mm-dd")
//                .setPrettyPrinting()
//                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        ParkingBill billStub = new ParkingBill(REGULAR, FINISHED, NUMBER_PLATE, 5,
                                            LocalDate.of(2018, 12, 25), 5.5);
//        arrange
        when(parkingServiceMock.finishParkingActivity("PARKING_CODE"))
                                .thenReturn(billStub);
//        execute
        MvcResult mvcResult = mockMvc.perform(
                                    MockMvcRequestBuilders.post("/stop_parking_meter/" + "PARKING_CODE")
                                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                                        .accept(MediaType.APPLICATION_JSON_UTF8)
                                        .content(gson.toJson(billStub)))
                                    .andReturn();

        logger.trace("***** \n\n" + mvcResult.getResponse().getContentAsString() + "\n\n******");
        logger.trace("***** \n\n" + mvcResult.getResponse().toString() + "\n\n******");
//        PROBLEM WITH PARSING LOCALDATE !?!?!?!
//        ParkingBill responseBill = gson.fromJson(mvcResult.getResponse().getContentAsString(), ParkingBill.class);
//        assertEquals(responseBill.getParkingStatus(), bill.getParkingStatus());

    }
/*
    {"driverTy
    pe":"REGUL
    AR","parki
    ngStatus":
    "FINISHED"

    ,"numberPl
    ate":"ABCD
    1234","par
    kingTimeIn
    Hours":5,"
           \/err
    date":"2018-12-25","parkingFee":5.5}
*/
    class LocalDateAdapter implements JsonSerializer<LocalDate> {
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }
    }

}
