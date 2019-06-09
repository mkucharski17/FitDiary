package com.example.android.fitdiary.Day.DietDay.Models;

public class MacroComponents {

    // values per 100g
    private float carbohydrates;
    private float protein;
    private float fat;

    public MacroComponents(float carbohydrates, float protein, float fat) {
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
    }

    public MacroComponents() {
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

}
