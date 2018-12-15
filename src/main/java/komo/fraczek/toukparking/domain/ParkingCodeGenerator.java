package komo.fraczek.toukparking.domain;


import org.apache.commons.lang3.RandomStringUtils;

public class ParkingCodeGenerator {

    public static String getCode(){
        String firstPart = RandomStringUtils.randomAlphabetic(3);
        String secondPart = RandomStringUtils.randomNumeric(3);
        return firstPart.toUpperCase() + "-" + secondPart;
    }

}
