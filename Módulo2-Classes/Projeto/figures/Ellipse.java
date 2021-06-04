package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.*;

public class Ellipse extends Figure {
    private Ellipse2D shape;

    public Ellipse (int x,int y, int w,int h, Color corFundo,Color corContorno) {
        super(x,y, w,h, corFundo);
        this.corContorno = corContorno;
    }

    @Override
    public boolean clicked(int posX, int posY) {
        this.shape = new Ellipse2D.Float(this.x,this.y, this.w,this.h);
        return this.shape.contains(posX,posY);
    }

    @Override
    public void paint(Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;    
        g2d.setColor(this.corFundo);
        g2d.fillOval(this.x,this.y, this.w+1,this.h+1);   
        g2d.setColor(this.corContorno);
        g2d.drawOval(this.x,this.y, this.w,this.h);
        super.paint(g, focused);
     }
}
