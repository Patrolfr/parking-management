package komo.fraczek.toukparking;


import komo.fraczek.toukparking.service.ParkingRepository;
import komo.fraczek.toukparking.service.ParkingService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestContext {


    @Autowired
    ParkingRepository parkingRepository;

    @Bean
    public ParkingService parkingService(){
        return mock(ParkingService.class);
    }



}
