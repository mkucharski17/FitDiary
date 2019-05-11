package com.example.android.fitdiary;

public class Food {
    private String name;
    private float portion;
    private int Kcal;
    private MacroComponents macroComponents;


    public Food(String name, float portion, MacroComponents macroComponents) {
        this.name = name;
        this.portion = portion;
        computeeKcal(macroComponents);
    }

    public String getName() {
        return name;
    }

    public float getPortion() {
        return portion;
    }

    public int getKcal() {
        return Kcal;
    }

    public MacroComponents getMacroComponents() {
        return macroComponents;
    }

    private void computeeKcal(MacroComponents macroComponents) {
        Kcal = (int) ((macroComponents.getCarbohydrates() * 4 + macroComponents.getProtein() * 4 +
                macroComponents.getFat() * 9)*portion);
    }

}
