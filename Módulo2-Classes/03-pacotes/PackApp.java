import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.*;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    Rect r1, r2, r3;
    Ellipse e1, e2, e3;
    Losango l1;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(350, 350);
        this.r1 = new Rect(75, 120, 180, 80, Color.green, Color.blue);
        this.r2 = new Rect(75, 120, 190, 90, Color.pink, Color.red);
        this.r3 = new Rect(75, 120, 200, 100, Color.yellow, Color.black);

        Color olive = new Color(128, 128, 0);
        this.e1 = new Ellipse(100,130, 130,60, Color.blue, olive);

        Color teal = new Color(0, 128, 128);
        this.e2 = new Ellipse(100,130, 120,40, Color.red, teal);

        Color purple = new Color(128, 0, 128);
        this.e3 = new Ellipse(100,130, 100,30, Color.green, purple);
        Color darkOrange = new Color(255, 140, 0);
        this.l1 = new Losango(135,-110, 220,220, Color.gray, darkOrange);

    }
    public void paint (Graphics g) {
        super.paint(g);
        this.l1.paint(g);

        this.r3.paint(g);
        this.r2.paint(g);
        this.r1.paint(g);

        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
    }
}
