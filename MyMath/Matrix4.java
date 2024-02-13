package MyMath;

public class Matrix4 {
  public Vector4 top;
  public Vector4 mid1;
  public Vector4 mid2;
  public Vector4 bot;
  
  public Matrix4(Vector4 top, Vector4 mid1, Vector4 mid2, Vector4 bot) {
    this.top = top;
    this.mid1 = mid1;
    this.mid2 = mid2;
    this.bot = bot;
  }

  public Matrix4(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k, double l, double m, double n, double o, double p) {
    this.top = new Vector4(a, b, c, d);
    this.mid1 = new Vector4(e, f, g, h);
    this.mid2 = new Vector4(i, j, k, l);
    this.bot = new Vector4(m, n, o, p);
  }

  public static Point4 times(Matrix4 matrix, Point4 point){
    double x = (matrix.top.x * point.x) + (matrix.top.y * point.y) + (matrix.top.z * point.z) + (matrix.top.w * point.w);
    double y = (matrix.mid1.x * point.x) + (matrix.mid1.y * point.y) + (matrix.mid1.z * point.z) + (matrix.mid1.w * point.w);
    double z = (matrix.mid2.x * point.x) + (matrix.mid2.y * point.y) + (matrix.mid2.z * point.z) + (matrix.mid2.w * point.w);
    double w = (matrix.bot.x * point.x) + (matrix.bot.y * point.y) + (matrix.bot.z * point.z) + (matrix.bot.w * point.w);
    Point4 result = new Point4(x, y, z, w);
    return result;
  }

  // public static Matrix4 times(Matrix4 matrix1, Matrix4 matrix2) {
  //   // top row
  //   double a = (matrix1.top.x * matrix2.top.x) + (matrix1.top.y * matrix2.mid1.x) + (matrix1.top.z * matrix2.mid2.x) + (matrix1.top.w * matrix2.bot.x);
  //   double b = (matrix1.top.x * matrix2.top.y) + (matrix1.top.y * matrix2.mid1.y) + (matrix1.top.z * matrix2.mid2.y) + (matrix1.mid1.w * matrix2.bot.y);
  //   double c = (matrix1.top.x * matrix2.top.z) + (matrix1.top.y * matrix2.mid1.z) + (matrix1.top.z * matrix2.mid2.z) + (matrix1.mid2.w * matrix2.bot.z);
  //   double d = (matrix1.top.x * matrix2.top.w) + (matrix1.top.y * matrix2.mid1.w) + (matrix1.top.z * matrix2.mid2.w) + (matrix1.bot.w * matrix2.bot.w);
  //   // mid1 row
  //   double e = (matrix1.mid1.x * matrix2.top.x) + (matrix1.mid1.y * matrix2.mid1.x) + (matrix1.mid1.z * matrix2.mid2.x) + (matrix1.mid1.w * matrix2.bot.x);
  //   double f = (matrix1.mid1.x * matrix2.top.y) + (matrix1.mid1.y * matrix2.mid1.y) + (matrix1.mid1.z * matrix2.mid2.y) + (matrix1.mid1.w * matrix2.bot.y);
  //   double g = (matrix1.mid1.x * matrix2.top.z) + (matrix1.mid1.y * matrix2.mid1.z) + (matrix1.mid1.z * matrix2.mid2.z) + (matrix1.mid1.w * matrix2.bot.z);
  //   double h = (matrix1.mid1.x * matrix2.top.w) + (matrix1.mid1.y * matrix2.mid1.w) + (matrix1.mid1.z * matrix2.mid2.w) + (matrix1.mid1.w * matrix2.bot.w);
  //   // mid2 row
  //   double i = (matrix1.mid2.x * matrix2.top.x) + (matrix1.mid2.y * matrix2.mid.x) + (matrix1.mid2.z * matrix2.mid2.x) + ;
  //   double j = (matrix1.mid2.x * matrix2.top.y) + (matrix1.mid2.y * matrix2.mid.y) + (matrix1.mid2.z * matrix2.mid2.y);
  //   double k = (matrix1.mid2.x * matrix2.top.z) + (matrix1.mid2.y * matrix2.mid.z) + (matrix1.mid2.z * matrix2.mid2.z);
  //   double l = (matrix1.mid2.x * matrix2.top.z) + (matrix1.mid2.y * matrix2.mid.z) + (matrix1.mid2.z * matrix2.mid2.z);
  //   // bot row
  //   double i = (matrix1.bot.x * matrix2.top.x) + (matrix1.bot.y * matrix2.mid.x) + (matrix1.bot.z * matrix2.bot.x);
  //   double j = (matrix1.bot.x * matrix2.top.y) + (matrix1.bot.y * matrix2.mid.y) + (matrix1.bot.z * matrix2.bot.y);
  //   double k = (matrix1.bot.x * matrix2.top.z) + (matrix1.bot.y * matrix2.mid.z) + (matrix1.bot.z * matrix2.bot.z);
  //   double l = (matrix1.bot.x * matrix2.top.z) + (matrix1.bot.y * matrix2.mid.z) + (matrix1.bot.z * matrix2.bot.z);
    
  //   Matrix3 result = new Matrix3(a, b, c, d, e, f, g, h, i);
  //   return result;
  // }

  @Override
  public String toString() {
    return "--------------------------\n" + this.top + "\n" + this.mid1 + "\n" + this.mid2 + "\n" + this.bot + "\n--------------------------";
  }
}
