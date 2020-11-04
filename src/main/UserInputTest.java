package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
    void calculateEta() {
    }

    @Test
    void discount() {
    }

    @Test
    void saveTicket() {
    }

    @Test
    void testDiscount(){


        assertEquals(90.99, user.discount(BigDecimal.valueOf(90.99),31, "Male"));
    }
}