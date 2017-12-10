/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;

/**
 *
 * @author lllgabrielll
 */
public class Enemy extends Element {
    
    public Enemy(String[] imgs) {
        super(imgs, 0);
    }
    
    
    @Override
    public void autoDraw(Graphics g) {
    }
    
    
}
