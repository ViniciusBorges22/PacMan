package control;

import elements.Element;
import elements.PacMan;
import elements.Cherry;
import elements.Strawberry;
import utils.Consts;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Projeto de POO 2017
 *
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameController {
    
    //métodos
    public void drawAllElements(ArrayList<Element> elemArray, Graphics g){
        for(int i=0; i<elemArray.size(); i++){
            elemArray.get(i).autoDraw(g);
        }
    }
    
    public void processAllElements(ArrayList<Element> e){
        if(e.isEmpty())
            return;

        PacMan pPacMan = (PacMan)e.get(0);
        if(!isValidPosition(e, pPacMan)) {
            pPacMan.backToLastPosition();
            pPacMan.setMovDirection(PacMan.STOP);
            return;
        }

        Element eTemp;
        for(int i = 1; i < e.size(); i++){
            eTemp = e.get(i);
            if(pPacMan.overlap(eTemp)){
                if(eTemp.isTransposable()){     
                    e.remove(eTemp);
                    if(eTemp instanceof Cherry){
                        ((Cherry) eTemp).setCooldown(Consts.SPAWN_CHERRY/Consts.DELAY);
                        ((Cherry) eTemp).setState(false);
                    }
                    else if(eTemp instanceof Strawberry){
                        ((Strawberry) eTemp).setCooldown(Consts.SPAWN_STRAWBERRY/Consts.DELAY);
                        ((Strawberry) eTemp).setState(false);
                    }
                }
            }
        } 
        pPacMan.move();
    }
    
    public boolean isValidPosition(ArrayList<Element> elemArray, Element elem){
        Element elemAux;
        for(int i = 1; i < elemArray.size(); i++){
            elemAux = elemArray.get(i);
            if(!elemAux.isTransposable())
                if(elemAux.overlap(elem))
                    return false;
        }
        return true;
    }
}
