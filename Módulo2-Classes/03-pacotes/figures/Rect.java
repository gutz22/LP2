package figures;

import java.awt.*;

public class Rect {
    private int x, y; 
    private int w, h;
    private Color corContorno;
    private Color corFundo;

    public Rect (int x, int y, int w, int h, Color corContorno, Color corFundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.corContorno = corContorno;
        this.corFundo = corFundo;
    }
    private void print() {
		System.out.format("Tamanho (%d,%d) / Posição (%d,%d)\n",
		this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(this.corContorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);
        g2d.setColor(this.corFundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}
