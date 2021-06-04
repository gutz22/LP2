package button;

import ivisible.IVisible;
import figures.Figure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Button implements IVisible, Serializable {
    public Figure fig;

    public Button(Figure fig) {
      this.fig = fig;
    }
    
    public void paint(Graphics g, boolean focused) {
      Graphics g2d = (Graphics2D) g;
      fig.paint(g2d,false);
      if (focused) {
        this.fig.drawBorder(g, Color.magenta);
      }
      else {
        this.fig.drawBorder(g, Color.black);
      }
    }

    public void changeBackgroundColor(Color corFundo) {
      this.fig.changeBackgroundColor(corFundo);
    }

    public void changeOutlineColor(Color corContorno) {
      this.fig.changeOutlineColor(corContorno);
  }
  
    public boolean clicked(int posX, int posY) {
      return (((this.fig.x <= posX) && (this.fig.y <= posY)) && ((posX <= (this.fig.x + this.fig.w)) && (posY <= (this.fig.y + this.fig.h))));
    }
}
