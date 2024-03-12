package math;

public class Scaling3x3 extends Matrix3x3 {
    
    public Scaling3x3(double sx, double sy) {
        super();
        matrix[0][0] = sx;
        matrix[1][1] = sy;
    }
}
