package komo.fraczek.toukparking;


import com.google.gson.Gson;
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

    private static final Gson gson = DriverControllerTest.createWithLocalDateAdapter();

    private MvcResult doRequest(URI uri, ParkingBill parkingBill) throws Exception{
        return mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, uri)
                                .content(gson.toJson(parkingBill)))
                    .andReturn();
    }

    @Test
    void test_getDailyIncome() throws Exception{

        when(parkingServiceMock.calculateDailyIncome(DATE)).thenReturn(INCOME);

        MvcResult mvcResult = doRequest(new URI("/daily_income/" + DATE.toString()), null);

        verify(parkingServiceMock).calculateDailyIncome(any(LocalDate.class));
        assertEquals(INCOME, new BigDecimal( mvcResult.getResponse().getContentAsString() ));
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus(), "Incorrect Response Status");
    }

    @Test
    void test_retrieveParkingBillByNumberPlate() throws Exception{

        ParkingBill billStub = new ParkingBill(REGULAR, FINISHED, NUMBER_PLATE, HOURS, DATE, BigDecimal.TEN);

        when(parkingServiceMock.getBillByNumberPlateOrThrowEx(any(String.class))).thenReturn(billStub);

        MvcResult mvcResult = doRequest(new URI("/parking_bill/" + NUMBER_PLATE), billStub);

        ParkingBill responseBill = gson.fromJson(mvcResult.getResponse().getContentAsString(), ParkingBill.class);

        assertEquals(responseBill.getParkingStatus(), billStub.getParkingStatus());
        assertEquals(responseBill.getDate(), billStub.getDate());
        assertEquals(responseBill.getDriverType(), billStub.getDriverType());
        assertEquals(responseBill.getParkingTimeInHours(), billStub.getParkingTimeInHours());
        assertEquals(responseBill.getParkingFee(), billStub.getParkingFee());
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus(), "Incorrect Response Status");
    }

}
