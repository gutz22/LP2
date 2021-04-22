package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.geom.*;

public class LineSegment extends Figure {

    public LineSegment (int x,int y, int w,int h, Color corFundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.corFundo = corFundo;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.drawLine(x, y, x+w, y+h);
    }
}
