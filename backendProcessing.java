import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Path;
import java.io.BufferedReader;
import foodObj;

public class backendProcessing {
    private foodObj[] totalFoodList;

    public backendProcessing(String csvFilename) throws FileNotFoundException {
        // here is where we need to read in the CSV and populate the foodObj[] list
    }

    public void getIdealFoods(int moneyPriority, ecoPriorityObj ecoPs, macroPriorityObj macPs) {
        // here is where we do all ranking and return the ideal list of foods

        //WILL NOT BE VOID -- CHANGE RETURN TYPE!!!
    }

    public void gradeWithSuggestions(foodObj[] inputFoods) {
        // here is where we do grading and return the grade and a critique of the diet

        // WILL NOT BE VOID -- CHANGE RETURN TYPE!!!
    }
}
