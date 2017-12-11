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
public class Enemy extends Element {

    private static final int MOVE_LEFT = 1;
    private static final int MOVE_RIGHT = 2;
    private static final int MOVE_UP = 3;
    private static final int MOVE_DOWN = 4;

    protected static final int HOUSE = 1;
    protected static final int CHASE = 2;
    protected static final int DANGER = 3;

    private int movDirection;
    private int state;

    public Enemy(String[] imgs) {
        super(imgs, 0);
        this.isVisible = true;
        this.isTransposable = false;
    }

    public void setMoveDirection(int movDirection) {
        this.movDirection = movDirection;
    }

    public int getMovDirection() {
        return movDirection;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void move() {
        switch (movDirection) {
            case MOVE_LEFT:
                this.moveLeft();
                break;
            case MOVE_RIGHT:
                this.moveRight();
                break;
            case MOVE_UP:
                this.moveUp();
                break;
            case MOVE_DOWN:
                this.moveDown();
                break;
            default:
                break;
        }
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getX(), pos.getY());
    }

}
