package control;

import elements.Ball;
import elements.Blinky;
import elements.Element;
import elements.Enemy;
import elements.PacMan;
import elements.Pinky;
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

        // Pacmen
        PacMan pPacMan = (PacMan) e.get(0);

        // Inimigos
        Blinky blinky = (Blinky) enemys.get(0);
        Pinky pinky = (Pinky) enemys.get(3);

        // Verifica colisao entre pacman e o cenario
        if (pPacMan.overlap(scene.getWalls())) {
            pPacMan.backToLastPosition();
            pPacMan.setMovBefDirection(pPacMan.getMovDirection());
            pPacMan.setMovDirection(PacMan.STOP);
            return false;
        }

        // Verifica colisão entre blinky e cenario
        if (blinky.overlap(scene.getWalls())) {

            // Setar movimento do blinky quando ocorre uma colisão
            setInvtMovDirectionBlinky(blinky, pPacMan);
        }

        // Verifica colisão entre pinky e cenario
        if (pinky.overlap(scene.getWalls())) {

            // Setar movimento do pinky quando ocorre uma colisão
            setInvtMovDirectionPinky(pinky, pPacMan);

            // Se houve colisão, entao pinky passa para estado aleatorio
            pinky.setState(Pinky.MOVE_ALEAT);
        }

        // Verifica colisao entre as bolinhas e pacman
        Iterator<Ball> it = scene.getBalls().listIterator();
        while (it.hasNext()) {
            if (pPacMan.overlapBall(it.next())) {
                it.remove();
                break;
            }
        }

        // Variavel que detecta que se houve uma colisão entre pacman e inimigo 
        boolean aux = false;
        
        Element eTemp;

        // Verifica colisao entre PacMan e outros elementos
        for (int i = 1; i < e.size(); i++) {
            eTemp = e.get(i);
            if (!eTemp.isTransposable() && pPacMan.overlap(eTemp)) {

                // Verifica se a colisão é com um inimigo
                if (eTemp instanceof Enemy) {
                    aux = true;
                    pPacMan.backToLastPosition();
                    pPacMan.setMovDirection(PacMan.STOP);
                } else {

                    // Se nao for um inimigo remove o elemento da lista
                    e.remove(eTemp);
                }
            }
        }

        // Movimenta o pacman
        pPacMan.move();

        // Movimentar inimigos
        blinky.move();
        pinky.move();
        
        return aux;
    }

    // Verifica colisão entre elem e cenario
    private boolean isValidPositionSceneEnemy(final Element elem, final Scene scene) {
        int map[][] = scene.getMap();
        for (int y = 0; y < Consts.NUM_CELLS; y++) {
            for (int x = 0; x < Consts.NUM_CELLS; x++) {
                if (map[y][x] == 1) {
                    double x_aux = Math.abs(x - elem.getPos().getX());
                    double y_aux = Math.abs(y - elem.getPos().getY());
                    
                    if (x_aux < 0.7 && y_aux < 0.7) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private boolean isValidPositionScenePacman(final Element elem, final Scene scene) {
        int map[][] = scene.getMap();
        for (int y = 0; y < Consts.NUM_CELLS; y++) {
            for (int x = 0; x < Consts.NUM_CELLS; x++) {
                if (map[y][x] == 1) {
                    double x_aux = Math.abs(y - elem.getPos().getX());
                    double y_aux = Math.abs(x - elem.getPos().getY());
                    
                    if (x_aux < 0.95 && y_aux < 0.95) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Setar movimento do blinky
    private void setInvtMovDirectionBlinky(Enemy enemy, PacMan pPacMan) {

        // Definir uma nova direção para o blinky
        switch (enemy.getMovDirection()) {
            case Enemy.MOVE_LEFT:
                if (pPacMan.getPos().getX() > enemy.getPos().getX()) {
                    enemy.setMoveDirection(Enemy.MOVE_DOWN);
                } else {
                    enemy.setMoveDirection(Enemy.MOVE_UP);
                }
//                enemy.setMoveDirection(Enemy.MOVE_RIGHT);
                break;
            
            case Enemy.MOVE_RIGHT:
                if (pPacMan.getPos().getX() > enemy.getPos().getX()) {
                    enemy.setMoveDirection(Enemy.MOVE_DOWN);
                } else {
                    enemy.setMoveDirection(Enemy.MOVE_UP);
                }
//                enemy.setMoveDirection(Enemy.MOVE_LEFT);
                break;
            
            case Enemy.MOVE_DOWN:
                if (pPacMan.getPos().getY() > enemy.getPos().getY()) {
                    enemy.setMoveDirection(Enemy.MOVE_RIGHT);
                } else {
                    enemy.setMoveDirection(Enemy.MOVE_LEFT);
                }
//                enemy.setMoveDirection(Enemy.MOVE_UP);
                break;
            
            case Enemy.MOVE_UP:
                if (pPacMan.getPos().getY() > enemy.getPos().getY()) {
                    enemy.setMoveDirection(Enemy.MOVE_RIGHT);
                } else {
                    enemy.setMoveDirection(Enemy.MOVE_LEFT);
                }
//                enemy.setMoveDirection(Enemy.MOVE_DOWN);
                break;
        }
    }

    // Setar movimento do blinky
    private void setInvtMovDirectionPinky(Enemy enemy, PacMan pPacMan) {
        
        int aux = Math.round((float) Math.random() % 10);

        // Definir uma nova direção para o blinky
        switch (enemy.getMovDirection()) {
            case Enemy.MOVE_LEFT:
                if (aux == 0) {
                    enemy.setMoveDirection(Enemy.MOVE_UP);
                } else {
                    enemy.setMoveDirection(Enemy.MOVE_DOWN);
                }
                break;
            
            case Enemy.MOVE_RIGHT:
                if (aux == 0) {
                    enemy.setMoveDirection(Enemy.MOVE_UP);
                } else {
                    enemy.setMoveDirection(Enemy.MOVE_DOWN);
                }
                break;
            
            case Enemy.MOVE_DOWN:
                if (aux == 0) {
                    enemy.setMoveDirection(Enemy.MOVE_LEFT);
                } else {
                    enemy.setMoveDirection(Enemy.MOVE_RIGHT);
                }
                break;
            
            case Enemy.MOVE_UP:
                if (aux == 0) {
                    enemy.setMoveDirection(Enemy.MOVE_UP);
                } else {
                    enemy.setMoveDirection(Enemy.MOVE_DOWN);
                }
                break;
        }
    }
    
}
