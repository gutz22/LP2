package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Rect extends Figure {
    public Rect(int x,int y, int w,int h, Color corFundo,Color corContorno) {
        super(x,y, w,h, corFundo);
        this.corContorno = corContorno;
    }
    @Override
    public void drawBorder(Graphics g, Color corBorda) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(corBorda);
        g2d.drawRect(this.x-2,this.y-2, this.w+4,this.h+4);
      }
    @Override
    public void paint(Graphics g, boolean focused, boolean isFigs) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corContorno);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(this.corFundo);
        g2d.fillRect(this.x+1,this.y+1, this.w-1,this.h-1);
        super.paint(g, focused, isFigs);
    }
}
