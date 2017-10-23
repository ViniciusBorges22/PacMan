/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import utils.Consts;
import utils.Drawing;

/**
 *
 * @author vrtornisiello
 */
public class Strawberry extends Element{
    private final int points;
    private int duration;
    private int contIntervals;
    
    
    public Strawberry() {
        super(new String[]{"cherry.png"}, 0);
        this.isMortal = true;
        this.points = 100;
        this.duration = 20;
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




