package LineClipping;

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

      int x1 = (int) (linea1.x1-(width/2));
      int x2 = (int) (linea1.x2-(width/2));
      int y1 = (int) ((height/2) - linea1.y1);
      int y2 = (int) ((height/2) - linea1.y2);

      LineClipAlg lineClipping = new LineClipAlg();
      BresenhamsAlg bresenhamsAlg = new BresenhamsAlg(width, height);

      
      g2d.setColor(Color.BLACK);
      bresenhamsAlg.drawLine(g2d, lineClipping.xmin, lineClipping.ymin, lineClipping.xmin, lineClipping.ymax, width, height); // Left
      bresenhamsAlg.drawLine(g2d, lineClipping.xmin, lineClipping.ymin, lineClipping.xmax, lineClipping.ymin, width, height); // bottom
      bresenhamsAlg.drawLine(g2d, lineClipping.xmin, lineClipping.ymax, lineClipping.xmax, lineClipping.ymax, width, height); // top
      bresenhamsAlg.drawLine(g2d, lineClipping.xmax, lineClipping.ymax, lineClipping.xmax, lineClipping.ymin, width, height); // right
      
      lineClipping.CohenSutherlandLineClip(g2d, x1, y1, x2, y2);
      
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

