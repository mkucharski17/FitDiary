package com.example.android.fitdiary;

import com.example.android.fitdiary.Day.TrainingDay.Models.Exercise;
import com.example.android.fitdiary.Day.TrainingDay.Models.TrainingDay;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;


public class TrainingDayTest {
    private TrainingDay day;

    @Before
    public void setUp(){
        day = new TrainingDay();
    }

    @Test
    public void toStringTest(){
        Exercise e = new Exercise();
        e.setName("push up");
        e.setReps(2);
        e.setWeight(25);
        e.setSets(4);
        assertEquals("push up" + "  " + 4+ "x" + 2 + "  " + 25 + "kg",e.toString());
    }
    
}
