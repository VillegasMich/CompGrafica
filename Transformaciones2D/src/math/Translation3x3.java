package math;

public class Translation3x3 extends Matrix3x3 {
    
    public Translation3x3(double tx, double ty) {
        super();
        matrix[0][2] = tx;
        matrix[1][2] = ty;
    }
}
