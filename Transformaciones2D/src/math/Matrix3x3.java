package math;

public class Matrix3x3 {
    public double [][] matrix = new double[3][3];

    public Matrix3x3() {
        matrix[0][0] = 1;
        matrix[0][1] = 0;
        matrix[0][2] = 0;

        matrix[1][0] = 0;
        matrix[1][1] = 1;
        matrix[1][2] = 0;

        matrix[2][0] = 0;
        matrix[2][1] = 0;
        matrix[2][2] = 1;
    }

    void set(int i, int j, double value) {
        matrix[i][j] = value;
    }

    public static Matrix3x3 times(Matrix3x3 m1, Matrix3x3 m2) {
        Matrix3x3 m3 = new Matrix3x3();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j ++) {
                m3.matrix[i][j] = 0;
                for(int k = 0; k < 3; k++) {
                    m3.matrix[i][j] += m1.matrix[i][k] * m2.matrix[k][j];
                }
            }
        }
        return m3;
    }

    public static Vector3 times(Matrix3x3 m, Vector3 v) {
        Vector3 v2 = new Vector3();
        for(int i = 0; i < 3; i++) {
            v2.vector[i] = 0;
            for(int j = 0; j < 3; j++) {
                v2.vector[i] += m.matrix[i][j] * v.vector[j];
            }
        }
        return v2;
    }
}
