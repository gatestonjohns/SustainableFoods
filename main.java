import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        File file = new File("food-footprintsKCAL.csv");

        Scanner scn = new Scanner(file);
        scn.useDelimiter(",|\\n");

        int count = 0;
        for (int i = 0; i < 7; i++) {
            scn.next();
        }

        ArrayList<Food> foods = new ArrayList<Food>();

        while (foods.size() < 211 && scn.hasNextLine()) {
            String name = scn.next();
            double emission = Double.parseDouble(scn.next());
            double land = Double.parseDouble(scn.next());
            double eutrophication = Double.parseDouble(scn.next());
            double waterScarcity = Double.parseDouble(scn.next());
            double waterWithdrawal = Double.parseDouble(scn.next());
            double biodiversity = Double.parseDouble(scn.next());

            foods.add(new Food(name, emission, land, eutrophication, waterScarcity, waterWithdrawal, biodiversity));
        }

        Collections.sort(foods, new FoodComparator());

        //remove tea and coffee after rankings.
        {
            foods.remove(foods.size()-1);
            foods.remove(foods.size()-1);
            foods.remove(foods.size()-1);
            foods.remove(foods.size()-1);
        }

        for (Food f : foods)
        {
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

    Food()
    {}

    Food(String name, double emissions, double land,
         double eutrophication, double waterScarcity, double waterWithdrawal,
         double biodiversity)
    {
        this.name = name;
        this.emissions = emissions;
        this.land = land;
        this.eutrophication = eutrophication;
        this.waterScarcity = waterScarcity;
        this.waterWithdrawal = waterWithdrawal;
        this.biodiversity = biodiversity;

        totalscore = ecoScore(emissions, land,
        eutrophication, waterScarcity, waterWithdrawal,
        biodiversity);

        inverted = 100 - totalscore;
    }

    double ecoScore(double emissions, double land,
                 double eutrophication, double waterScarcity, double waterWithdrawal,
                 double biodiversity)
    {
        return 0.07169*7*emissions + 0.05656*4*land + 0.01796*eutrophication
                + 0.000056986*3*waterScarcity + 0.00202*waterWithdrawal
                + 0.00064*biodiversity;
    }

    String tostring()
    {
        return name + " " + inverted;
    }

}