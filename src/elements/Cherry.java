package elements;

import java.awt.Graphics;
import utils.Drawing;

public class Cherry extends Element{
    private final int points;
    private int duration;
    private int contIntervals;


    public Cherry() {
        super(new String[]{"cherry.png"}, 0);
        this.isMortal = true;
        this.points = 100;
        this.duration = 400;
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }

    public int getDuration(){
        return duration;
    }

    public void decrementDuration(){
        this.duration--;
    }

}
