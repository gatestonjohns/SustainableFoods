import dataObjs.ecoPriorityObj;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import dataObjs.foodObj;
import dataObjs.macroPriorityObj;

public class backendProcessing {
    private List<foodObj> totalFoodList;

    public backendProcessing(String csvFilename) {
        // copy in the stuff from eco foods.
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

        // we now have all food objects that user eats, so lets grade and find a suggestion!
        String gradeWithSuggestion = "";




        return gradeWithSuggestion;
    }
}
