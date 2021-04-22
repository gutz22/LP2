package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Rect extends Figure {

    public Rect (int x, int y, int w, int h, Color corContorno, Color corFundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.corContorno = corContorno;
        this.corFundo = corFundo;
    }
    public Rect (int x, int y, int w, int h, Color corContorno) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.corContorno = corContorno;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(this.corContorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);
        g2d.setColor(this.corFundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);
    }

    public void drawLozenge (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corContorno);
        //g2d.rotate(Math.toRadians(90), this.x+this.w/2, this.y+this.h/2);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        //g2d.rotate(Math.toRadians(-90), this.x, this.y);
    }
    public void draw (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corContorno);
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }
}
