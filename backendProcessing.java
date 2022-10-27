import java.util.*;
import java.io.*;

import dataObjs.ecoPriorityObj;
import dataObjs.foodObj;
import dataObjs.macroPriorityObj;

public class backendProcessing {
    private List<foodObj> totalFoodList = new ArrayList<foodObj>();

    private float avgEmission=0;
    private float avgLand=0;
    private float avgWater=0;
    private int rows=99;

    public backendProcessing(String csvFilename) {
        try {
            ReadFile(csvFilename);
            averages();
        } catch (Exception e)
        {
            System.out.println("Error reading file.");
        }
    }

    private void ReadFile(String csvFilename) throws IOException {

        File file = new File(csvFilename);
        Scanner scn = new Scanner(file);

        scn.useDelimiter(",|\\n");

        //Skip the CSV Headers
        for (int i = 0; i < 17; i++) {
           scn.next();
        }

        //Iterate over CSV rows and store food objects
        while (totalFoodList.size() < 99 && scn.hasNextLine()) {
            String name = scn.next();
            double normEmission = Double.parseDouble(scn.next());
            double normLand = Double.parseDouble(scn.next());
            double normwaterWithdrawal = Double.parseDouble(scn.next());
            double normFat = Double.parseDouble(scn.next());
            double normProtein = Double.parseDouble(scn.next());
            double normFiber = Double.parseDouble(scn.next());
            double normCarbs = Double.parseDouble(scn.next());
            double emission = Double.parseDouble(scn.next());
            double land = Double.parseDouble(scn.next());
            double waterWithdrawal = Double.parseDouble(scn.next());
            double price = Double.parseDouble(scn.next());
            int fat = Integer.parseInt(scn.next());
            int protein = Integer.parseInt(scn.next());
            int fiber = Integer.parseInt(scn.next());
            int carb = Integer.parseInt(scn.next());
            double priceNormalized = Double.parseDouble(scn.next());

            totalFoodList.add(new foodObj(name, normEmission, normLand, normwaterWithdrawal, normFat, normProtein, normFiber, normCarbs, emission, land, waterWithdrawal, price, fat, protein, fiber, carb, priceNormalized));

        }

        //Sort according to most sustainable
        Collections.sort(totalFoodList, new FoodComparator());

        //Verify stored foods
        for (foodObj f : totalFoodList) {
            System.out.println(f.tostring());
        }
        scn.close();
    }

    private void averages()
    {  
        float sumEmission = 0;
        float sumLand = 0;
        float sumWater = 0;

        for (foodObj f : totalFoodList)
        {
            sumEmission += f.normEmission;
            sumLand += f.normLand;
            sumWater += f.normwaterWithdrawal;
        }

        avgEmission = sumEmission / rows;
        avgLand = sumLand / rows;
        avgWater = sumWater / rows;
    }


    public void getIdealFoods(int moneyPriority, ecoPriorityObj ecoPs, macroPriorityObj macPs) {
        // here is where we do all ranking and return the ideal list of foods

        //WILL NOT BE VOID -- CHANGE RETURN TYPE!!!
    }

    public double[] gradeWithSuggestions(List<String> inputFoods, double[] ranks) {
        // parameters: List<String> is the list of the names of foods that the user selects
        // double[] ranks is the values based on slider in the app.
        double totalrank = ranks[0] + ranks[1] + ranks[2];
        ranks[0] = ranks[0]/totalrank;
        ranks[1] = ranks[1]/totalrank;
        ranks[2] = ranks[2]/totalrank;


        //System.out.println(totalFoodList.get(0).getName());
        double[] gradeWithSuggestion = new double[inputFoods.size()];
        int doublecount = 0;
        int inputfoodscount = 0;
        for (int i = 0; i < totalFoodList.size(); i++) {
            //System.out.println(i);
            if((totalFoodList.get(i).getName().equals(inputFoods.get(inputfoodscount)))){
                double temp1 = totalFoodList.get(i).normEmission - avgEmission;
                double temp2 = totalFoodList.get(i).normLand - avgLand;
                double temp3 = totalFoodList.get(i).normwaterWithdrawal - avgWater;
                gradeWithSuggestion[doublecount] = temp1 * Math.pow((1.0 - ranks[0]), -1) + temp2 * Math.pow((1.0 - ranks[1]), -1) + temp3 * Math.pow((1.0 - ranks[2]), -1);
                doublecount++;
                inputfoodscount++;
                i = 0;
                if (doublecount == inputFoods.size()) {
                    break;
                }
            }
        }
        return gradeWithSuggestion;
    }

    public ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (foodObj f: totalFoodList)
        {
            names.add(f.getName());
        }

        return names;
    }
}

/*
 * Class used for organizing foods in foodlist based on their ecoScore
 */
class FoodComparator implements Comparator<foodObj> {
    @Override
    public int compare(foodObj a, foodObj b)
    {
        return (a.getEcoScore() > b.getEcoScore()) ? -1 : (a.getEcoScore() == b.getEcoScore()) ?  0 : 1;
    }
}
