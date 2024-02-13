package MyMath;

import java.text.DecimalFormat;

public class Vector4 {
  public double x, y, z, w;
  private static final DecimalFormat df = new DecimalFormat("0.00");
  public Vector4(double x, double y, double z, double w){
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }
  
  public double magnitude() {
    double mag = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2) + Math.pow(this.w, 2));
    return mag;
  }

  public void normalize() {
    double mag = this.magnitude();

    this.x = this.x / mag;
    this.y = this.y / mag;
    this.z = this.z / mag;
    this.w = this.w / mag;
  }

  public Vector4 normalizeWithReturn() {
    double mag = this.magnitude();
    
    x = this.x / mag;
    y = this.y / mag;
    z = this.z / mag;
    w = this.w / mag;
    
    Vector4 normalized = new Vector4(x, y, z, w);
    return normalized;
  }

  public static double dotProduct(Vector4 v1, Vector4 v2) {
    double result = (v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z) + (v1.w * v2.w);
    return result;
  }

  @Override
  public String toString() {
    return "X: " + df.format(this.x) + " Y: " + df.format(this.y) + " Z: " + df.format(this.z) + " W: " + df.format(this.w);
  }
}
