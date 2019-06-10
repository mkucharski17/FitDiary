package com.example.android.fitdiary.Day.DietDay.Models;

import java.io.Serializable;
/*
* Food - model class represents one comestible product
 * */
public class Food implements Serializable {
    private String name;
    private float portion;
    private int Kcal;
    private MacroComponents macroComponents;

    public Food() {
        macroComponents = new MacroComponents();
    }

    public int getKcal() {
        return Kcal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPortion() {
        return portion;
    }

    public void setPortion(float portion) {
        this.portion = portion;
    }

    public MacroComponents getMacroComponents() {
        return macroComponents;
    }


    /*
    * computing kcal from  using Components and portion
    * */
    public void computeKcal() {
        Kcal = (int) ((macroComponents.getCarbohydrates() * 4 + macroComponents.getProtein() * 4 +
                macroComponents.getFat() * 9) * portion);
    }


    /*
    * toString method created in order to being used by  arrayAdapter
    * */
    public String toString() {
        computeKcal();
        return name + " " + (int) (portion * 100) + "g " + Kcal + " Kcal";
    }

}
