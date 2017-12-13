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
public class Inky extends Enemy {

    public Inky() {
        super(new String[]{"inky.png", "vulnerable_ghost.png"});
    }

    public void backToLastPosition() {
        this.pos.comeBack();
    }

}
