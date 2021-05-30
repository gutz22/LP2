package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.geom.GeneralPath;

public class Menu {
	private int x; int y;
	public String op;
	private String font;
	private boolean isPlain;
	private int size;
	private Color cor;
	private int pointerx; float pointery;
	private int pointerw = 8; int pointerh = 8;
	
	public Menu(int x, int y, String op, String font, boolean isPlain, int size, Color cor) {
		this.x = x;
		this.y = y;
		this.op = op;
		this.font = font;
		this.isPlain = isPlain;
		this.size = size;
		this.cor = cor;
	}
	
	public void paint(Graphics g, boolean menuFocus) {
		Font font;
		Graphics2D g2d = (Graphics2D) g;
		font = new Font(this.font, this.isPlain ? Font.PLAIN: Font.BOLD, this.size);
		g2d.setColor(this.cor);
		g2d.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);	
		if ((this.x == 0) || (this.op == "Continuar")) {
			this.x = (350 - metrics.stringWidth(this.op)) / 2;	
		}
		g2d.drawString(this.op, this.x, this.y+metrics.getAscent());
		if (menuFocus) {
			this.pointerx = this.x - 20;
			this.pointery = (this.y+(metrics.getAscent()/2) - 3) ;
			GeneralPath shape = new GeneralPath();
			shape.moveTo(this.pointerx, this.pointery);
			shape.lineTo(this.pointerx+this.pointerw, this.pointery+(this.pointerh)/2);
			shape.lineTo(this.pointerx, this.pointery+this.pointerh);
			shape.closePath();
			g2d.setColor(Color.white);
			g2d.fill(shape);
			g2d.draw(shape);
		}
	}
}
