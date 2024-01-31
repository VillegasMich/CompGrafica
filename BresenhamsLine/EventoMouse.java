package BresenhamsLine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

/**
 *
 * @author htrefftz
 * 
 */
public class EventoMouse 
    extends JPanel
    implements MouseListener {
    
    int width = 500;
    int height = 500;
    Line2D.Double linea1;
    
    public EventoMouse() {
        linea1 = new Line2D.Double();
        this.addMouseListener(this);
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

      // Mostramos coordenadas en java y cartesianas
      g2d.setColor(Color.BLUE);
      System.out.println("Coordenadas Java");
      System.out.println("X: " + linea1.x1 + " " + linea1.x2);
      System.out.println("Y: " +linea1.y1 + " " + linea1.y2);
      System.out.println("Coordenadas Cartesianas");
      int x1 = (int) (linea1.x1-(width/2));
      int x2 = (int) (linea1.x2-(width/2));
      int y1 = (int) ((height/2) - linea1.y1);
      int y2 = (int) ((height/2) - linea1.y2);
      System.out.println("X: " + x1 + " " + x2);
      System.out.println("Y: " + y1 + " " + y2);
      
      BresenhamsAlg bresenham = new BresenhamsAlg(width, height);
      bresenham.drawLine(g2d, x1, y1, x2, y2, width, height);

      // g2d.setColor(Color.GREEN);
      // g2d.draw(linea1); //! Instead this use Bresenhams algorithm
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
