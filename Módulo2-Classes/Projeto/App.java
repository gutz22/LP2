import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.geom.*;

import figures.*;

class App {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}   

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Figure focus = null;
    Point pos = null;
    Color lightSalmon = new Color(255, 160, 122);
    Rect redim = new Rect(0, 0, 7, 7, Color.black, lightSalmon);
    Rect rediml = new Rect(0, 0, 7, 7, Color.black, lightSalmon);
    Rect border = null; Rect borderl = null;
    boolean redimClick = false;
    boolean isLozenge = false;
    int dx, dy;

    Random rand = new Random();

    
    Color aqua = new Color(0, 255, 255);
    Color crimson = new Color(220, 0, 60);  
    Color gold = new Color(255, 215, 0);
    Color purple = new Color(128, 0, 128);
    Color forestGreen = new Color(34, 139, 24);
    Color plum = new Color(221, 160, 221);
    Color darkOrange = new Color(255, 140, 0);
    Color silver = new Color(192, 192, 192);
    Color saddleBrown = new Color(139, 69, 19);
    Color cor[] = { aqua, crimson, gold, purple, forestGreen,
                    plum, darkOrange, silver, saddleBrown };
    
   
    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        
        this.addMouseListener(
            new MouseAdapter() {
                public void mousePressed (MouseEvent me) {
                    pos = getMousePosition();
                    focus = null;
                    boolean insideFocus = true;
                    isLozenge = false;
                    if (insideFocus) {
                        insideFocus = false;
                        for (Figure fig: figs) {
                            if (fig.getClass().getSimpleName().equals("Lozenge")) {
                                if (((fig.x-9 <= pos.x) && (fig.y-9 <= pos.y)) && 
                                    ((pos.x < (fig.x-9 + fig.w+18)) && (pos.y < (fig.y-9 + fig.h+18)))) {
                                    focus = fig;
                                    insideFocus = true;
                                    redimClick = false;
                                    figs.remove(focus);
                                    figs.add(focus);
                                    dx = focus.x-9 - pos.x;
                                    dy = focus.y-9 - pos.y;
                                    break;
                                }
                            }
                            else if ( ((fig.x <= pos.x) && (fig.y <= pos.y)) && ((pos.x <= (fig.x + fig.w)) && (pos.y <= (fig.y + fig.h ))) ) {
                                focus = fig;
                                insideFocus = true;
                                redimClick = false;
                                figs.remove(focus);
                                figs.add(focus);
                                dx = focus.x - pos.x;
                                dy = focus.y - pos.y;
                                break;
                            }
                            repaint();
                        }  
                    }    
                    if ( (insideFocus) && ( ((redim.x <= pos.x) && (redim.y <= pos.y)) && ((pos.x <= (redim.x + redim.w)) && (pos.y  <= (redim.y + redim.h)))) ) {
                        redimClick = true;
                        dx = redim.x - pos.x;
                        dy = redim.y - pos.y;
                    }
                    if ( (insideFocus) && ( ((rediml.x <= pos.x) && (rediml.y <= pos.y)) && ((pos.x <= (rediml.x + rediml.w)) && (pos.y  <= (rediml.y + rediml.h)))) ) {
                        redimClick = true;
                        isLozenge = true;
                        dx = rediml.x - pos.x;
                        dy = rediml.y - pos.y;
                    }
                    if (!(insideFocus)) {
                        focus = null;
                    }
                    repaint();
                }   
            }
        );

        this.addMouseMotionListener(
            new MouseMotionAdapter() {
                public void mouseDragged (MouseEvent me) {
                    pos = getMousePosition();
                    int stretchx; int stretchy;
                    if (redimClick) {
                        if (isLozenge) {
                            stretchx = rediml.x - pos.x;
                            stretchy = rediml.y - pos.y;
                            figs.remove(focus);
                            figs.add(focus);
                            focus.w += dx - stretchx;
                            focus.h += dx - stretchy;
                        }
                        else {
                            stretchx = redim.x - pos.x;
                            stretchy = redim.y - pos.y;
                            figs.remove(focus);
                            figs.add(focus);
                            focus.w += dx - stretchx;
                            focus.h += dy - stretchy;
                        }
                    }
                    else if (focus != null) {
                        figs.remove(focus);
                        figs.add(focus);
                        focus.x = pos.x + dx;
                        focus.y = pos.y + dy;
                    }
                   repaint();
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent ke) {
                    pos = getMousePosition();
                    int xa = pos.x;
                    int ya = pos.y;
                    int w = rand.nextInt(30) + 20;
                    int h = rand.nextInt(30) + 20;
            
                    int contorno = rand.nextInt(9);
                    int fundo = rand.nextInt(9);
                    
                    if (ke.getKeyChar() == 'r') {
                        figs.add(new Rect(xa,ya, w,h, cor[contorno],cor[fundo]));
                        focus = figs.get(figs.size()-1);
                    } 
                    else if (ke.getKeyChar() == 'e') {
                        figs.add(new Ellipse(xa,ya, w,h, cor[contorno],cor[fundo]));
                        focus = figs.get(figs.size()-1);
                    } 
                    else if (ke.getKeyChar() == 'l') {
                        figs.add(new Lozenge(xa,ya, w, w, cor[contorno],cor[fundo]));
                        focus = figs.get(figs.size()-1);
                    }
                    else if (ke.getKeyChar() == 's') {
                        figs.add(new LineSegment(xa,ya, w,h, cor[fundo]));
                        focus = figs.get(figs.size()-1);
                    }
                    
                    if ((ke.getKeyChar() == 127) || (ke.getKeyChar() == 'd')){
                        figs.remove(focus);
                        focus = null;
                    }

                    if (focus != null) {
                        if (ke.getKeyChar() == '0') {
                            focus.changeColor(cor[0]);
                        }
                        else if (ke.getKeyChar() == '1') {
                            focus.changeColor(cor[1]);
                        }
                        else if (ke.getKeyChar() == '2') {
                            focus.changeColor(cor[2]);
                        }
                        else if (ke.getKeyChar() == '3') {
                            focus.changeColor(cor[3]);
                        }
                        else if (ke.getKeyChar() == '4') {
                            focus.changeColor(cor[4]);
                        }
                        else if (ke.getKeyChar() == '5') {
                            focus.changeColor(cor[5]);
                        }
                        else if (ke.getKeyChar() == '6') {
                            focus.changeColor(cor[6]);
                        }
                        else if (ke.getKeyChar() == '7') {
                            focus.changeColor(cor[7]);
                        }
                        else if (ke.getKeyChar() == '8') {
                            focus.changeColor(cor[8]);
                        }
                    }
                    repaint();
                }
            }
        );
        this.setTitle("Projeto parte 1 - Utilizar as teclas '0' à '8' para mudança de cor");
        this.setSize(350, 350);
        setLocationRelativeTo(null);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }   
        if (focus != null) {
            if (focus.getClass().getSimpleName().equals("Lozenge")) {
                Graphics2D g2d = (Graphics2D) g;
                borderl = new Rect(focus.x-9,focus.y-9, focus.w+18,focus.h+18, Color.red);
                borderl.drawLozenge(g);
                
                rediml.x = borderl.x + borderl.w-6;
                rediml.y = borderl.y + borderl.h-6;
                rediml.paint(g);
            }
            else {
                border = new Rect(focus.x-2,focus.y-2, focus.w+4,focus.h+4, Color.red);
                border.draw(g);
                redim.x = border.x + border.w - 8;
                redim.y = border.y + border.h - 8;
            
                redim.paint(g);
            }
        }
    }
}
