package elements;

import utils.Drawing;
import java.awt.Graphics;
import java.io.Serializable;

public class PacMan extends Element implements Serializable {

    public static final int STOP = 0;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_UP = 3;
    public static final int MOVE_DOWN = 4;

    private int movDirection = STOP;

    public PacMan() {
        super(new String[]{"pacman_right.png", "pacman_down.png",
            "pacman_left.png", "pacman_up.png"}, 0);
    }

    public void changeDirection(int dir) {
        setImageIcon(dir);
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, imageIcon, pos.getY(), pos.getX());
    }

    public void backToLastPosition() {
        this.pos.comeBack();
    }

    public void setMovDirection(int direction) {
        movDirection = direction;
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
}
