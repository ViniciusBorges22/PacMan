/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import elements.Ball;
import elements.PowerPellet;
import java.awt.Graphics;
import java.util.Iterator;
import utils.Consts;

public class Scene1 extends Scene {

    public Scene1() {
        this.drawSceneFinal();
    }

    @Override
    public void paintScene(Graphics g) {
        g.fillRect(0, 0, Consts.CELL_SIZE * Consts.NUM_CELLS, Consts.CELL_SIZE * Consts.NUM_CELLS + 50);
        
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
        
        Iterator<PowerPellet> it2 =  powerPellet.listIterator();
        while (it2.hasNext()){
            it2.next().autoDraw(g);
        }
        
        // Calcula a quantidade de pontos nessa fase
        points = (tballs - balls.size()) * 10;
    }

    @Override
    protected void drawSceneFinal() {
        // Criar bolinhas
        for (int x = 1; x < Consts.NUM_CELLS-1; x++) {
            for (int y = 1; y < Consts.NUM_CELLS-1; y++) {
                if (map[x][y] == 0) {
                    this.balls.add(new Ball("ball.png", 10, x, y));  
                    if(x == 2 && y == 2){
                        this.powerPellet.add(new PowerPellet("fire.png", 10, x, y));
                    }
                }
            }
        }
        map[1][1] = 0;
        
        // Total de bolinhas na tela
        tballs = balls.size();
    }
}
