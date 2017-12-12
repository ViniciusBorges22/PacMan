/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import java.awt.Point;
import utils.Drawing;

/**
 *
 * @author lllgabrielll
 */
public abstract class Enemy extends Element {
    
    private static final int MOVE_LEFT = 1;
    private static final int MOVE_RIGHT = 2;
    private static final int MOVE_UP = 3;
    private static final int MOVE_DOWN = 4;

    protected static final int HOUSE = 1;
    protected static final int CHASE = 2;
    protected static final int DANGER = 3;
    
    // Posições do pacman
    protected Point pacman_pos;
    
    // Mapa da tela
    protected int map[][];
    
    private int movDirection;
    private int state;
    
    public Enemy(String[] imgs) {
        super(imgs, 0);
        this.isVisible = true;
        this.isTransposable = false;
        this.pacman_pos = new Point();
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

    public void setPacman_pos(final int x, final int y) {
        this.pacman_pos.x = x;
        this.pacman_pos.y = y;
    }
    
    public void setMap(int map[][]) {
        this.map = map;
    }
}
