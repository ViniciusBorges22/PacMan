/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import javax.swing.ImageIcon;

/**
 *
 * @author lllgabrielll
 */
public class Blinky extends Enemy {

    private int lastDirection;

    public Blinky() {
        super(new String[]{"red_ghost.png"});

        // Movimentação inicial
        setMoveDirection(Enemy.MOVE_LEFT);
        this.lastDirection = Enemy.MOVE_LEFT;
    }

    public void setLastDirection(int lastDirection) {
        this.lastDirection = lastDirection;
    }

    public int getLastDirection() {
        return lastDirection;
    }

}
