import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();
    Color cor[] = { Color.blue, Color.black, Color.cyan, Color.gray, Color.green,
                    Color.magenta, Color.orange, Color.red, Color.yellow };

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = rand.nextInt(350);
                    int y = rand.nextInt(350);
                    int w = rand.nextInt(50);
                    int h = rand.nextInt(50);

                    int contorno = rand.nextInt(8);
                    int fundo = rand.nextInt(8);

                    if (evt.getKeyChar() == 'r') {
                        Rect r = new Rect(x,y, w,h, cor[contorno],cor[fundo]);
                        figs.add(r);
                        r.print();
                    } 
                    else if (evt.getKeyChar() == 'e') {
                        Ellipse e = new Ellipse(x,y, w,h, cor[contorno],cor[fundo]);
                        figs.add(e);
                        e.print();
                    } 
                    else if (evt.getKeyChar() == 'l') {
                        Lozange l = new Lozange(x,y, w,w, cor[contorno], cor[fundo]);
                        figs.add(l);
                        l.print();
                        
                    }
                    repaint();
                }
            }
        );

        this.setTitle("Lista de Figuras");
        this.setSize(350, 350);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}
