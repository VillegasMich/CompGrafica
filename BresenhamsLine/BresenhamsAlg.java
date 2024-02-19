package BresenhamsLine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * @author RossetaCode
 * https://rosettacode.org/wiki/Bitmap/Bresenham%27s_line_algorithm
 */
public class BresenhamsAlg extends JPanel {

  private final int pixelSize = 1;

  public BresenhamsAlg() {
    setPreferredSize(new Dimension());
}

  private void plot(Graphics g, int x, int y, int width, int height) {
      int w = (width - 1) / pixelSize;
      int h = (height - 1) / pixelSize;
      int maxX = (w - 1) / 2;
      int maxY = (h - 1) / 2;

      int borderX = width - ((2 * maxX + 1) * pixelSize + 1);
      int borderY = height - ((2 * maxY + 1) * pixelSize + 1);
      int left = (x + maxX) * pixelSize + borderX / 2;
      int top = (y + maxY) * pixelSize + borderY / 2;

      g.setColor(Color.black);
      g.drawRect(left, top, pixelSize, pixelSize);
  }

  public void drawLine(Graphics g, int x1, int y1, int x2, int y2, int width, int height) {
      // delta of exact value and rounded value of the dependent variable
      int d = 0;

      int dx = Math.abs(x2 - x1);
      int dy = Math.abs(y2 - y1);

      int dx2 = 2 * dx; // slope scaling factors to
      int dy2 = 2 * dy; // avoid floating point

      int ix = x1 < x2 ? 1 : -1; // increment direction
      int iy = y1 < y2 ? 1 : -1;

      int x = x1;
      int y = y1;


      if (dx >= dy) {
          while (true) {
              plot(g, x, y*-1, width, height);
              if (x == x2)
                  break;
              x += ix;
              d += dy2;
              if (d > dx) {
                  y += iy;
                  d -= dx2;
              }
          }
      } else {
          while (true) {
              plot(g, x, y*-1, width, height);
              if (y == y2)
                  break;
              y += iy;
              d += dx2;
              if (d > dy) {
                  x += ix;
                  d -= dy2;
              }
          }
      }
  }
}
