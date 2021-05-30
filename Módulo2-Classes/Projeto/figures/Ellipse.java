package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Ellipse extends Figure {
    public Ellipse (int x,int y, int w,int h, Color corFundo,Color corContorno) {
        super(x,y, w,h, corFundo);
        this.corContorno = corContorno;
    }
    @Override
    public void paint(Graphics g, boolean focused, boolean isFigs) {
        Graphics2D g2d = (Graphics2D) g;    
        g2d.setColor(this.corFundo);
        g2d.fillOval(this.x,this.y, this.w+1,this.h+1);   
        g2d.setColor(this.corContorno);
        g2d.drawOval(this.x,this.y, this.w,this.h);
        super.paint(g, focused, isFigs);
     }
}
