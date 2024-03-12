package geometry;

import math.TranslScalRot3x3;

public class ObjectTransformation {
    public double dx;
    public double dy;
    public double sx;
    public double sy;
    public double theta;

    public static final double DELTA_TRANSL = 10;
    public static final double DELTA_SCAL = 0.1;
    public static final double DELTA_ROT = 10 * Math.PI / 180;

    public ObjectTransformation() {
        dx = 0;
        dy = 0;
        sx = 1;
        sy = 1;
        theta = 0;
    }

    public TranslScalRot3x3 createTransformation() {
        TranslScalRot3x3 tsr = new TranslScalRot3x3(dx, dy, sx, sy, theta);
        return tsr;
    }

    public void reset() {
        dx = 0;
        dy = 0;
        sx = 1;
        sy = 1;
        theta = 0;  
    }

}