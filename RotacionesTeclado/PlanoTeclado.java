package RotacionesTeclado;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

import BresenhamsLine.BresenhamsAlg;
import MyMath.Matrix3;
import MyMath.Vector3;
import MyMath.Point3;

public class PlanoTeclado
    extends JPanel
    implements KeyListener {

  boolean rigth = false;
  boolean left = false;
  boolean up = true;
  boolean down = false;
  int width = 500;
  int height = 500;
  Point3[] point3s;
  Double[][] coordinates;
  Double apexes;
  Line2D.Double linea1;

  public PlanoTeclado(Double[][] x) {
    linea1 = new Line2D.Double();
    this.addKeyListener(this);
    this.setFocusable(true);
    this.requestFocusInWindow();
    this.coordinates = x;
    this.apexes = coordinates[0][0];
  }

  public void printCoords() {
    for (int i = 0; i < this.coordinates.length; i++) {
      if (this.coordinates[i].length >= 2) {
        System.out.println(this.coordinates[i][0] + " " + this.coordinates[i][1]);
      } else {
        System.out.println(this.coordinates[i][0]);
      }
    }
  }

  public Point3[] makeApexes() {
    int y = this.apexes.intValue();

    this.point3s = new Point3[y];

    for (int i = 1; i <= apexes; i++) {
      this.point3s[i - 1] = new Point3(this.coordinates[i][0], this.coordinates[i][1], 1);
    }

    return point3s;
  }

  public void DrawForm(Graphics2D g) {
    int y = this.apexes.intValue();

    if (this.point3s == null) {
      this.point3s = this.makeApexes();
    }

    for (int i = y + 2; i < this.coordinates.length; i++) {

      int pStart = this.coordinates[i][0].intValue();
      int pEnd = this.coordinates[i][1].intValue();

      int x1 = this.point3s[pStart].getX().intValue();
      int y1 = this.point3s[pStart].getY().intValue();
      int x2 = this.point3s[pEnd].getX().intValue();
      int y2 = this.point3s[pEnd].getY().intValue();

      BresenhamsAlg bresenhams = new BresenhamsAlg();
      g.setColor(Color.magenta);
      bresenhams.drawLine(g, x1, y1, x2, y2, this.width, this.height);
    }
  }

  public Matrix3 createMoveMatrix(int flag, double x, double y) {
    Matrix3 idMatrix;
    if (flag == 0) { // Transposición
      idMatrix = new Matrix3(new Vector3(1, 0, x), new Vector3(0, 1, y), new Vector3(0, 0, 1));
    } else if (flag == 1) { // Escalamiento
      idMatrix = new Matrix3(new Vector3(x, 0, 0), new Vector3(0, y, 0), new Vector3(0, 0, 1));
    } else if (flag == 2) { // Rotar izquierda
      double THETA = Math.PI / 28;
      idMatrix = new Matrix3(new Vector3(Math.cos(THETA), -Math.sin(THETA), 0),
          new Vector3(Math.sin(THETA), Math.cos(THETA), 0), new Vector3(0, 0, 1));
    } else if (flag == 3) { // Rotar derecha
      final double THETA = -Math.PI / 28;
      idMatrix = new Matrix3(new Vector3(Math.cos(THETA), -Math.sin(THETA), 0),
          new Vector3(Math.sin(THETA), Math.cos(THETA), 0), new Vector3(0, 0, 1));
    } else if (flag == 4) { // 90 anti clockwise
      idMatrix = new Matrix3(new Vector3(0, -1, 0),
          new Vector3(1, 0, 0), new Vector3(0, 0, 1));
    } else if (flag == 5) { // 90 clockwise
      final double THETA = Math.toRadians(-90);
      idMatrix = new Matrix3(new Vector3(Math.cos(THETA), -Math.sin(THETA), 0),
          new Vector3(Math.sin(THETA), Math.cos(THETA), 0), new Vector3(0, 0, 1));
    } else if (flag == 6) { // Reflection Y
      idMatrix = new Matrix3(new Vector3(-1, 0, 0),
          new Vector3(0, 1, 0), new Vector3(0, 0, -1));
    } else { // Reflection x
      idMatrix = new Matrix3(new Vector3(1, 0, 0),
          new Vector3(0, -1, 0), new Vector3(0, 0, 1));
    }
    return idMatrix;
  }

  public Point3 multiplyMatrix(Point3 point, Matrix3 mat) {
    Point3 newPoint = Matrix3.times(mat, point);
    return newPoint;
  }

  public Point3[] multiplyAllPoints(Point3[] points, Matrix3 mat) {
    for (int i = 0; i < points.length; i++) {
      points[i] = multiplyMatrix(points[i], mat);
    }
    return points;
  }

  public Point3[] multiplyAllPointsOwnAxis(Point3[] points, Matrix3 mat) {

    Point3 ogP1 = new Point3(points[0].getX(), points[0].getY(), 1);

    for (int i = 0; i < points.length; i++) {
      points[i].setX(points[i].getX() - ogP1.getX());
      points[i].setY(points[i].getY() - ogP1.getY());
    }
    for (int i = 0; i < points.length; i++) {
      points[i] = multiplyMatrix(points[i], mat);
    }
    for (int i = 0; i < points.length; i++) {
      points[i].setX(points[i].getX() + ogP1.getX());
      points[i].setY(points[i].getY() + ogP1.getY());
    }
    return points;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    this.DrawForm(g2d);

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int tecla = e.getKeyCode();

    System.out.println("Key pressed " + tecla);
    if (tecla == KeyEvent.VK_UP) {
      Matrix3 mat = createMoveMatrix(0, 0, 10);
      multiplyAllPoints(this.point3s, mat);
    } else if (tecla == KeyEvent.VK_DOWN) {
      Matrix3 mat = createMoveMatrix(0, 0, -10);
      multiplyAllPoints(this.point3s, mat);
    } else if (tecla == KeyEvent.VK_RIGHT) {
      Matrix3 mat = createMoveMatrix(0, 10, 0);
      multiplyAllPoints(this.point3s, mat);
    } else if (tecla == KeyEvent.VK_LEFT) {
      Matrix3 mat = createMoveMatrix(0, -10, 0);
      multiplyAllPoints(this.point3s, mat);
    } else if (tecla == KeyEvent.VK_U) {
      Matrix3 mat = createMoveMatrix(1, 1.2, 1.2);
      multiplyAllPoints(this.point3s, mat);
    } else if (tecla == KeyEvent.VK_T) {
      Matrix3 mat = createMoveMatrix(1, 0.8, 0.8);
      multiplyAllPoints(this.point3s, mat);
    } else if (tecla == KeyEvent.VK_L) {
      Matrix3 mat = createMoveMatrix(2, 0, 0);
      multiplyAllPoints(this.point3s, mat);
    } else if (tecla == KeyEvent.VK_R) {
      Matrix3 mat = createMoveMatrix(3, 0, 0);
      multiplyAllPoints(this.point3s, mat);
    } else if (tecla == KeyEvent.VK_O) {
      Matrix3 mat = createMoveMatrix(3, 0, 0);
      multiplyAllPointsOwnAxis(this.point3s, mat);
    } else if (tecla == KeyEvent.VK_P) {
      Matrix3 mat = createMoveMatrix(2, 0, 0);
      multiplyAllPointsOwnAxis(this.point3s, mat);
    } else if (tecla == KeyEvent.VK_D) {
      if (this.up == true) {
        Matrix3 mat = createMoveMatrix(5, 0, 0);
        multiplyAllPointsOwnAxis(this.point3s, mat);
        this.down = false;
        this.left = false;
        this.rigth = true;
        this.up = false;
        Matrix3 matDes = createMoveMatrix(0, 10, 0);
        multiplyAllPoints(this.point3s, matDes);
      } else if (this.down == true) {
        Matrix3 mat = createMoveMatrix(4, 0, 0);
        multiplyAllPointsOwnAxis(this.point3s, mat);
        this.down = false;
        this.left = false;
        this.rigth = true;
        this.up = false;
        Matrix3 matDes = createMoveMatrix(0, 10, 0);
        multiplyAllPoints(this.point3s, matDes);
      } else if (this.left == true) {
        Matrix3 mat = createMoveMatrix(6, 0, 0);
        multiplyAllPointsOwnAxis(this.point3s, mat);
        this.down = false;
        this.left = false;
        this.rigth = true;
        this.up = false;
        Matrix3 matDes = createMoveMatrix(0, -10, 0);
        multiplyAllPoints(this.point3s, matDes);
      } else {
        Matrix3 matDes = createMoveMatrix(0, 10, 0);
        multiplyAllPoints(this.point3s, matDes);
      }
    } else if (tecla == KeyEvent.VK_A) {
      if (this.up == true) {
        Matrix3 mat = createMoveMatrix(4, 0, 0);
        multiplyAllPointsOwnAxis(this.point3s, mat);
        this.down = false;
        this.left = true;
        this.rigth = false;
        this.up = false;
        Matrix3 matDes = createMoveMatrix(0, -10, 0);
        multiplyAllPoints(this.point3s, matDes);
      } else if (this.down == true) {
        Matrix3 mat = createMoveMatrix(5, 0, 0);
        multiplyAllPointsOwnAxis(this.point3s, mat);
        this.down = false;
        this.left = true;
        this.rigth = false;
        this.up = false;
        Matrix3 matDes = createMoveMatrix(0, -10, 0);
        multiplyAllPoints(this.point3s, matDes);
      } else if (this.rigth == true) {
        Matrix3 mat = createMoveMatrix(6, 0, 0);
        multiplyAllPointsOwnAxis(this.point3s, mat);
        this.down = false;
        this.left = true;
        this.rigth = false;
        this.up = false;
        Matrix3 matDes = createMoveMatrix(0, 10, 0);
        multiplyAllPoints(this.point3s, matDes);
      } else {
        Matrix3 matDes = createMoveMatrix(0, -10, 0);
        multiplyAllPoints(this.point3s, matDes);
      }
    } else if (tecla == KeyEvent.VK_S) {
      if (this.down == false) {
        Matrix3 mat = createMoveMatrix(6, 0, 0);
        multiplyAllPointsOwnAxis(this.point3s, mat);
        this.down = true;
        this.left = false;
        this.rigth = false;
        this.up = false;
      }
      Matrix3 matDes = createMoveMatrix(0, 0, -10);
      multiplyAllPoints(this.point3s, matDes);
    } else if (tecla == KeyEvent.VK_W) {
      if (this.up == false) {
        Matrix3 mat = createMoveMatrix(7, 0, 0);
        multiplyAllPointsOwnAxis(this.point3s, mat);
        this.down = false;
        this.left = false;
        this.rigth = false;
        this.up = true;
      }
      Matrix3 matDes = createMoveMatrix(0, 0, 10);
      multiplyAllPoints(this.point3s, matDes);
    }
    repaint();
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }
}
