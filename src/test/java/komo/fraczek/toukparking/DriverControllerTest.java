package komo.fraczek.toukparking;

import komo.fraczek.toukparking.resource.DriverController;
import komo.fraczek.toukparking.service.ParkingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = {TestContext.class})
@Disabled
public class DriverControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(DriverControllerTest.class);


    private MockMvc mockDriverController;

    @Autowired
    ParkingService parkingService;

    @Before
    public void setUp(){
        mockDriverController = MockMvcBuilders.standaloneSetup(new DriverController()).build();
    }

    @Test
    public void test() throws Exception{
        logger.trace("@Test test()");
//        mockDriverController.perform(post("http://localhost:8080/startMeter/test123"));
//                .andExpect(status().isOk());
//                .andExpect(content().contentType(MediaType.ALL));
    }


}
