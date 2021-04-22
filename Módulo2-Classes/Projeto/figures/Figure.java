package figures;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figure {
    public int x, y;
    public int w, h;
    public Color corContorno, corFundo;
    
    public abstract void paint (Graphics g);

    public void changeBackgroundColor(Color corFundo) {
        this.corFundo = corFundo;
    }

    public void changeOutlineColor(Color corContorno) {
      this.corContorno = corContorno;
  }

    public void print() {
		System.out.format("Tamanho (%d,%d) / Posição (%d,%d)\n",
		this.w, this.h, this.x, this.y);
    }

}
