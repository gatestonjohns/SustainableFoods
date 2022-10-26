package dataObjs;

public class macroPriorityObj {
    public int protein;
    public int carb;
    public int fat;
    public float normProtein;
    public float normCarb;
    public float normFat;

    public macroPriorityObj(int p, int c, int f) {
        this.protein = p;
        this.carb = c;
        this.fat = f;

        // set normalized values
        float totSum = (float) (p + c + f);
        this.normProtein = p / totSum;
        this.normCarb = c / totSum;
        this.normFat = f / totSum;
    }
}
