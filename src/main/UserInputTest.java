package main;

import entity.BoardingPassTrain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {
    private static final Scanner testInput = new Scanner(System.in);
    UserInput user;
    BoardingPassTrain test;
    BoardingPassTrain test1;

    @BeforeEach
    void setUp() {
        user = new UserInput();
        test = new BoardingPassTrain();
        test1 = new BoardingPassTrain();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculateEta() throws ParseException {
        //assertEquals("Thu Nov 05 18:07:00 CST 2020", user.calculateEta("2020-11-05 15:00", BigDecimal.valueOf(187.99),BigDecimal.valueOf(60)));
    }

    @Test
    void testsetId(){
        test.setId(5);
        assertEquals(5, test.getId());
        assertSame(5, test.getId());
    }

    @Test
    void testsetName(){
        test.setName("Herman");
        assertEquals("Herman", test.getName());
        assertSame("Herman", test.getName());
    }

    @Test
    void testsetEmail(){
        test.setEmail("hbreck@gnail.com");
        assertEquals("hbreck@gnail.com", test.getEmail());
        assertSame("hbreck@gnail.com", test.getEmail());

    }

    @Test
    void testsetPhone(){
        test.setPhone("(901) 241-2672");
        assertEquals("(901) 241-2672", test.getPhone());
        assertSame("(901) 241-2672", test.getPhone());

    }

    @Test
    void testsetGender(){
        test.setGender("Male");
        assertEquals("Male", test.getGender());
        assertSame("Male", test.getGender());
    }

    @Test
    void testsetAge(){
        test.setAge(31);
        assertEquals(31, test.getAge());
        assertSame(31, test.getAge());
    }

    @Test
    void testobjectTicketPrice(){
        BigDecimal price = new BigDecimal(500.00);
        BigDecimal price1 = new BigDecimal(6000.00);
        MyBigDecimal amount = new MyBigDecimal(price1);
        BigDecimal expected = BigDecimal.valueOf(500.00);
        test.setTicketPrice(price);
        test1.setTicketPrice(price1);
        assertNotEquals(test, test1);
        //assertEquals(500, test.getTicketPrice());
        assertSame(price, test.getTicketPrice());
        assertTrue(expected.compareTo(test.getTicketPrice()) == 0);
        assertTrue(amount.compareTo(test1.getTicketPrice()) == 0);
    }

    @Test
    void testEtaObject(){
        for(int i = 0; i < 50; i++ ){

            Date date = new Date(2020, 11, 4);
            Date date1 = new Date(2020, 11, 4);

            test.setEta(date);
            test1.setEta(date1);
            assertNotEquals(test, test1);

        }
    }

    @Test
    void testparsePhoneNumber (){

        assertEquals("(901) 243-2672", user.parsePhoneNumber("(901) 243-2672"));
        assertEquals("(901) 243-2672", user.parsePhoneNumber("9012432672"));
        assertEquals(null, user.parsePhoneNumber("(901) 243-26"));
        assertEquals(null, user.parsePhoneNumber("abcdefghij"));

    }

    @Test
    void testparseGenderNumber (){
        assertEquals("Male", user.parseGender("Male"));
        assertEquals(null, user.parseGender("MALE"));
        assertEquals(null, user.parseGender("mALE"));
        assertEquals(null, user.parseGender("MaLE"));
        assertEquals(null, user.parseGender("MAlE"));
        assertEquals(null, user.parseGender("MALe"));
    }
}