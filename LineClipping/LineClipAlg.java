package LineClipping;

import java.awt.Color;
import java.awt.Graphics;

public class LineClipAlg {

  LineClipAlg() {}

  final int INSIDE = 0;
  final int LEFT = 1;
  final int RIGHT = 2;
  final int BOTTOM = 4;
  final int TOP = 8;

  final int xmax = 200;
  final int xmin = -200;
  final int ymax = 100;
  final int ymin = -100;

  int ComputeOutCOde(int x, int y) {
    int code = INSIDE;
    
    if (x < this.xmin) {
      code |= LEFT; // to the left of clip window
    } else if (x > this.xmax) {
      code |= RIGHT;         // to the right of clip window
    }      
	  
	  if (y < this.ymin) {
      code |= BOTTOM; // below the clip window
    } else if (y > this.ymax) {
        code |= TOP; // above the clip window
    }

	  return code;
  }
  
  public boolean CohenSutherlandLineClip(Graphics g, int x0, int y0, int x1, int y1) {
    BresenhamsAlg bresenhamsAlg = new BresenhamsAlg(500, 500);
    boolean flag = false;
    int outCode0 = ComputeOutCOde(x0, y0);
    int outCode1 = ComputeOutCOde(x1, y1);

    while (true) {
      if ((outCode0 == 0) && (outCode1 == 0)) {
        // bitwise OR is 0: both points inside window; trivially accept and exit loop
        flag = true;
        System.out.println("The two points are inside the rectangle");
        g.setColor(Color.GREEN);
        bresenhamsAlg.drawLine(g, x0, y0, x1, y1, 500, 500);
        break;
      } else if ((outCode0 & outCode1) != 0) {
        // If both endpoints are outside rectangle, in same region
        System.out.println("The two points are outside the rectangle");
        g.setColor(Color.BLACK);
        bresenhamsAlg.drawLine(g, x0, y0, x1, y1, 500, 500);
        break;
      } else {
          int x = 0, y = 0;
          int outCodeOut;
          boolean endPointInside;

          if (outCode0 != 0) {
            outCodeOut = outCode0;
            endPointInside = true;
          } else {
            endPointInside = false;
            outCodeOut = outCode1;
          }

          g.setColor(Color.RED);

          if ((outCodeOut & TOP) != 0) {           // point is above the clip window
            x = x0 + (x1 - x0) * (ymax - y0) / (y1 - y0);
            y = ymax;
            if (endPointInside) {
              bresenhamsAlg.drawLine(g, x0, y0, x, y, 500, 500);
            } else {
              bresenhamsAlg.drawLine(g, x, y, x1, y1, 500, 500);
            }
            
          } else if ((outCodeOut & BOTTOM) != 0) { // point is below the clip window
            x = x0 + (x1 - x0) * (ymin - y0) / (y1 - y0);
            y = ymin;
            if (endPointInside) {
              bresenhamsAlg.drawLine(g, x0, y0, x, y, 500, 500);
            } else {
              bresenhamsAlg.drawLine(g, x, y, x1, y1, 500, 500);
            }
          } else if ((outCodeOut & RIGHT) != 0) {  // point is to the right of clip window
            y = y0 + (y1 - y0) * (xmax - x0) / (x1 - x0);
            x = xmax;
            if (endPointInside) {
              bresenhamsAlg.drawLine(g, x0, y0, x, y, 500, 500);
            } else {
              bresenhamsAlg.drawLine(g, x, y, x1, y1, 500, 500);
            }
          } else if ((outCodeOut & LEFT) != 0) {   // point is to the left of clip window
            y = y0 + (y1 - y0) * (xmin - x0) / (x1 - x0);
            x = xmin;
            if (endPointInside) {
              bresenhamsAlg.drawLine(g, x0, y0, x, y, 500, 500);
            } else {
              bresenhamsAlg.drawLine(g, x, y, x1, y1, 500, 500);
            }
          }

          if (outCodeOut == outCode0) {
            x0 = x;
            y0 = y;
            outCode0 = this.ComputeOutCOde(x0, y0);
          } else {  
            x1 = x;
            y1 = y;
            outCode1 = this.ComputeOutCOde(x1, y1);
          }
      }
    }

    return flag;
  }
}