package MyMath;

public class Matrix3 {
  public Vector3 top;
  public Vector3 mid;
  public Vector3 bot;
  
  public Matrix3(Vector3 top, Vector3 mid, Vector3 bot) {
    this.top = top;
    this.mid = mid;
    this.bot = bot;
  }

  public Matrix3(double a, double b, double c, double d, double e, double f, double g, double h, double i) {
    this.top = new Vector3(a, b, c);
    this.mid = new Vector3(d, e, f);
    this.bot = new Vector3(g, h, i);
  }

  public static Point3 times(Matrix3 matrix, Point3 point){
    double x = (matrix.top.x * point.x) + (matrix.top.y * point.y) + (matrix.top.z * point.z);
    double y = (matrix.mid.x * point.x) + (matrix.mid.y * point.y) + (matrix.mid.z * point.z);
    double z = (matrix.bot.x * point.x) + (matrix.bot.y * point.y) + (matrix.bot.z * point.z);
    Point3 result = new Point3(x, y, z);
    return result;
  }

  public static Matrix3 times(Matrix3 matrix1, Matrix3 matrix2) {
    // top row
    double a = (matrix1.top.x * matrix2.top.x) + (matrix1.top.y * matrix2.mid.x) + (matrix1.top.z * matrix2.bot.x);
    double b = (matrix1.top.x * matrix2.top.y) + (matrix1.top.y * matrix2.mid.y) + (matrix1.top.z * matrix2.bot.y);
    double c = (matrix1.top.x * matrix2.top.z) + (matrix1.top.y * matrix2.mid.z) + (matrix1.top.z * matrix2.bot.z);
    // mid row
    double d = (matrix1.mid.x * matrix2.top.x) + (matrix1.mid.y * matrix2.mid.x) + (matrix1.mid.z * matrix2.bot.x);
    double e = (matrix1.mid.x * matrix2.top.y) + (matrix1.mid.y * matrix2.mid.y) + (matrix1.mid.z * matrix2.bot.y);
    double f = (matrix1.mid.x * matrix2.top.z) + (matrix1.mid.y * matrix2.mid.z) + (matrix1.mid.z * matrix2.bot.z);
    // bot row
    double g = (matrix1.bot.x * matrix2.top.x) + (matrix1.bot.y * matrix2.mid.x) + (matrix1.bot.z * matrix2.bot.x);
    double h = (matrix1.bot.x * matrix2.top.y) + (matrix1.bot.y * matrix2.mid.y) + (matrix1.bot.z * matrix2.bot.y);
    double i = (matrix1.bot.x * matrix2.top.z) + (matrix1.bot.y * matrix2.mid.z) + (matrix1.bot.z * matrix2.bot.z);
    Matrix3 result = new Matrix3(a, b, c, d, e, f, g, h, i);
    return result;
  }

  @Override
  public String toString() {
    return "--------------------------\n" + this.top + "\n" + this.mid + "\n" + this.bot + "\n--------------------------";
  }
}
