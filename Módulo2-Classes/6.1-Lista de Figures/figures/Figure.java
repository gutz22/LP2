package figures;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figure {
    protected int x, y;
    protected int w, h;
    protected Color corContorno, corFundo;
    
    public abstract void paint (Graphics g);
}
