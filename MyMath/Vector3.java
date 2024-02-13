package MyMath;

import java.text.DecimalFormat;

public class Vector3 {
  public double x, y, z;
  private static final DecimalFormat df = new DecimalFormat("0.00");
  public Vector3(double x, double y, double z){
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public double magnitude() {
    double mag = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    return mag;
  }

  public void normalize() {
    double mag = this.magnitude();

    this.x = this.x / mag;
    this.y = this.y / mag;
    this.z = this.z / mag;
  }

  public Vector3 normalizeWithReturn() {
    double mag = this.magnitude();
    
    x = this.x / mag;
    y = this.y / mag;
    z = this.z / mag;
    
    Vector3 normalized = new Vector3(x, y, z);
    return normalized;
  }

  public static Vector3 crossProduct(Vector3 v1, Vector3 v2) {

    double x = (v1.y * v2.z) - (v1.z * v2.y);
    double y = (v1.z * v2.y) - (v1.x * v2.z); 
    double z = (v1.x * v2.y) - (v1.y * v2.x);
    
    Vector3 crossVec = new Vector3(x, y, z);
    return crossVec;
  }

  public static double dotProduct(Vector3 v1, Vector3 v2) {
    double result = (v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z);
    return result;
  }

  @Override
  public String toString() {
    return "X: " + df.format(this.x) + " Y: " + df.format(this.y) + " Z: " + df.format(this.z);
  }
}
