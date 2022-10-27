package dataObjs;

public class foodObj {
    //Values from CSV
    private String name;
    private double emissionsPer1kCals, landUsePer1kCals, waterUsePer1kCals;
    private double pricePerPound, normalizedPrice;
    private int fat, protein, fiber, carb;
    public double normEmission;
    public double normLand;
    public double normwaterWithdrawal;
    double normFat;
    double normProtein;
    double normFiber;
    double normCarbs;

    //Calculated values
    private double summationScore, ecoScore;

    public foodObj(String name, double normEmission, double normLand, double normwaterWithdrawal, double normFat,
                   double normProtein, double normFiber, double normCarbs, double emissionsPer1kCals, double landUsePer1kCals,
                   double waterUsePer1kCals, double pricePerPound,
                   int fat, int protein, int fiber, int carb, double normalizedPrice)
    {
        this.name = name;
        this.emissionsPer1kCals = emissionsPer1kCals;
        this.landUsePer1kCals = landUsePer1kCals;
        this.waterUsePer1kCals = waterUsePer1kCals;
        this.pricePerPound = pricePerPound;
        this.fat = fat;
        this.protein = protein;
        this.fiber = fiber;
        this.carb = carb;
        this.normalizedPrice = normalizedPrice;

        this.summationScore = summationScore();
        this.ecoScore = 100-this.summationScore;

        this.normEmission = normEmission;
        this.normLand = normLand;
        this.normwaterWithdrawal = normwaterWithdrawal;
        this.normFat = normFat;
        this.normProtein = normProtein;
        this.normFiber = normFiber;
        this.normCarbs = normCarbs;


    }

    //calculate basic ecoScore. kinda shitty lol
    private double summationScore()
    {
        return 0.07169*7*emissionsPer1kCals + 0.05656*4*landUsePer1kCals + 0.00202*waterUsePer1kCals;
    }

    //temporary tostring funct
    public String tostring()
    {
        return name + " " + ecoScore;
    }

    ///
    /// Getters for private variables
    ///
    public String getName() {
        return name;
    }
    public double getEmissionsPer1kCals() {
        return emissionsPer1kCals;
    }
    public double getLandUsePer1kCals() {
        return landUsePer1kCals;
    }
    public double getWaterUsePer1kCals() {
        return waterUsePer1kCals;
    }
    public double getPricePerPound() {
        return pricePerPound;
    }
    public double getNormalizedPrice() {
        return normalizedPrice;
    }
    public int getFat() {
        return fat;
    }
    public int getProtein() {
        return protein;
    }
    public int getFiber() {
        return fiber;
    }
    public int getCarb() {
        return carb;
    }
    public double getSummationScore() {
        return summationScore;
    }
    public double getEcoScore() {
        return ecoScore;
    }
}
