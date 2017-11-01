package elements;

import utils.Drawing;
import java.awt.Graphics;

public class Wall extends Element{
    
    //construtor
    public Wall(){
        super(new String[]{"wall.png"}, 0);
        this.isTransposable = false;
    }
    
    //métodos
    @Override
    public void autoDraw(Graphics g){
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
}
