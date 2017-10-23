package elements;

import utils.Drawing;
import java.awt.Graphics;

public class Brick extends Element
{
    public Brick()
    {
        super(new String[]{"brick.png"}, 0);
        this.isTransposable = false;
    }
    
    @Override
    public void autoDraw(Graphics g){
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
}
