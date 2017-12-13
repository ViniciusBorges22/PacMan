/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lllgabrielll
 */
public class Inky extends Enemy implements Runnable{
        
    public Inky() {
        super(new String[] {"inky.png", "vulnerable_ghost.png"});        
    }
    
    @Override
    public void run() {
        // Calcula rota entre inimigo e pacman
        while (true) {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
            setMoveDirection(Enemy.MOVE_UP);  
        }
    }
    
}
