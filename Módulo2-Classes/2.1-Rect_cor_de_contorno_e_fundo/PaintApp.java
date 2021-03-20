import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;


class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1, r2, r3;

    PaintFrame() {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);
        this.r1 = new Rect(75, 120, 180, 80, Color.green, Color.blue);
        this.r2 = new Rect(75, 120, 190, 90, Color.pink, Color.red);
        this.r3 = new Rect(75, 120, 200, 100, Color.yellow, Color.black);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r3.paint(g);
        this.r2.paint(g);
        this.r1.paint(g);
    }
}

class Rect {
    int x, y; 
    int w, h;
    Color corContorno;
    Color corFundo;

    Rect (int x, int y, int w, int h, Color corContorno, Color corFundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.corContorno = corContorno;
        this.corFundo = corFundo;
    }
    void print() {
		System.out.format("Tamanho (%d,%d) / Posição (%d,%d)\n",
		this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.corContorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);
        g2d.setColor(this.corFundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}
