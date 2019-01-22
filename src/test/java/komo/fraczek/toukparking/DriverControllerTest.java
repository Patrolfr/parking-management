package komo.fraczek.toukparking;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import komo.fraczek.toukparking.domain.DriverType;
import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.resource.DriverController;
import komo.fraczek.toukparking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;

import static komo.fraczek.toukparking.domain.DriverType.REGULAR;
import static komo.fraczek.toukparking.domain.ParkingStatus.FINISHED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DriverController.class)
class DriverControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(DriverControllerTest.class);

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParkingService parkingServiceMock;

    private static final String NUMBER_PLATE = "ABCD1234";

    private Gson gson = createWithLocalDateAdapter();

    static Gson createWithLocalDateAdapter(){
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) ->
                                LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .create();
    }

    private MvcResult doRequest(URI uri) throws Exception{
        return mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, uri)).andReturn();
    }

    @Test
    void test_startParkingMeter() throws Exception {

        String parkingCodeStub = "ABC-123";
        String numberPlateStub = "XZYK123";

        when(parkingServiceMock.initParkingActivity(any(String.class), any(DriverType.class))).thenReturn(parkingCodeStub);

        MvcResult mvcResult = doRequest(new URI("/start_parking_meter/" + numberPlateStub));
        String resultParkingCode = mvcResult.getResponse().getContentAsString();

        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus(), "Incorrect Response Status");
        verify(parkingServiceMock).initParkingActivity(any(String.class), any(DriverType.class));
        assertEquals(resultParkingCode, parkingCodeStub);
    }

    @Test
    void test_stopParkingMeter() throws Exception {

        ParkingBill billStub = new ParkingBill(REGULAR, FINISHED, NUMBER_PLATE, 5,
                    LocalDate.of(2018, 12, 25), BigDecimal.TEN);

        when(parkingServiceMock.finishParkingActivity("PARKING_CODE")).thenReturn(billStub);

        MvcResult mvcResult = doRequest(new URI("/stop_parking_meter/" + "PARKING_CODE"));
        ParkingBill responseBill = gson.fromJson(mvcResult.getResponse().getContentAsString(), ParkingBill.class);

        assertEquals(responseBill.getParkingStatus(), billStub.getParkingStatus());
        assertEquals(responseBill.getDate(), billStub.getDate());
        assertEquals(responseBill.getDriverType(), billStub.getDriverType());
        assertEquals(responseBill.getParkingTimeInHours(), billStub.getParkingTimeInHours());
        assertEquals(responseBill.getParkingFee(), billStub.getParkingFee());
        assertEquals(HttpStatus.ACCEPTED.value(), mvcResult.getResponse().getStatus(), "Incorrect Response Status");
    }
}
