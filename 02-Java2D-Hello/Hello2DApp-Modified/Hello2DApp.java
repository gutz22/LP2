import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.GeneralPath;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
        frame.setVisible(true);
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Star \u2728");
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.black);
    }

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.magenta);
        
        g2d.fillRect(0, 0, 350, 60);
        g2d.fillRect(0, 0, 30, 350);
        g2d.fillRect(320, 0, 30, 350);
        g2d.fillRect(0, 320, 350, 30);

        double points[][] = { 
            { 75, 170 }, { 275, 170 }, { 115, 275 }, { 175, 95 }, { 235, 275 },  { 75, 170 }
        };
        GeneralPath star = new GeneralPath();

        star.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++) {
                star.lineTo(points[k][0], points[k][1]);
        };

        star.closePath();
        g2d.draw(star);
    }
}
