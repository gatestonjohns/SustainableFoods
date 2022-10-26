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
    private int rows=101;

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
        for (int i = 0; i < 10; i++) {
            scn.next();
        }

        //Iterate over CSV rows and store food objects
        while (totalFoodList.size() < 101 && scn.hasNextLine()) {
            String name = scn.next();
            double emission = Double.parseDouble(scn.next());
            double land = Double.parseDouble(scn.next());
            double waterWithdrawal = Double.parseDouble(scn.next());
            double price = Double.parseDouble(scn.next());
            int fat = Integer.parseInt(scn.next());
            int protein = Integer.parseInt(scn.next());
            int fiber = Integer.parseInt(scn.next());
            int carb = Integer.parseInt(scn.next());
            double priceNormalized = Integer.parseInt(scn.next());

            totalFoodList.add(new foodObj(name, emission, land, waterWithdrawal, price, fat, protein, fiber, carb, priceNormalized));

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
            sumEmission += f.getEmissionsPer1kCals();
            sumLand += f.getLandUsePer1kCals();
            sumWater += f.getWaterUsePer1kCals();
        }

        avgEmission = sumEmission / rows;
        avgLand = sumLand / rows;
        avgWater = sumWater / rows;
    }


    public void getIdealFoods(int moneyPriority, ecoPriorityObj ecoPs, macroPriorityObj macPs) {
        // here is where we do all ranking and return the ideal list of foods

        //WILL NOT BE VOID -- CHANGE RETURN TYPE!!!
    }

    public String gradeWithSuggestions(List<String> inputFoods) {
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
        String gradeWithSuggestion = "";



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
