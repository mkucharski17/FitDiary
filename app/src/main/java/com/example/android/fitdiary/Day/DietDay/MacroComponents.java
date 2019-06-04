package com.example.android.fitdiary.Day.DietDay;

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

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }


    public float getProtein() {
        return protein;
    }

    public float getFat() {
        return fat;
    }

}
