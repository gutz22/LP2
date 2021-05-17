package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.GeneralPath;

public class Lozenge extends Figure {

    public Lozenge (int x, int y, int w, int h, Color corContorno, Color corFundo) {
        super(x,y, w,h, corFundo);
        this.corContorno = corContorno;
    }
    
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GeneralPath shape = new GeneralPath();
        shape.moveTo(this.x+(this.w)/2, this.y);
        shape.lineTo(this.x+this.w, this.y+(this.h)/2);
        shape.lineTo(this.x+(this.w)/2, this.y+this.h);
        shape.lineTo(this.x, this.y+(this.h)/2);
        shape.lineTo(this.x+(this.w)/2, this.y); 
        shape.closePath();
        g2d.setColor(this.corFundo);
        g2d.fill(shape);
        g2d.setColor(this.corContorno);
        g2d.draw(shape);
    }
}
