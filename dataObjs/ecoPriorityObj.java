package dataObjs;

public class ecoPriorityObj {
    public int emission;
    public int waterUse;
    public int landUse;
    public float normEmission;
    public float normWaterUse;
    public float normLandUse;

    public ecoPriorityObj(int e, int w, int l) {
        this.emission = e;
        this.waterUse = w;
        this.landUse = l;

        // set normalized values
        float totSum = (float) (e + w + l);
        this.normEmission = e / totSum;
        this.normWaterUse = w / totSum;
        this.normLandUse = l / totSum;
    }
}
