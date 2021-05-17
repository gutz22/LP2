package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Rect extends Figure {

    public Rect (int x, int y, int w, int h, Color corContorno, Color corFundo) {
        super(x,y, w,h, corFundo);
        this.corContorno = corContorno;
    }

    public void drawBorder(Graphics g, Color corBorda) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(corBorda);
        g2d.drawRect(this.x-2,this.y-2, this.w+4,this.h+4);
      }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corContorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);
        g2d.setColor(this.corFundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}
