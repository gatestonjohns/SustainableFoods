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
        // initialize list of foods that user input to empty
        List<foodObj> usersFoods = new ArrayList<>();

        // populate list of user foods
        for (foodObj food : totalFoodList) {
            for (String currInputFood : inputFoods) {
                if (Objects.equals(food.getName(), currInputFood)) {
                    usersFoods.add(food);
                    inputFoods.remove(currInputFood);
                }
            }
        }

        //GRADEEE PLEASE
        // we now have all food objects that user eats, so lets grade and find a suggestion!
        double[] gradeWithSuggestion = new double[inputFoods.size()];
        for (int i = 0; i < inputFoods.size(); i++) {
            double temp1 = usersFoods.get(i).normEmission - avgEmission;
            double temp2 = usersFoods.get(i).normLand - avgLand;
            double temp3 = usersFoods.get(i).normwaterWithdrawal - avgWater;

            gradeWithSuggestion[i] = temp1 * Math.pow((1 - ranks[1]/5.0), -1) + temp2 * Math.pow((1 - ranks[2]/5.0), -1) + temp3 * Math.pow((1 - ranks[3]/5.0), -1);
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
