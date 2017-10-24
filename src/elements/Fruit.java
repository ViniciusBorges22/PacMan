package elements;

import java.awt.Graphics;
import utils.Drawing;

public abstract class Fruit extends Element{
    protected int points;
    protected int duration;

    public Fruit(String iconName)
    {
        super(new String[]{iconName}, 0);
        this.isMortal = true;
    }

    public int getPoints()
    {
        return points;
    }

    public int getDuration(){
        return duration;
    }

    public void decrementDuration(){
        this.duration--;
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
}
