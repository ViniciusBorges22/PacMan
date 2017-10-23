package elements;

import utils.Consts;
import utils.Position;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * Projeto de POO 2017
 *
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public abstract class Element implements Serializable{

    protected ImageIcon[] directions;
    protected ImageIcon imageIcon;
    protected Position pos;
    protected boolean isTransposable; // Pode passar por cima?
    protected boolean isMortal;       // Se encostar, morre?

    protected Element(String[] imageName, int dir) {
        this.pos = new Position(1, 1);
        this.isTransposable = true;
        this.isMortal = false;
        directions = new ImageIcon[imageName.length];

        for(int i = 0; i < imageName.length; i++)
        {
            directions[i] = getImageIcon(imageName[i]);
        }

        setImageIcon(dir);
    }

    private ImageIcon getImageIcon(String imageName)
    {
        try {
            ImageIcon imageIconFunc = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + imageName);
            Image img = imageIconFunc.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIZE, Consts.CELL_SIZE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIZE, Consts.CELL_SIZE, null);
            imageIconFunc = new ImageIcon(bi);
            return imageIconFunc;
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    protected final void setImageIcon(int dir)
    {
        imageIcon = directions[dir];
    }

    public boolean overlap(Element elem) {
        double xDist = Math.abs(elem.pos.getX() - this.pos.getX());
        double yDist = Math.abs(elem.pos.getY() - this.pos.getY());

        if (xDist < 1.0 && yDist < 1.0)
            return true;
        else
            return false;
    }

    public String getStringPosition() {
        return ("(" + pos.getX() + ", " + pos.getY() + ")");
    }

    public boolean setPosition(double x, double y) {
        return pos.setPosition(x, y);
    }

    public boolean isTransposable() {
        return isTransposable;
    }

    public void setTransposable(boolean isTransposable) {
        this.isTransposable = isTransposable;
    }

    abstract public void autoDraw(Graphics g);

    public boolean moveUp() {
        return this.pos.moveUp();
    }

    public boolean moveDown() {
        return this.pos.moveDown();
    }

    public boolean moveRight() {
        return this.pos.moveRight();
    }

    public boolean moveLeft() {
        return this.pos.moveLeft();
    }
}
