package com.example.android.fitdiary;

import com.example.android.fitdiary.Day.Models.Day;
import com.example.android.fitdiary.Day.Models.DaysComparator;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class DayUnitTest {
    private Day day;

    @Before
    public void setUp() {
        Date date = new Date(2019 - 1900, 2, 17);
        day = new Day(date);
    }

    @Test
    public void toStringTest() {
        assertEquals("2019-03-17", day.toString());
    }

    @Test
    public void compareTest() {
        DaysComparator com = new DaysComparator();
        Date date = new Date(2019 - 1900, 2, 17);
        Day day2 = new Day(date);
        assertEquals(0, com.compare(day, day2));
    }
}
