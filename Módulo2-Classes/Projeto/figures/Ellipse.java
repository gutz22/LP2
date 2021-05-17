package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {

    public Ellipse (int x, int y, int w, int h, Color corContorno, Color corFundo) {
        super(x,y, w,h, corFundo);
        this.corContorno = corContorno;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corContorno);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(this.corFundo);
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
     }
}
