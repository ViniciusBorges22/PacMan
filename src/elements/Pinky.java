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
public class Pinky extends Enemy implements Runnable {

    // Se movimentar aleatorio
    public static int MOVE_ALEAT = 0;

    // Se movimentar paralelamente ao pacman
    public static int MOVE_PAC = 1;

    private int stateDirection;
    private int antStateDirection;

    public Pinky() {
        super(new String[]{"pink_ghost.png"});
        setMoveDirection(Enemy.MOVE_RIGHT);
        this.stateDirection = MOVE_PAC;
        this.stateDirection = MOVE_PAC;
    }

    public int getAntStateDirection() {
        return antStateDirection;
    }

    public int getStateDirection() {
        return stateDirection;
    }

    public void setAntStateDirectioni(int antStateDirection) {
        this.antStateDirection = antStateDirection;
    }

    public void setStateDirection(int stateDirection) {
        this.stateDirection = stateDirection;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            // Altera o estado de movimentação a cada 0.5s
            // Movimentar aleatorio
            if (stateDirection == MOVE_ALEAT) {
                stateDirection = MOVE_PAC;
                antStateDirection = MOVE_ALEAT;

            } else {

                // Seguir pacman
                stateDirection = MOVE_ALEAT;
                antStateDirection = MOVE_PAC;
            }
        }
    }

}
