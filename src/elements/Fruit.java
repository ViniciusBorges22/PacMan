package elements;

import java.awt.Graphics;
import utils.Drawing;

public abstract class Fruit extends Element {

    protected int points;
    protected int duration;

    private static final int MOVE_LEFT = 1;
    private static final int MOVE_RIGHT = 2;
    private static final int MOVE_UP = 3;
    private static final int MOVE_DOWN = 4;

    private int move_direction;

    public Fruit(String iconName, int direction) {
        super(new String[]{iconName}, 0);
        this.move_direction = direction;
        
        // Condições iniciais da fruta
        this.isTransposable = true;
        this.isVisible = false;
    }

    public int getPoints() {
        return points;
    }

    public int getDuration() {
        return duration;
    }

    public void decrementDuration() {
        this.duration--;
    }

    public void setMoveDirection(int direction) {
        this.move_direction = direction;
    }

    public int getMoveDirection() {
        return move_direction;
    }

    public void backToLastPosition() {
        this.getPos().comeBack();
    }

    public void move() {
        switch (move_direction) {
            case MOVE_LEFT:
                this.getPos().moveLeftFruit();
                break;
            case MOVE_RIGHT:
                this.getPos().moveRightFruit();
                break;
            case MOVE_UP:
                this.getPos().moveUpFruit();
                break;
            case MOVE_DOWN:
                this.getPos().moveDownFruit();
                break;
            default:
                break;
        }
    }

    @Override
    public void autoDraw(Graphics g) {
        // Se a fruta estiver visivel
        if (isVisible) {
            Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
        }
    }

}
