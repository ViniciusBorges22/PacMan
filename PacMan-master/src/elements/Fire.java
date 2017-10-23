package elements;

import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * Projeto de POO 2017
 *
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Fire extends Element implements Serializable{

    public Fire() {
        super(new String[]{"fire.png"}, 0);
        this.isMortal = true;
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        if(!this.moveRight())
            Drawing.getGameScreen().removeElement(this);
    }

}
