package com.example.android.fitdiary.Day.DietDay;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private float portion;
    private int Kcal;
    private MacroComponents macroComponents;

    public int getKcal() {
        return Kcal;
    }

    public Food() {
        macroComponents = new MacroComponents();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPortion(float portion) {
        this.portion = portion;
    }


    public String getName() {
        return name;
    }

    public float getPortion() {
        return portion;
    }


    public MacroComponents getMacroComponents() {
        return macroComponents;
    }

    public void computeKcal() {
        Kcal = (int) ((macroComponents.getCarbohydrates() * 4 + macroComponents.getProtein() * 4 +
                macroComponents.getFat() * 9)*portion);
    }

    public String toString(){
        computeKcal();
        return name +" " + (int)(portion*100) + "g " + Kcal +" Kcal";
    }

}
