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
// Para a para mudança de cor nas figuras, pressione 'c' para troca de contorno e 'f' para troca de fundo, com as teclas de 0 a 8 representando uma cor específica."
class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Figure focus = null;
    Point pos = null;
    
    Rect border = null; Rect borderl = null;
    boolean redimClick = false;
    int dx, dy;
    int dxR,dyR;
    int redimX, redimY;
    boolean changeOutline = false; boolean changeBackground = false;

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
                    redimClick = false;
                    boolean insideFocus = false;
                    for (Figure fig: figs) {
                        if (fig.clicked(pos.x, pos.y)) {
                            focus = fig;
                            insideFocus = true;
                            figs.remove(focus);
                            figs.add(focus);
                            dx = focus.x - pos.x;
                            dy = focus.y - pos.y;
                            if (focus.redimClicked(pos.x, pos.y)) {
                                redimClick = true;
                                redimX = focus.x + focus.w - 7;
                                redimY = focus.y + focus.h - 7;
                                dxR = redimX - pos.x;
                                dyR = redimY - pos.y;
                            }
                            break;
                        }
                    }  
                    if (!insideFocus) {
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
                        figs.remove(focus);
                        figs.add(focus);
                        stretchx = redimX - pos.x;
                        stretchy = redimY - pos.y;
                        focus.w += dxR - stretchx;
                        focus.h += dyR - stretchy;
                        dxR = stretchx;
                        dyR = stretchy;
                    }
                    else if (focus != null) {
                        redimClick = false;
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
                        figs.add(new Lozenge(xa,ya, w, h, cor[contorno],cor[fundo]));
                        focus = figs.get(figs.size()-1);
                    }
                    else if (ke.getKeyChar() == 's') {
                        figs.add(new LineSegment(xa,ya, w,h, cor[fundo]));
                        focus = figs.get(figs.size()-1);
                    }
                    if (ke.getKeyChar() == 'f') {
                        changeBackground = true;
                        changeOutline = false;
                    }
                    else if (ke.getKeyChar() == 'c') {
                        changeOutline = true;
                        changeBackground = false;
                    }
                    if ((ke.getKeyChar() == 127) || (ke.getKeyChar() == 'd')){
                        figs.remove(focus);
                        focus = null;
                    }

                    if ((focus != null) && (changeBackground)) {
                        if (ke.getKeyChar() == '0') {
                            focus.changeBackgroundColor(cor[0]);
                        }
                        else if (ke.getKeyChar() == '1') {
                            focus.changeBackgroundColor(cor[1]);
                        }
                        else if (ke.getKeyChar() == '2') {
                            focus.changeBackgroundColor(cor[2]);
                        }
                        else if (ke.getKeyChar() == '3') {
                            focus.changeBackgroundColor(cor[3]);
                        }
                        else if (ke.getKeyChar() == '4') {
                            focus.changeBackgroundColor(cor[4]);
                        }
                        else if (ke.getKeyChar() == '5') {
                            focus.changeBackgroundColor(cor[5]);
                        }
                        else if (ke.getKeyChar() == '6') {
                            focus.changeBackgroundColor(cor[6]);
                        }
                        else if (ke.getKeyChar() == '7') {
                            focus.changeBackgroundColor(cor[7]);
                        }
                        else if (ke.getKeyChar() == '8') {
                            focus.changeBackgroundColor(cor[8]);
                        }
                    }

                    else if ((focus != null) && (changeOutline)) {
                        if (ke.getKeyChar() == '0') {
                            focus.changeOutlineColor(cor[0]);
                        }
                        else if (ke.getKeyChar() == '1') {
                            focus.changeOutlineColor(cor[1]);
                        }
                        else if (ke.getKeyChar() == '2') {
                            focus.changeOutlineColor(cor[2]);
                        }
                        else if (ke.getKeyChar() == '3') {
                            focus.changeOutlineColor(cor[3]);
                        }
                        else if (ke.getKeyChar() == '4') {
                            focus.changeOutlineColor(cor[4]);
                        }
                        else if (ke.getKeyChar() == '5') {
                            focus.changeOutlineColor(cor[5]);
                        }
                        else if (ke.getKeyChar() == '6') {
                            focus.changeOutlineColor(cor[6]);
                        }
                        else if (ke.getKeyChar() == '7') {
                            focus.changeOutlineColor(cor[7]);
                        }
                        else if (ke.getKeyChar() == '8') {
                            focus.changeOutlineColor(cor[8]);
                        }
                    }
                    repaint();
                }
            }
        );
        this.setTitle("Projeto parte 1 - Utilizar as teclas '0' à '8' para mudança de cor, com 'c' para troca de contorno e 'f' para troca de fundo");
        this.setSize(350, 350);
        setLocationRelativeTo(null);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }   
        if (focus != null) {
            focus.drawBorder(g);
            focus.drawRedim(g);
        }
    }
}
