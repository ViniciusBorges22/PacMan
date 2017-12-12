package elements;

import java.awt.Graphics;
import utils.Consts;
import utils.Drawing;

public abstract class Fruit extends Element {

    protected int points;
    protected int duration;

    public Fruit(String iconName) {
        super(new String[]{iconName}, 0, Consts.ELEMENT_SIZE);

        // Condições iniciais da fruta
        this.isTransposable = true;
        this.isVisible = false;
    }

    public int getPoints() {
        return points;
    }

    public int getDuration() {
        return duration;
    }

    public void decrementDuration() {
        this.duration--;
    }

    @Override
    public void autoDraw(Graphics g) {
        if (isVisible) {
            Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        }
    }
}
