package math;

public class Test {
    public static void main(String [] args) {
        Vector3 v1 = new Vector3(100, 200);
        Translation3x3 t1 = new Translation3x3(50, 60);
        Vector3 v2 = Matrix3x3.times(t1, v1);
        System.out.println("Translation: " + v2);

        Vector3 v3 = new Vector3(100, 100);
        Rotation3x3 r1 = new Rotation3x3(90.0 * Math.PI / 180.0);
        Vector3 v4 = Matrix3x3.times(r1, v3);
        System.out.println("Rotation: " + v4);

        Vector3 v5 = new Vector3(100, 100);
        Scaling3x3 s1 = new Scaling3x3(2, 2);
        Vector3 v6 = Matrix3x3.times(s1, v5);
        System.out.println("Scaling" + v6);
    }
}
