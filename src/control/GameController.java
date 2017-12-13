package control;

import elements.Ball;
import elements.Blinky;
import elements.Element;
import elements.Enemy;
import elements.PacMan;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import scene.Scene;
import utils.Consts;

public class GameController {

    // Desenhar todos os elementos do jogo
    public void drawAllElements(Scene scene, ArrayList<Element> elemArray, Graphics g, int control) {
        // Desenha cenario e bolinhas
        scene.paintScene(g);

        // Desenha outros elementos
        if (control != 0 && control != 4) {
            Iterator<Element> it = elemArray.listIterator();
            while (it.hasNext()) {
                it.next().autoDraw(g);
            }
        }
    }

    public boolean processAllElements(Scene scene, ArrayList<Element> e, ArrayList<Enemy> enemys) {
        if (e.isEmpty()) {
            return false;
        }

        PacMan pPacMan = (PacMan) e.get(0);
        Blinky blinky = (Blinky) enemys.get(0);

        // Verifica colisao entre pacman e o cenario
        if (!isValidPositionScene(pPacMan, scene)) {
            pPacMan.backToLastPosition();
            pPacMan.setMovBefDirection(pPacMan.getMovDirection());
            pPacMan.setMovDirection(PacMan.STOP);
            return false;
        }

        // Verifica colisão entre blinky e cenario
        if (!isValidPositionScene(blinky, scene)) {
            setBlinkyMovDirection(blinky, pPacMan);
        }

        // Verifica colisao entre as bolinhas e pacman
        Iterator<Ball> it = scene.getBalls().listIterator();
        while (it.hasNext()) {
            if (pPacMan.overlapBall(it.next())) {
                it.remove();
                break;
            }
        }

        boolean aux = false;

        Element eTemp;
        // Verifica colisao entre PacMan e outros elementos
        for (int i = 1; i < e.size(); i++) {
            eTemp = e.get(i);
            if (!eTemp.isTransposable() && pPacMan.overlap(eTemp) && (pPacMan.getTurn() == true)) {
                if (eTemp instanceof Enemy) {
                    aux = true;
                    pPacMan.backToLastPosition();
                    pPacMan.setMovDirection(PacMan.STOP);
                } else {
                    e.remove(eTemp);
                }
            }
        }

        // Movimenta o pacman
        pPacMan.move();

        // Movimentar inimigos
        blinky.move();

        return aux;
    }

    // Verifica colisão entre elem e cenario
    private boolean isValidPositionScene(final Element elem, final Scene scene) {
        int map[][] = scene.getMap();
        for (int y = 0; y < Consts.NUM_CELLS; y++) {
            for (int x = 0; x < Consts.NUM_CELLS; x++) {
                if (map[y][x] == 1) {
                    double x_aux = Math.abs(y - elem.getPos().getX());
                    double y_aux = Math.abs(x - elem.getPos().getY());

                    if (x_aux < 1 && y_aux < 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Setar movimento do blinky
    private void setBlinkyMovDirection(Enemy enemy, PacMan pPacMan) {

        // Definir uma nova direção para o blinky
        switch (enemy.getMovDirection()) {
            case Enemy.MOVE_LEFT:
//                if (pPacMan.getPos().getX() > enemy.getPos().getX()) {
//                    enemy.setMoveDirection(Enemy.MOVE_DOWN);
//                } else {
//                    enemy.setMoveDirection(Enemy.MOVE_UP);
//                }
                enemy.setMoveDirection(Enemy.MOVE_RIGHT);
                break;

            case Enemy.MOVE_RIGHT:
//                if (pPacMan.getPos().getX() > enemy.getPos().getX()) {
//                    enemy.setMoveDirection(Enemy.MOVE_DOWN);
//                } else {
//                    enemy.setMoveDirection(Enemy.MOVE_UP);
//                }
                enemy.setMoveDirection(Enemy.MOVE_LEFT);
                break;

            case Enemy.MOVE_DOWN:
//                if (pPacMan.getPos().getY() > enemy.getPos().getY()) {
//                    enemy.setMoveDirection(Enemy.MOVE_RIGHT);
//                } else {
//                    enemy.setMoveDirection(Enemy.MOVE_LEFT);
//                }
                enemy.setMoveDirection(Enemy.MOVE_UP);
                break;

            case Enemy.MOVE_UP:
//                if (pPacMan.getPos().getY() > enemy.getPos().getY()) {
//                    enemy.setMoveDirection(Enemy.MOVE_RIGHT);
//                } else {
//                    enemy.setMoveDirection(Enemy.MOVE_LEFT);
//                }
                enemy.setMoveDirection(Enemy.MOVE_DOWN);
                break;
        }
    }

}
