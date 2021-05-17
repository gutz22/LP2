import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import figures.*;

class App {
    public static void main(String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}   
// Para a para mudança de cor nas figuras, pressione 'c' para troca de contorno e 'f' para troca de fundo, com as teclas de 0 a 9 representando uma cor específica. Pressione as teclas 'r', 'e', 'l' ou 's' para interromper a criação de figuras com o clique."
class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    ArrayList<Figure> sel = new ArrayList<Figure>();
    boolean selIsNotInicialized = true;
    
    Figure focus = null;
    Figure nextPaint = null;
    int nextPaintIndex = -1;
    int call = 0;
    Point pos = null;
    
    boolean redimClick = false;
    int dx, dy;
    int dxR,dyR;
    int redimX, redimY;
    boolean changeOutline = false; boolean changeBackground = false;
    int f, c; 
    Random rand = new Random();
    int fundo = rand.nextInt(9), contorno = rand.nextInt(9); 

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
                    plum, darkOrange, silver, saddleBrown, Color.black };
    
    ListFrame() {
        this.setTitle("Projeto parte 2 - Utilizar as teclas '0' à '9' para mudança de cor, com 'c' para troca de contorno e 'f' para troca de fundo. Pressionar teclas 'r', 'e', 'l' ou 's' para interromper a criação de figuras com o clique.");
        this.setSize(350, 350);
        setLocationRelativeTo(null);
        
        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList<Figure>) o.readObject();
            o.close();
        } catch (Exception x) {
            System.out.println("Erro");
        }

        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    try {
                        FileOutputStream f = new FileOutputStream("proj.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();
                    } catch (Exception x) {
                    }
                    System.exit(0);
                }
            }
        );
      
        this.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    pos = getMousePosition();
                    focus = null;
                    redimClick = false;
                    boolean insideFocus = false;
                    boolean insideSel = false;
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
                    for (Figure s: sel) {
                        if (s.clicked(pos.x, pos.y)) {
                            nextPaintIndex = sel.indexOf(s);
                            nextPaint = s;
                            insideSel = true;
                            break;
                        }
                    }
                    if ((!insideFocus) && (nextPaint != null) && (!insideSel)) {
                        focus = null;
                        int w = rand.nextInt(30) + 20;
                        int h = rand.nextInt(30) + 20;

                        if (nextPaintIndex == 0) {
                            figs.add(new LineSegment(pos.x,pos.y, w,h, nextPaint.corFundo));
                            focus = figs.get(figs.size()-1);
                        }
                        else if (nextPaintIndex == 1) {
                            figs.add(new Lozenge(pos.x,pos.y, w,h, nextPaint.corContorno,nextPaint.corFundo));
                            focus = figs.get(figs.size()-1);
                        }
                        else if (nextPaintIndex == 2) {
                            figs.add(new Ellipse(pos.x,pos.y, w,h, nextPaint.corContorno,nextPaint.corFundo));
                            focus = figs.get(figs.size()-1);
                        }
                        else if (nextPaintIndex == 3) {
                            figs.add(new Rect(pos.x,pos.y, w,h, nextPaint.corContorno,nextPaint.corFundo));
                            focus = figs.get(figs.size()-1);
                        }
                    }
                    else if ((!insideFocus) && (nextPaint == null)) {
                        focus = null;
                    }
                    repaint();
                }   
            }
        );

        this.addMouseMotionListener(
            new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent me) {
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
                public void keyPressed(KeyEvent ke) {
                    pos = getMousePosition();
                    int xa = pos.x;
                    int ya = pos.y;
                    int w = rand.nextInt(30) + 20;
                    int h = rand.nextInt(30) + 20;
            
                    if (ke.getKeyChar() == 'r') {
                        nextPaintIndex = -1;
                        nextPaint = null;
                        figs.add(new Rect(xa,ya, w,h, sel.get(3).corContorno,sel.get(3).corFundo));
                        focus = figs.get(figs.size()-1);
                    } 
                    else if (ke.getKeyChar() == 'e') {
                        nextPaintIndex = -1;
                        nextPaint = null;
                        figs.add(new Ellipse(xa,ya, w,h, sel.get(2).corContorno,sel.get(2).corFundo));
                        focus = figs.get(figs.size()-1);
                    } 
                    else if (ke.getKeyChar() == 'l') {
                        nextPaintIndex = -1;
                        nextPaint = null;
                        figs.add(new Lozenge(xa,ya, w, h, sel.get(1).corContorno,sel.get(1).corFundo));
                        focus = figs.get(figs.size()-1);
                    }
                    else if (ke.getKeyChar() == 's') {
                        nextPaintIndex = -1;
                        nextPaint = null;
                        figs.add(new LineSegment(xa,ya, w,h, sel.get(0).corFundo));
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
                    if ((ke.getKeyChar() == 127) || (ke.getKeyChar() == 'd')) {
                        figs.remove(focus);
                        focus = null;
                    }

                    if (changeBackground) {
                        f = ke.getKeyChar() - '0';
                        if ((focus != null) && (f >= 0 && f <= 9)) {
                            focus.changeBackgroundColor(cor[f]);
                        }
                        if ((nextPaint != null) && (f >= 0 && f <= 9)){
                            nextPaint.changeBackgroundColor(cor[f]);
                        }
                    }
                    else if (changeOutline) {
                        c = ke.getKeyChar() - '0';
                        if ((focus != null) && (c >= 0 && c <= 9)) {
                            focus.changeOutlineColor(cor[c]);
                        }
                        if ((nextPaint != null) && (c >= 0 && c <= 9)) {
                            nextPaint.changeOutlineColor(cor[c]);
                        }
                    }
                    repaint();
                }
            }
        );
    }
    public void init_sel() {
        sel.clear();
        sel.add(new LineSegment(320,318, 30,30, cor[fundo]));
        sel.add(new Lozenge(290,318, 30,30, cor[contorno],cor[fundo]));
        sel.add(new Ellipse(260,318, 30,30, cor[contorno], cor[fundo]));
        sel.add(new Rect(228,320, 30,26, cor[contorno], cor[fundo]));
        selIsNotInicialized = false;
    }
    public void paint(Graphics g) {
        super.paint(g);
        for (Figure fig: this.figs) {
            fig.paint(g);
        }   
        if (focus != null) {
            focus.drawBorder(g, Color.red);
            focus.drawRedim(g);
        }
        if (selIsNotInicialized) {
            init_sel();
        }
        for (Figure s: this.sel) {
            s.paint(g);
            if (nextPaint == s) {
                s.drawBorder(g, Color.magenta);
            }
            else {
               s.drawBorder(g, Color.black);
            }   
        }
    }
}
