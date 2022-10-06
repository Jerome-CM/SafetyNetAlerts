package com.safetynet.alerts;

import com.safetynet.alerts.utility.Utility;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilityTest {

    @Test
    public void utilityPersonAgeTest() throws Exception{
        Calendar birthdate = Calendar.getInstance();
        birthdate.set(1991, 8,30);

        int expected = 31;
        int result = Utility.personAge(birthdate.getTime());
        assertEquals("", expected, result);

    }

    @Test
    public void isAdultTest() throws Exception{

        Calendar birthdate = Calendar.getInstance();
        birthdate.set(1991, 5,29);
        boolean expected = true;
        boolean result = Utility.isAdult(birthdate.getTime());
        assertEquals("", expected, result);

    }

    @Test
    public void birthdateInFuturExceptionTest() {

        Calendar birthdate = Calendar.getInstance();
        birthdate.set(2500, 0,1);

        assertThrows(IllegalArgumentException.class, () -> Utility.personAge(birthdate.getTime()));

    }
}
