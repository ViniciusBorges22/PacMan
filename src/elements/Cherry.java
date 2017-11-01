/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;
import utils.Consts;

/**
 *
 * @author vrtornisiello
 */
public class Cherry extends Fruit {
    
    //construtor
    public Cherry(){
        super("cherry.png");
        this.points = 100;
        this.cooldown = (Consts.SPAWN_CHERRY/Consts.DELAY);
        this.state = false;
    }
}


