/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import utils.Drawing;

/**
 *
 * @author vrtornisiello
 */
public class Fruit extends Element{
    private int points;
    
    public Fruit(String iconName){
        super(new String[]{iconName}, 0);
        
        switch (iconName) {
            case "cherry.png":
                this.isMortal = true;
                this.points = 100;
                break;
            case "strawberry.png":
                this.isMortal = true;
                this.points = 300;
                break;
            default:
                points = 0;
                break;
        }
    }
    
    @Override
    public void autoDraw(Graphics g) {    
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());     
    }
}
