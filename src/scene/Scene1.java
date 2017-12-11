/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import elements.Ball;
import java.awt.Graphics;
import java.util.Iterator;
import utils.Consts;

public class Scene1 extends Scene {
    
    public Scene1() {
        super();
        this.drawScene();
    }

    @Override
    public void paintScene(Graphics g) {
        // Desenha cenario
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                if (map[i][j] == 1) {
                    g.drawImage(brick, j * Consts.CELL_SIZE, i * Consts.CELL_SIZE,
                            Consts.CELL_SIZE, Consts.CELL_SIZE, null);
                } else {
                    g.fillRect(j * Consts.CELL_SIZE, i * Consts.CELL_SIZE,
                            Consts.CELL_SIZE, Consts.CELL_SIZE);
                }
            }
        }

        // Desenhar bolinhas da tela
        Iterator<Ball> it = balls.listIterator();
        while (it.hasNext()) {
            it.next().autoDraw(g);
        }
    }

    @Override
    protected void drawScene() {
        // Bordas
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                if (i == 0 || i == Consts.NUM_CELLS - 1 || j == 0 || j == Consts.NUM_CELLS - 1) {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        // Criar bolinhas
        map[13][11] = 1;
        map[13][12] = 1;
        map[13][13] = 1;
        map[13][14] = 1;
        map[13][15] = 1;
        map[13][16] = 1;
        map[13][17] = 1;
        map[13][18] = 1;

        map[16][11] = 1;
        map[16][12] = 1;
        map[16][13] = 1;
        map[16][14] = 1;
        map[16][15] = 1;
        map[16][16] = 1;
        map[16][17] = 1;
        map[16][18] = 1;

        map[14][11] = 1;
        map[14][18] = 1;
        map[15][11] = 1;
        map[15][18] = 1;

        map[1][1] = 1;

        for (int x = 0; x < Consts.NUM_CELLS; x++) {
            for (int y = 0; y < Consts.NUM_CELLS; y++) {
                if (map[x][y] == 0
                        && !(x > 13 && x < 16 && y > 11 && y < 19)) {
                    this.balls.add(new Ball("ball.png", 100, x, y));
                }
            }
        }

        map[1][1] = 0;
        map[13][14] = 3;
        map[13][15] = 3;
    }

}
