/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;
import java.awt.Graphics;
import utils.Drawing;
import utils.Consts;

/**
 *
 * @author vrtornisiello
 */
public abstract class Fruit extends Element{
    protected int points;
    protected int cooldown;
    protected boolean state;

    //construtor
    public Fruit(String iconName){
        super(new String[]{iconName}, 0);
    }

    //métodos
    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }

    public int getPoints(){
        return points;
    }

    public int getCooldown(){
        return cooldown;
    }

    public void setCooldown(int time){
        cooldown = time;
    }

    public void decreaseCooldown(){
        cooldown--;
    }

    public boolean getState(){
        return state;
    }

    public void setState(boolean state){
        this.state = state;
    }

    public void spawnFruit(){
        double x = Math.random()*Consts.NUM_CELLS;
        double y = Math.random()*Consts.NUM_CELLS;
        this.setPosition(x,y);
    }
}
