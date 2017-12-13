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
<<<<<<< HEAD
public class Blinky extends Enemy {

    public Blinky() {
        super(new String[]{"red_ghost.png", "vulnerable_ghost.png"});

=======
public class Blinky extends Enemy implements Runnable {
       
    public Blinky() {
        super(new String[]{"blinky.png", "vulnerable_ghost.png"});
        
>>>>>>> origin/Score/Menu/Níveis
        // Movimentação inicial
        setMoveDirection(Enemy.MOVE_UP);
    }
    
    public void backToLastPosition() {
        this.pos.comeBack();
    }
<<<<<<< HEAD
    
    public void backToLastPosition() {
        this.pos.comeBack();
=======

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
>>>>>>> origin/Score/Menu/Níveis
    }
}
