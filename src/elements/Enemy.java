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
public class Enemy extends Element {
    
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_UP = 3;
    public static final int MOVE_DOWN = 4;
    
    // Posições do pacman
    protected Point pacman_pos;
    
    // Mapa da tela
    protected int map[][];
    
    private int movDirection;
    private int state;
    
    public Enemy(String[] imgs) {
        super(imgs, 0, 3);
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
                this.moveLeftEnemy();
                break;
            case MOVE_RIGHT:
                this.moveRightEnemy();
                break;
            case MOVE_UP:
                this.moveUpEnemy();
                break;
            case MOVE_DOWN:
                this.moveDownEnemy();
                break;
            default:
                break;
        }
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon,  pos.getY(), pos.getX());
    }

    public void setPacman_pos(final int x, final int y) {
        this.pacman_pos.x = x;
        this.pacman_pos.y = y;
    }
    
    public void setMap(int map[][]) {
        this.map = map;
    }
}
