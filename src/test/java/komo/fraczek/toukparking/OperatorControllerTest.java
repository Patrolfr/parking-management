package komo.fraczek.toukparking;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import komo.fraczek.toukparking.domain.ParkingBill;
import komo.fraczek.toukparking.resource.OperatorController;
import komo.fraczek.toukparking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static komo.fraczek.toukparking.domain.DriverType.REGULAR;
import static komo.fraczek.toukparking.domain.ParkingStatus.FINISHED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OperatorController.class)
public class OperatorControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(OperatorControllerTest.class);

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParkingService parkingServiceMock;

    private static final String NUMBER_PLATE = "ABCD1234";

    private static final LocalDate DATE = LocalDate.of(2018, 12, 25);

    private static final BigDecimal INCOME = new BigDecimal(122.99);

    private static final int HOURS = 5;

    @Test
    public void test_getDailyIncome() throws Exception{

//        arrange
        when(parkingServiceMock.calculateDailyIncome(DATE)).thenReturn(INCOME);
//        execute
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/daily_income/" + DATE.toString())).andReturn();
//        verify number of method calls
        verify(parkingServiceMock).calculateDailyIncome(any(LocalDate.class));
//        verify value
        assertEquals(INCOME, new BigDecimal( mvcResult.getResponse().getContentAsString() ));
//        verify status
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus(), "Incorrect Response Status");
    }

    @Test
    public void test_retrieveParkingBillByNumberPlate() throws Exception{
//        prepare json parser
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) ->
                                LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .create();
//        prepare parking bill stub
        ParkingBill billStub = new ParkingBill(REGULAR, FINISHED, NUMBER_PLATE, HOURS, DATE, BigDecimal.TEN);
//        arrange
        when(parkingServiceMock.getBillByNumberPlateOrThrowEx(any(String.class))).thenReturn(billStub);
//        execute
        MvcResult mvcResult = mockMvc
                .perform(
                    MockMvcRequestBuilders.get("/parking_bill/" + NUMBER_PLATE)
                    .content(gson.toJson(billStub)))
                .andReturn();
//        get response
        ParkingBill responseBill = gson.fromJson(mvcResult.getResponse().getContentAsString(), ParkingBill.class);
//        verify
        assertEquals(responseBill.getParkingStatus(), billStub.getParkingStatus());
        assertEquals(responseBill.getDate(), billStub.getDate());
        assertEquals(responseBill.getDriverType(), billStub.getDriverType());
        assertEquals(responseBill.getParkingTimeInHours(), billStub.getParkingTimeInHours());
        assertEquals(responseBill.getParkingFee(), billStub.getParkingFee());
//        verify status
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus(), "Incorrect Response Status");
    }



}
