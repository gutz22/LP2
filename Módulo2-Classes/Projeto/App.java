import java.awt.GraphicsEnvironment;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.awt.*;

import figures.*;
import menu.Menu;

class App {
    public static void main(String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}   
class ListFrame extends JFrame {

    private ArrayList<Figure> figs = new ArrayList<Figure>();
    private ArrayList<Figure> sel = new ArrayList<Figure>();
    private ArrayList<Menu> options = new ArrayList<Menu>();
    private ArrayList<Menu> instructions = new ArrayList<Menu>();
    private ArrayList<Menu> leave = new ArrayList<Menu>();

    private boolean selIsNotInicialized = true;
    private boolean displayMenu = true; boolean displayInstructions = false; boolean displayLeave = false; 
    private boolean menuExited = false;
    private Menu menuFocus = null;
    private Figure focus = null;
    private Figure nextPaint = null;
    private int nextPaintIndex = -1;
    private Point pos = null;
    
    private boolean redimClick = false;
    private int dx, dy;
    private int dxR,dyR;
    private int redimX, redimY;
    private boolean changeOutline = false; boolean changeBackground = false;
    private int f, c; 
    private Random rand = new Random();
    private int fundo = rand.nextInt(9), contorno = rand.nextInt(9); 

    Color aqua = new Color(0, 255, 255);
    Color crimson = new Color(220, 0, 60);  
    Color gold = new Color(255, 215, 0);
    Color purple = new Color(128, 0, 128);
    Color forestGreen = new Color(34, 139, 24);
    Color plum = new Color(221, 160, 221);
    Color darkOrange = new Color(255, 140, 0);
    Color saddleBrown = new Color(139, 69, 19);
    Color cor[] = { aqua, crimson, gold, purple, forestGreen,
                    plum, darkOrange, saddleBrown, Color.white, Color.black };
    
    ListFrame() {
        this.setSize(350, 350);
        this.getContentPane().setBackground(Color.gray);
        setLocationRelativeTo(null);
                
        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList<Figure>) o.readObject();
            this.selIsNotInicialized = o.readBoolean();
            this.sel = (ArrayList<Figure>) o.readObject();
            o.close();
        } catch (Exception x) {
            System.out.println("Iniciando pela primera vez!");
        }

        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    try {
                        FileOutputStream f = new FileOutputStream("proj.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.writeBoolean(selIsNotInicialized);
                        o.writeObject(sel);
                        o.flush();
                        o.close();            
                    } catch (Exception x) {
                        System.out.println("Erro na gravação do arquivo.");
                    }
                    System.exit(0);
                }
                public void windowOpened(WindowEvent e) {
                    initMenu();
                }
            }
        );
        this.addMouseListener(
            new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    if (menuExited) {
                        pos = getMousePosition();
                        redimClick = false;
                        boolean insideFocus = false;
                        boolean stop = false;
                        boolean insideSel = false;
                        for (Figure fig: figs) {
                            if (fig.clicked(pos.x, pos.y)) {
                                if (focus == fig) {
                                    focus = figs.get(figs.size()-1);
                                    stop = true;
                                }   
                                else {
                                    focus = fig;
                                }
                                insideFocus = true;
                                dx = focus.x - pos.x;
                                dy = focus.y - pos.y;
                                if (focus.redimClicked(pos.x, pos.y)) {
                                    redimClick = true;
                                    redimX = focus.x + focus.w - 7;
                                    redimY = focus.y + focus.h - 7;
                                    dxR = redimX - pos.x;
                                    dyR = redimY - pos.y;
                                }
                                if (stop) {
                                    break;
                                }
                            }
                        }
                        if (insideFocus) {
                            figs.remove(focus);
                            figs.add(focus);
                        }
                        for (Figure s: sel) {
                            if (s.clicked(pos.x, pos.y)) {
                                nextPaintIndex = sel.indexOf(s);
                                nextPaint = s;
                                insideSel = true;
                                insideFocus = false;
                                break;
                            }
                        }
                        if ((!insideFocus) && (nextPaint != null) && (!insideSel)) {
                            focus = null;
                            int w = rand.nextInt(30) + 20;
                            int h = rand.nextInt(30) + 20;

                            if (nextPaintIndex == 0) {
                                figs.add(new LineSegment(pos.x,pos.y, w,h, nextPaint.corFundo));
                            }
                            else if (nextPaintIndex == 1) {
                                figs.add(new Lozenge(pos.x,pos.y, w,h, nextPaint.corFundo,nextPaint.corContorno));
                            }
                            else if (nextPaintIndex == 2) {
                                figs.add(new Ellipse(pos.x,pos.y, w,h, nextPaint.corFundo,nextPaint.corContorno));
                            }
                            else if (nextPaintIndex == 3) {
                                figs.add(new Rect(pos.x,pos.y, w,h, nextPaint.corFundo,nextPaint.corContorno));
                            }
                            focus = figs.get(figs.size()-1);
                            insideFocus = true;
                        }
                        else if ((!insideFocus) && (nextPaint == null) && (!insideSel)) {
                            focus = null;
                        }
                        repaint();
                    }
                }   
            }
        );

        this.addMouseMotionListener(
            new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent me) {
                    if (menuExited) {
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
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed(KeyEvent ke) {
                    if (menuExited) {
                        pos = getMousePosition();
                        if (pos != null) {
                            int xa = pos.x;
                            int ya = pos.y;
                            int w = rand.nextInt(30) + 20;
                            int h = rand.nextInt(30) + 20;
                            if (ke.getKeyChar() == 'r') {
                                nextPaintIndex = -1;
                                nextPaint = null;
                                figs.add(new Rect(xa,ya, w,h, sel.get(3).corFundo,sel.get(3).corContorno));
                                focus = figs.get(figs.size()-1);
                            } 
                            else if (ke.getKeyChar() == 'e') {
                                nextPaintIndex = -1;
                                nextPaint = null;
                                figs.add(new Ellipse(xa,ya, w,h, sel.get(2).corFundo,sel.get(2).corContorno));
                                focus = figs.get(figs.size()-1);
                            } 
                            else if (ke.getKeyChar() == 'l') {
                                nextPaintIndex = -1;
                                nextPaint = null;
                                figs.add(new Lozenge(xa,ya, w, h, sel.get(1).corFundo,sel.get(1).corContorno));
                                focus = figs.get(figs.size()-1);
                            }
                            else if (ke.getKeyChar() == 's') {
                                nextPaintIndex = -1;
                                nextPaint = null;
                                figs.add(new LineSegment(xa,ya, w,h, sel.get(0).corFundo));
                                focus = figs.get(figs.size()-1);
                            }
                        }
                        
                        if (ke.getKeyChar() == 'm') {
                            menuExited = false;
                            options.get(1).setOP("Continuar");
                            displayMenu = true;
                            menuFocus = options.get(1);
                        }
                        if (ke.getKeyChar() == 'f') {
                            changeBackground = true;
                            changeOutline = false;
                        }
                        else if (ke.getKeyChar() == 'c') {
                            changeOutline = true;
                            changeBackground = false;
                        }
                        else {
                            int num = ke.getKeyChar() - '0';
                            if (!(num>= 0 && num<= 9)){
                                nextPaint = null;
                                nextPaintIndex = -1;
                            }
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
                        
                    }
                    else {
                        if ((ke.getKeyCode() == KeyEvent.VK_UP) || (ke.getKeyCode() == KeyEvent.VK_LEFT)) {
                            if (displayMenu) {
                                if (options.indexOf(menuFocus) == 1) {
                                    menuFocus = options.get(options.size()-1);
                                }
                                else {
                                    menuFocus = options.get(options.indexOf(menuFocus)-1);
                                }
                            }
                            else if (displayLeave) {
                                if (leave.indexOf(menuFocus) == 1) {
                                    menuFocus = leave.get(leave.size()-1);
                                }
                                else {
                                    menuFocus = leave.get(leave.indexOf(menuFocus)-1);
                                }
                            }
                        }
                        if ((ke.getKeyCode() == KeyEvent.VK_DOWN) || (ke.getKeyCode() == KeyEvent.VK_RIGHT)) {
                            if (displayMenu)  {
                                if (options.indexOf(menuFocus) == (options.size()-1)) {
                                    menuFocus = options.get(1);
                                }
                                else {
                                    menuFocus = options.get(options.indexOf(menuFocus)+1);
                                }
                            }
                            else if (displayLeave) {
                                if (leave.indexOf(menuFocus) == (leave.size()-1)) {
                                    menuFocus = leave.get(1);
                                }
                                else {
                                    menuFocus = leave.get(leave.indexOf(menuFocus)+1);
                                }
                            }
                        }
                        if (ke.getKeyChar() == 'b') {
                            displayInstructions = false;
                            displayLeave = false;
                            displayMenu = true;
                            menuFocus = options.get(1);
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                            if (menuFocus == options.get(1)) {
                                displayMenu = false;
                                displayLeave = false;
                                displayInstructions = false;
                                menuExited = true;
                                initSel();
                            }
                            else if (menuFocus == options.get(2)) {
                                displayMenu = false;
                                displayLeave = false;
                                displayInstructions = true;
                            }
                            else if (menuFocus == options.get(3)) {
                                displayMenu = false;
                                displayInstructions = false;
                                displayLeave = true;
                                menuFocus = leave.get(1);
                            }
                            else if (menuFocus == leave.get(2)) {
                                displayInstructions = false;
                                displayLeave = false;
                                displayMenu = true;
                                menuFocus = options.get(1);
                            }
                            else if (menuFocus == leave.get(1)) {
                                closeApp();
                            }
                        }
                    }
                    repaint();
                }
            }
        );
    }
    private void closeApp(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void initSel() {
        if (selIsNotInicialized) {
            sel.add(new LineSegment(320,318, 30,30, cor[fundo]));
            sel.add(new Lozenge(290,318, 30,30, cor[fundo],cor[contorno]));
            sel.add(new Ellipse(260,318, 30,30, cor[fundo],cor[contorno]));
            sel.add(new Rect(228,320, 30,26,  cor[fundo],cor[contorno]));
            selIsNotInicialized = false;
        }
        repaint();
    }

    private void initMenu() {
        String[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String font = (Arrays.asList(allFonts).contains("Ubuntu")) ? "Ubuntu" : "Serif";
        String title = (Arrays.asList(allFonts).contains("Comic Sans MS")) ? "Comic Sans MS" : "Serif";

        options.add(new Menu(0, 100, "Java 2D Projeto", title, false, 18, Color.red));
        menuFocus = new Menu(0, 155, "Iniciar", font, true, 12, Color.black);
        options.add(menuFocus);
        options.add(new Menu(0, 180, "Instruções", font, true, 12, Color.black));
        options.add(new Menu(0, 205, "Sair", font, true, 12, Color.black));
        
        instructions.add(new Menu(5, 40, "Pressione B para voltar.", "Serif", true, 10, Color.black));

        instructions.add(new Menu(5, 60, "Este é um programa para criação, redimensionamento, movimentação", font, false, 10, Color.black));
        instructions.add(new Menu(5, 70, "e troca de cores de figuras na tela.", font, false, 10, Color.black));
        
        instructions.add(new Menu(5, 85, "É possível criá-las tanto pelo mouse quanto pelo teclado.", font, true, 9, Color.black));
        instructions.add(new Menu(5, 95, "Pelo mouse, selecione uma das figuras no canto inferior direito da tela e ela exibirá", font, true, 9, Color.black));
        instructions.add(new Menu(5, 105, "na tela com o clique em um espaço escolhido.", font, true, 9, Color.black));
        instructions.add(new Menu(5, 115, "Para interromper a criação com o clique, aperte uma tecla qualquer.", font, true, 9, Color.black));
        instructions.add(new Menu(5, 135, "A partir do teclado, utilize as seguintes teclas:", font, true, 9, Color.black));
        instructions.add(new Menu(5, 145, "R", font, false, 9, Color.black));
        instructions.add(new Menu(14, 145, "para retângulo", font, true, 9, Color.black));
        instructions.add(new Menu(5, 155, "E", font, false, 9, Color.black));
        instructions.add(new Menu(14, 155, "para elipse", font, true, 9, Color.black));
        instructions.add(new Menu(5, 165, "L", font, false, 9, Color.black));
        instructions.add(new Menu(14, 165, "para losango", font, true, 9, Color.black));
        instructions.add(new Menu(5, 175, "S", font, false, 9, Color.black));
        instructions.add(new Menu(14, 175, "para linha", font, true, 9, Color.black));
        instructions.add(new Menu(5, 185, "D", font, false, 9, Color.black));
        instructions.add(new Menu(14, 185, "ou", font, true, 9, Color.black));
        instructions.add(new Menu(26, 185, "del", font, false, 9, Color.black));
        instructions.add(new Menu(44, 185, "para deleção do foco", font, true, 9, Color.black));
        instructions.add(new Menu(5, 195, "M", font, false, 9, Color.black));
        instructions.add(new Menu(14, 195, "para retornar ao menu.", font, true, 9, Color.black));
        instructions.add(new Menu(5, 215, "Para o redimensionamento, clique e arraste o quadrado localizado no canto inferior", font, true, 9, Color.black));
        instructions.add(new Menu(5, 225, "direito da figura em foque.", font,true, 9, Color.black));
        instructions.add(new Menu(5, 245, "Para a mudança de cor, utilize as seguintes teclas:", font, true, 9, Color.black));
        instructions.add(new Menu(5, 255, "C", font, false, 9, Color.black));
        instructions.add(new Menu(14, 255, "para trocar o contorno", font, true, 9, Color.black));
        instructions.add(new Menu(5, 265, "F", font, false, 9, Color.black));
        instructions.add(new Menu(14, 265, "para trocar o fundo", font, true, 9, Color.black));
        instructions.add(new Menu(5, 275, "Com a opção escolhida, as teclas de 0 a 9 determinarão uma cor específica.", font, true, 9, Color.black));
        instructions.add(new Menu(5, 285, "Além das figuras criadas na tela, também é possível mudar a cor da figura selecionada", font, true, 9, Color.black));
        instructions.add(new Menu(5, 295, "na lista de seleção.", font, true, 9, Color.black));
        instructions.add(new Menu(5, 315, "Após fechar o programa, tanto o estado das figuras quanto da lista estará salvo num", font, true, 9, Color.black));
        instructions.add(new Menu(5, 325, "arquivo, que será carregado a cada nova instância do programa.", font, true, 9, Color.black));

        leave.add(new Menu(0, 110, "Deseja sair?", "Serif", false, 15, Color.black));
        leave.add(new Menu(0, 155, "Sim", font, true, 12, Color.black));
        leave.add(new Menu(0, 180, "Não", font, true, 12, Color.black));
        repaint();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if (menuExited) {
            for (Figure fig: this.figs) {
                fig.paint(g, fig == focus, true);
            } 
            for (Figure s: this.sel) {
                s.paint(g, s == nextPaint, false);
            }
        }
        else if (displayMenu) {
            for (Menu op: this.options) {
                op.paint(g, op == menuFocus);
            }
        }
        else if (displayInstructions) {
            for (Menu i: this.instructions) {
                i.paint(g, false);
            }
        }
        else if (displayLeave) {
            for (Menu l: this.leave) {
                l.paint(g, l == menuFocus);
            }
        }
    }
}
