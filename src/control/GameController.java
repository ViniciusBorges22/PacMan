package control;

import elements.Element;
import elements.PacMan;
import java.awt.Graphics;
import java.util.ArrayList;
import scene.Scene;

public class GameController {

    public void drawAllElements(ArrayList<Element> elemArray, Graphics g) {
        for (int i = 1; i < elemArray.size(); i++) {
            elemArray.get(i).autoDraw(g);
        }
        elemArray.get(0).autoDraw(g);
    }

    public void processAllElements(ArrayList<Element> e, Scene scene) {
        if (e.isEmpty()) {
            return;
        }

        PacMan pPacMan = (PacMan) e.get(0);
        
        // Verifica colisao entre pacman e o cenario
        if (scene.overlap(pPacMan) || !isValidPosition(e, pPacMan)) {
            pPacMan.backToLastPosition();
            pPacMan.setMovDirection(PacMan.STOP);
            return;
        }
        
        Element eTemp;
        // Verifica colisao entre PacMan e outros elementos
        for (int i = 1; i < e.size(); i++) {
            eTemp = e.get(i);
            if (pPacMan.overlap(eTemp)) {
                if (eTemp.isTransposable()) {
                    e.remove(eTemp);
                }
            }
        }

        pPacMan.move();
    }

    private boolean isValidPosition(ArrayList<Element> elemArray, Element elem) {
        Element elemAux;
        for (int i = 1; i < elemArray.size(); i++) {
            elemAux = elemArray.get(i);
            if (!elemAux.isTransposable()) {
                if (elemAux.overlap(elem)) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
