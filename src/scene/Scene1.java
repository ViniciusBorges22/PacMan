/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import java.awt.Graphics;
import utils.Consts;

public class Scene1 extends Scene {
    
    public Scene1(final String imageName, final int fruits) {
        super(imageName);
        this.fruits = fruits;
    }
    
    @Override
    public void paintScene(Graphics g) {
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                if (map[i][j] == 1) {
                    g.drawImage(brick, j * Consts.CELL_SIZE, i * Consts.CELL_SIZE,
                            Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                }
                else {
                    g.fillRect(j * Consts.CELL_SIZE, i * Consts.CELL_SIZE, 
                            Consts.CELL_SIZE, Consts.CELL_SIZE);
                }
            }
        }
    }

    @Override
    protected void drawSceneFinal() {
        
    }
    
}
