package figures;
import ivisible.IVisible;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Figure implements IVisible, Serializable {
    public int x, y;
    public int w, h;
    public Color corContorno, corFundo;
    
    public void paint(Graphics g, boolean focused, boolean isFigs) {
      if (isFigs && focused) {
          this.drawBorder(g, Color.red);
          this.drawRedim(g);
      }
      else if ((!isFigs) && (focused)) {
        this.drawBorder(g, Color.magenta);
      }
      else if (!isFigs) {
        this.drawBorder(g, Color.black);
      }
    }

    protected Figure(int x,int y, int w,int h, Color corFundo) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      this.corFundo = corFundo;
    }

    public void changeBackgroundColor(Color corFundo) {
      this.corFundo = corFundo;
    }

    public void changeOutlineColor(Color corContorno) {
      this.corContorno = corContorno;
  }
    public void drawBorder(Graphics g, Color corBorda) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(corBorda);
      g2d.drawRect(this.x,this.y, this.w,this.h);
    }

    public void drawRedim(Graphics g) {
      Color lightSalmon = new Color(255, 160, 122);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(lightSalmon);
      g2d.drawRect(this.x+this.w-7,this.y+this.h-7, 7,7);
      g2d.fillRect(this.x+this.w-7+1,this.y+this.h-7+1, 7-1,7-1);
    }

    public boolean clicked(int posX, int posY) {
      return (((this.x <= posX) && (this.y <= posY)) && ((posX <= (this.x + this.w)) && (posY <= (this.y + this.h))));
    }

    public boolean redimClicked(int posX, int posY) {
      return ((((this.x+this.w-7) <= posX) && ((this.y+this.h-7) <= posY)) && ((posX <= (this.x+this.w-7 + 7)) && (posY <= (this.y+this.h-7 + 7))));
    }
}
