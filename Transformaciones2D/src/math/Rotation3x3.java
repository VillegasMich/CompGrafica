package math;

public class Rotation3x3 extends Matrix3x3 {
    
    public Rotation3x3(double theta) {
        super();
        matrix[0][0] = Math.cos(theta);
        matrix[0][1] = -Math.sin(theta);
        matrix[1][0] = Math.sin(theta);
        matrix[1][1] = Math.cos(theta);
    }
}
