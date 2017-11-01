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
public class Strawberry extends Fruit{
    
    //construtor
    public Strawberry(){
        super("strawberry.png");
        this.points = 300;
        this.cooldown = (Consts.SPAWN_STRAWBERRY/Consts.DELAY);
        this.state = false;
    }
}
