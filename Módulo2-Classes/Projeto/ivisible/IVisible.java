package ivisible;

import java.awt.Graphics;

public interface IVisible {
    public boolean clicked(int posX, int posY);
    public void paint(Graphics g, boolean focused, boolean isFigs);
}
