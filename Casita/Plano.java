package Casita;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

import BresenhamsLine.BresenhamsAlg;
import MyMath.Point2;

/**
 *
 * @author htrefftz
 * 
 */
public class Plano 
    extends JPanel
    implements MouseListener {
    
    int width = 500;
    int height = 500;
    Double[][] coordinates;
    Double apexes;
    Line2D.Double linea1;
    
    public Plano(Double[][] x) {
        linea1 = new Line2D.Double();
        this.addMouseListener(this);
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

  public Point2[] makeApexes() {
    int y = this.apexes.intValue();

    Point2[] points = new Point2[y];

    for (int i = 1; i <= apexes; i++) {
        points[i - 1] = new Point2(this.coordinates[i][0], this.coordinates[i][1]);
    }

    return points;
}

public void DrawForm(Graphics2D g) {
  int y = this.apexes.intValue();

  Point2[] point2s = this.makeApexes();

  for (int i = y + 2; i < this.coordinates.length; i++) {

      int pStart = this.coordinates[i][0].intValue();
      int pEnd = this.coordinates[i][1].intValue();

      int x1 = point2s[pStart].getX().intValue();
      int y1 = point2s[pStart].getY().intValue();
      int x2 = point2s[pEnd].getX().intValue();
      int y2 = point2s[pEnd].getY().intValue();

      BresenhamsAlg bresenhams = new BresenhamsAlg();
      bresenhams.drawLine(g, x1, y1, x2, y2, this.width, this.height);

      
  }
}

    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      Graphics2D g2d = (Graphics2D) g;

      // Dibujamos los ejes
      g2d.setColor(Color.RED);
      Line2D.Double ejeY = new Line2D.Double(width/2, height, width/2, 0);
      g2d.draw(ejeY);
      Line2D.Double ejeX = new Line2D.Double(width, height/2, 0, height/2);
      g2d.draw(ejeX);

      this.DrawForm(g2d);
      
  }
  
    @Override 
  public void mouseClicked(MouseEvent e) {}
  
    @Override
  public void mouseEntered(MouseEvent e) {}

    @Override
  public void mouseExited(MouseEvent e) {}

    @Override
  public void mousePressed(MouseEvent e) {
      linea1.x1 = e.getX();
      linea1.y1 = e.getY();
  }

    @Override
  public void mouseReleased(MouseEvent e) {
      linea1.x2 = e.getX();
      linea1.y2 = e.getY();    
      repaint();
  }
}
