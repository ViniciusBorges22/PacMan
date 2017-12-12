package control;

import elements.Ball;
import elements.Element;
import elements.PacMan;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import scene.Scene;
import utils.Consts;

public class GameController {

    public void drawAllElements(Scene scene, ArrayList<Element> elemArray, Graphics g, int control) {
        // Desenha cenario e bolinhas
        scene.paintScene(g);

        // Desenha pacman
        if (control != 0 || control == 4) {
            // Desenha outros elementos
            Iterator<Element> it = elemArray.listIterator(1);
            while (it.hasNext()) {
                it.next().autoDraw(g);
            }
            elemArray.get(0).autoDraw(g);
        }
    }

    public void processAllElements(Scene scene, ArrayList<Element> e) {
        if (e.isEmpty()) {
            return;
        }

        PacMan pPacMan = (PacMan) e.get(0);

        // Verifica colisao entre pacman e o cenario
        if (!isValidPositionScene(pPacMan, scene)) {
            pPacMan.backToLastPosition();
            pPacMan.setMovDirection(PacMan.STOP);
            return;
        }

        // Verifica colisao entre as bolinhas e pacman
        Iterator<Ball> it = scene.getBalls().listIterator();
        while (it.hasNext()) {
            if (pPacMan.overlapBall(it.next())) {
                it.remove();
                break;
            }
        }

        Element eTemp;
        // Verifica colisao entre PacMan e outros elementos
        for (int i = 1; i < e.size(); i++) {
            eTemp = e.get(i);
            if (!eTemp.isTransposable() && pPacMan.overlap(eTemp)) {
                e.remove(eTemp);
            }
        }

        pPacMan.move();
    }

    // Verifica colisão entre elem e cenario
    private boolean isValidPositionScene(final Element elem, final Scene scene) {
        int map[][] = scene.getMap();
        for (int y = 0; y < Consts.NUM_CELLS; y++) {
            for (int x = 0; x < Consts.NUM_CELLS; x++) {
                if (map[y][x] == 1) {
                    double x_aux = Math.abs(y - elem.getPos().getX());
                    double y_aux = Math.abs(x - elem.getPos().getY());

                    if (x_aux < 0.9 && y_aux < 0.9) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

//    public boolean isValidPosition(ArrayList<Element> elemArray, Element elem) {
//        Element elemAux;
//        for (int i = 1; i < elemArray.size(); i++) {
//            elemAux = elemArray.get(i);
//            if (!elemAux.isTransposable()) {
//                if (elemAux.overlap(elem)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

}
