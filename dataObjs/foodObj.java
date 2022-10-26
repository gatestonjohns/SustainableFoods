package dataObjs;

public class foodObj {
    private String name;
    private float pricePerPound;
    private int protein, fat, carb;
    private float emissionsPer1kCals, landUsePer1kCals, waterUsePer1kCals;

    public foodObj(String name, float ppp, int protein, int fat, int carb, float emission, float landUse, float waterUse) {
        this.name = name;
        this.pricePerPound = ppp;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
        this.emissionsPer1kCals = emission;
        this.landUsePer1kCals = landUse;
        this.waterUsePer1kCals = waterUse;
    }

    public String getName() {
        return name;
    }
    public float getPricePerPound() {
        return pricePerPound;
    }
    public int getProtein() {
        return protein;
    }
    public int getFat() {
        return fat;
    }
    public int getCarb() {
        return carb;
    }
    public float getEmissionsPer1kCals() {
        return emissionsPer1kCals;
    }
    public float getLandUsePer1kCals() {
        return landUsePer1kCals;
    }
    public float getWaterUsePer1kCals() {
        return waterUsePer1kCals;
    }
}
