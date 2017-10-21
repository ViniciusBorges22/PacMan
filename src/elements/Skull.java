package elements;

import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;

/**
 * Projeto de POO 2017
 *
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class Skull extends Element{
    private int contIntervals;

    public Skull() {
        super(new String[]{"caveira.png"}, 0);
        this.isTransposable = false;
        this.contIntervals = 0;
    }

    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());

        this.contIntervals++;
        if(this.contIntervals == Consts.TIMER_FOGO){
            this.contIntervals = 0;
            Fire f = new Fire();
            f.setPosition(pos.getX(),pos.getY()+1);
            Drawing.getGameScreen().addElement(f);
        }
    }
}
