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
public class Inky extends Enemy implements Runnable {
    
    public Inky() {
        super(new String[] {"green_ghost.png"});
    }

    @Override
    public void autoDraw(Graphics g) {
        
    }

    @Override
    public void run() {
        // implementar movimentação
    }
    
    
    
}
