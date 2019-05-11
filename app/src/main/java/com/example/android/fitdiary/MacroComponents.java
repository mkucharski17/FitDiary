package com.example.android.fitdiary;

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
