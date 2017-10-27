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
 * @author lllgabrielll
 */
public abstract class Enemy extends Element {
    
    public Enemy(String[] imageName, int dir) {
        super(imageName, dir);
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, imageIcon, this.pos.getY(), this.pos.getX());
    }
    
}
