package display;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import geometry.PolygonObject;
import geometry.ObjectTransformation;

import java.awt.Color;
import java.awt.Graphics;

public class Main extends JPanel
    implements KeyListener {

  static final int WIDTH = 800;
  static final int HEIGHT = 600;

  Graphics g;

  PolygonObject po;

  public Main() {
    setFocusable(true);
    requestFocusInWindow();
    addKeyListener(this);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    this.g = g;

    g.setColor(Color.RED);
    drawOneLine(-100, 0, 100, 0);

    g.setColor(Color.GREEN);
    drawOneLine(0, -100, 0, 100);

    g.setColor(Color.BLACK);
    po.draw();

  }

  public void drawOneLine(int x1, int y1, int x2, int y2) {
    x1 = x1 + WIDTH / 2;
    y1 = HEIGHT / 2 - y1;
    x2 = x2 + WIDTH / 2;
    y2 = HEIGHT / 2 - y2;
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_D) {
      // po.resetVertices();
      po.ot.dx += ObjectTransformation.DELTA_TRANSL;
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_A) {
      // po.resetVertices();
      po.ot.dx -= ObjectTransformation.DELTA_TRANSL;
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_W) {
      // po.resetVertices();
      po.ot.dy += ObjectTransformation.DELTA_TRANSL;
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_S) {
      // po.resetVertices();
      po.ot.dy -= ObjectTransformation.DELTA_TRANSL;
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_Q) {
      // po.resetVertices();
      po.ot.sx += ObjectTransformation.DELTA_SCAL;
      po.ot.sy += ObjectTransformation.DELTA_SCAL;
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_E) {
      // po.resetVertices();
      po.ot.sx -= ObjectTransformation.DELTA_SCAL;
      po.ot.sy -= ObjectTransformation.DELTA_SCAL;
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_R) {
      // po.resetVertices();
      po.ot.theta += ObjectTransformation.DELTA_ROT;
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_F) {
      // po.resetVertices();
      po.ot.theta -= ObjectTransformation.DELTA_ROT;
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_Z) {
      po.resetVertices();
      repaint();
    } else if (key == KeyEvent.VK_UP) {
      po.ot.dy += ObjectTransformation.DELTA_TRANSL * Math.cos(po.ot.theta);
      po.ot.dx -= ObjectTransformation.DELTA_TRANSL * Math.sin(po.ot.theta);
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_RIGHT) {
      // po.resetVertices();
      po.ot.theta -= ObjectTransformation.DELTA_ROT;
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_DOWN) {
      po.ot.dy -= ObjectTransformation.DELTA_TRANSL * Math.cos(po.ot.theta);
      po.ot.dx += ObjectTransformation.DELTA_TRANSL * Math.sin(po.ot.theta);
      po.transformObject();
      repaint();
    } else if (key == KeyEvent.VK_LEFT) {
      po.ot.theta += ObjectTransformation.DELTA_ROT;
      po.transformObject();
      repaint();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  public static void main(String[] args) {
    // Crear un nuevo Frame (Ventana)
    JFrame frame = new JFrame("Transformaciones 2D");
    // Al cerrar el frame, termina la ejecución de este programa
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Agregar un JPanel que se llama Main (esta clase)
    Main main = new Main();
    // Create a PolygonObject
    main.po = new PolygonObject();
    // Reading takes a long time. Read the file before adding the
    // JPanel to the JFrame.
    main.po.readObject("../casita2D.txt");
    main.po.setCanvas(main);
    // En true para que el objeto rote y se escale en torno a sí mismo
    math.TranslScalRot3x3.CENTER_TRANFORMS = true;
    // Agregar el JPanel al frame
    frame.add(main);
    // Asignarle tamaño
    frame.setSize(WIDTH, HEIGHT);
    // Poner el frame en el centro de la pantalla
    frame.setLocationRelativeTo(null);
    // Mostrar el frame
    frame.setVisible(true);
  }

}
