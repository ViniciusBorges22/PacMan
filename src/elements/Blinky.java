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
public class Blinky extends Enemy {

    public Blinky() {
        super(new String[]{"red_ghost.png", "vulnerable_ghost.png"});

        // Movimentação inicial
        setMoveDirection(Enemy.MOVE_LEFT);
    }
    
    public void backToLastPosition() {
        this.pos.comeBack();
    }
    
}
