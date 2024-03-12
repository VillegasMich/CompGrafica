package math;

public class Vector3 {
    public double [] vector = new double[3];

    public Vector3() {
        vector[0] = 0;
        vector[1] = 0;
        vector[2] = 0;
    }

    public Vector3(double x, double y) {
        vector[0] = x;
        vector[1] = y;
        vector[2] = 1;
    }

    public String toString() {
        return "(" + vector[0] + ", " + vector[1] + ")";
    }
}
