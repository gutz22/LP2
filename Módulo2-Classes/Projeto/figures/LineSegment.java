package figures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class LineSegment extends Figure {
    public LineSegment(int x,int y, int w,int h, Color corFundo) {
        super(x,y, w,h, corFundo);
    }

    @Override
    public boolean clicked(int posX, int posY) {
        return (((this.x <= posX) && (this.y <= posY)) && ((posX <= (this.x + this.w)) && (posY <= (this.y + this.h))));
    }

    @Override
    public void paint(Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corFundo);
        g2d.drawLine(this.x,this.y, this.x+this.w,this.y+this.h);
        super.paint(g, focused);
    }
}
