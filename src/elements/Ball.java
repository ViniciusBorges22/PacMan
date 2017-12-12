/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.awt.Graphics;
import utils.Consts;
import utils.Drawing;

public class Ball extends Element {

    protected int point;
   
    // Construtor
    public Ball(final String imageName, final int point) {
        super(new String[]{imageName}, 0, Consts.BALL_SIZE);
        this.point = point;
        this.isTransposable = false;
    }
    
    // Construtor
    public Ball(final String imageName, final int point, final double x, final double y) {
        super(new String[]{imageName}, 0, Consts.BALL_SIZE);
        this.point = point;
        this.isTransposable = false;
        this.setPosition(x, y);
    }

    @Override
    public void autoDraw(Graphics g) {
        Drawing.draw(g, imageIcon, pos.getY(), pos.getX());
    }

}
