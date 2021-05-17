package figures;
import ivisible.IVisible;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Figure implements IVisible {
    public int x, y;
    public int w, h;
    public Color corContorno, corFundo;
    
    public abstract void paint(Graphics g);

    public Figure(int x, int y, int w, int h, Color corFundo) {
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
      g2d.setColor(Color.black);
      g2d.drawRect(this.x+this.w-7,this.y+this.h-7, 7,7);
      g2d.setColor(lightSalmon);
      g2d.fillRect(this.x+this.w-7,this.y+this.h-7, 7,7);
    }

    public boolean clicked(int posX, int posY) {
      return (((this.x <= posX) && (this.y <= posY)) && ((posX <= (this.x + this.w)) && (posY <= (this.y + this.h))));
    }

    public boolean redimClicked(int posX, int posY) {
      return ((((this.x+this.w-7) <= posX) && ((this.y+this.h-7) <= posY)) && ((posX <= (this.x+this.w-7 + 7)) && (posY <= (this.y+this.h-7 + 7))));
    }

    public void print() {
      System.out.format("Tamanho (%d,%d) / Posição (%d,%d)\n",
      this.w, this.h, this.x, this.y);
    }

}
