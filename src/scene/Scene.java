/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import elements.Ball;
import elements.Fruit;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utils.Consts;

public abstract class Scene {

    // Mapa
    protected int map[][];
    
    // Lista de bolinhas
    protected List<Ball> balls;
    
    // Lista de frutas
    protected List<Fruit> fruits;

    // Bloco
    protected Image brick;
    
    public Scene(final String imageName) {
        this.map = new int[Consts.NUM_CELLS][Consts.NUM_CELLS];
        this.balls = new ArrayList<>();
        this.fruits = new ArrayList<>();

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

        // Imagem
        try {
            this.brick = Toolkit.getDefaultToolkit().getImage(
                    new java.io.File(".").getCanonicalPath() + Consts.PATH + imageName);
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    // Obter cenario
    public int[][] getMap() {
        return map;
    }
    
    // Pintar cenario
    public abstract void paintScene(Graphics g);

    // Termina de definir cenario
    protected abstract void drawSceneFinal();

    // Obter elementos bolinhas
    public List<Ball> getBalls() {
        return balls;
    }
    
    // Obter total de bolinhas
    public int getTotalBall() {
        return this.balls.size();
    }
    
}
