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
public class Blinky extends Enemy implements Runnable {
    
    public Blinky() {
        super(new String[]{"red_ghost.png"});
        
        // Movimentação inicial
        setMoveDirection(Enemy.MOVE_LEFT);
    }

    @Override
    public void run() {
        // Calcula rota entre inimigo e pacman
        while (true) {

            // Espera 8s para o calculo de uma rota
            try {
                Thread.sleep(8000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Calcula rota
            
        }
    }
    
}
