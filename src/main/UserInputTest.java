package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {
    UserInput user;
    @BeforeEach
    void setUp() {
        user = new UserInput();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateEta() throws ParseException {
        //assertEquals("Thu Nov 05 18:07:00 CST 2020", user.calculateEta("2020-11-05 15:00", BigDecimal.valueOf(187.99),BigDecimal.valueOf(60)));
    }



    @Test
    void testparsePhoneNumber (){
        assertEquals("(901) 243-2672", user.parsePhoneNumber("(901) 243-2672"));
    }

    @Test
    void testparseGenderNumber (){
        assertEquals("Male", user.parseGender("Male"));
        assertEquals(null, user.parseGender("MALE"));
    }
}