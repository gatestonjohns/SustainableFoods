import java.io.*;
import java.util.*;

public class EcoFoods {

    static ArrayList<Food> foods = new ArrayList<Food>();

    public static void ReadFile() throws IOException {

        //for intellij, use "src/testRepo/SustainableFoods/food-footprints.csv"
        File file = new File("food-footprints.csv");

        Scanner scn = new Scanner(file);
        scn.useDelimiter(",|\\n");

        int count = 0;

        //Skip the CSV Headers
        for (int i = 0; i < 8; i++) {
            scn.next();
        }

        //Iterate over CSV rows and store food objects
        while (foods.size() < 99 && scn.hasNextLine()) {
            String name = scn.next();
            double emission = Double.parseDouble(scn.next());
            double land = Double.parseDouble(scn.next());
            double eutrophication = Double.parseDouble(scn.next());
            double waterScarcity = Double.parseDouble(scn.next());
            double waterWithdrawal = Double.parseDouble(scn.next());
            double biodiversity = Double.parseDouble(scn.next());
            double price = Double.parseDouble(scn.next());

            foods.add(new Food(name, emission, land, eutrophication, waterScarcity, waterWithdrawal, biodiversity, price));
        }

        //Sort according to most sustainable
        Collections.sort(foods, new FoodComparator());

        //Verify stored foods
        for (Food f : foods) {
            System.out.println(f.tostring());
        }
    }
}

class FoodComparator implements Comparator<Food> {
    @Override
    public int compare(Food a, Food b)
    {
        return (a.inverted > b.inverted) ? -1 : (a.inverted == b.inverted) ?  0 : 1;
    }
}

class Food {
    String name;
    double emissions;
    double land;
    double eutrophication;
    double waterScarcity;
    double waterWithdrawal;
    double biodiversity;

    double totalscore;
    double inverted;

    double price;

    Food()
    {}

    //food constructor
    Food(String name, double emissions, double land,
         double eutrophication, double waterScarcity, double waterWithdrawal,
         double biodiversity, double price)
    {
        this.name = name;
        this.emissions = emissions;
        this.land = land;
        this.eutrophication = eutrophication;
        this.waterScarcity = waterScarcity;
        this.waterWithdrawal = waterWithdrawal;
        this.biodiversity = biodiversity;
        this.price = price;

        totalscore = ecoScore(emissions, land,
                eutrophication, waterScarcity, waterWithdrawal,
                biodiversity);

        inverted = 100 - totalscore;
    }

    //calculate basic ecoScore. kinda shitty lol
    double ecoScore(double emissions, double land,
                    double eutrophication, double waterScarcity, double waterWithdrawal,
                    double biodiversity)
    {
        return 0.07169*7*emissions + 0.05656*4*land + 0.01796*eutrophication
                + 0.000056986*3*waterScarcity + 0.00202*waterWithdrawal
                + 0.00064*biodiversity;
    }

    //temporary tostring funct
    String tostring()
    {
        return name + " " + inverted;
    }

}
