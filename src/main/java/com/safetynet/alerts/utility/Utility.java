package com.safetynet.alerts.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.System.currentTimeMillis;

public class Utility {

    @Generated
    public static <T> T jsonDecode(String json, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, tClass);
        } catch (Exception ex) {
            return null;
        }
    }


    public static String jsonEncode(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    public static int personAge(Date date){


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar now = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(date);

        if (dob.after(now)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }

        int year1 = now.get(Calendar.YEAR);
        int year2 = dob.get(Calendar.YEAR);

        int age = year1 - year2;

        int month1 = now.get(Calendar.MONTH);
        int month2 = dob.get(Calendar.MONTH);

        if (month2 > month1) {
            age--;
        } else if (month1 == month2) {
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            int day2 = dob.get(Calendar.DAY_OF_MONTH);

            if (day2 > day1) {
                age--;
            }
        }

        return age;
    }

    public static boolean isAdult(Date birthdate) {

        Calendar dateAdult = Calendar.getInstance();
        dateAdult.add(Calendar.YEAR, -18);

        Calendar birthdateCalendar = Calendar.getInstance();
        birthdateCalendar.setTime(birthdate);

        if (birthdateCalendar.equals(dateAdult)) {
            return true;
        } else if (birthdateCalendar.before(dateAdult)) {
            return true;
        } else if (birthdateCalendar.after(dateAdult)) {
            return false;
        } else {
            return false;
        }
    }

}
