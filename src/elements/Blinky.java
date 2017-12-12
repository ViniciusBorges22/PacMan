/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

/**
 *
 * @author lllgabrielll
 */
public class Blinky extends Enemy implements Runnable {
    
    public Blinky() {
        super(new String[]{"red_ghost.png"});
    }

    @Override
    public void run() {
        // Thread para realizar algum calculo para seguir o pacman
    }

}
