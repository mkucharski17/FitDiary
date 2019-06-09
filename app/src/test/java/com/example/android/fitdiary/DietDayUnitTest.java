package com.example.android.fitdiary;


import com.example.android.fitdiary.Day.DietDay.Models.DietDay;
import com.example.android.fitdiary.Day.DietDay.Models.Food;
import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DietDayUnitTest {
    DietDay day;

    @Before
    public void setup(){
        day = new DietDay();
        Food food = new Food();
        day.addFood(food);
    }
    @Test
    public void nameTest(){
        day.getFood().get(0).setName("bulka");
        assertEquals("bulka", day.getFood().get(0).getName());
    }

    @Test
    public void portionTest(){
        day.getFood().get(0).setPortion(2.5F);
        assertEquals(2.5F,day.getFood().get(0).getPortion());
    }

    @Test
    public void macroTest(){
       day.getFood().get(0).getMacroComponents().setCarbohydrates(2.5F);
       day.getFood().get(0).getMacroComponents().setFat(3.5F);
       day.getFood().get(0).getMacroComponents().setProtein(4.5F);
       day.getFood().get(0).setPortion(2.0F);
       day.getFood().get(0).computeKcal();

       assertEquals(2.5F,day.getFood().get(0).getMacroComponents().getCarbohydrates());
       assertEquals(3.5F,day.getFood().get(0).getMacroComponents().getFat());
       assertEquals(4.5F,day.getFood().get(0).getMacroComponents().getProtein());
       assertEquals(2.0F,day.getFood().get(0).getPortion());

        float kcal = (2.5F*4+3.5F*9+4.5F*4)*2.0F;
        assertEquals((int)kcal,day.getFood().get(0).getKcal());
    }
    
}
