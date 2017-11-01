/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import utils.Drawing;

/**
 *
 * @author vrtornisiello
 */
public class Ghost extends Element{
    private final int points;

    //construtor
    public Ghost(String iconName){
        super(new String[]{iconName}, 0);
        this.isMortal = true;
        points = 200;
    }

    //métodos
    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, this.imageIcon, pos.getY(), pos.getX());
    }
}
