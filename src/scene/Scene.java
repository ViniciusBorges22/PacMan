/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import elements.Ball;
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
    
    // Total de bolinhas
    protected int tballs;
    
    // Pontos da fase
    protected int points;
    
    // Bloco
    protected Image brick;

    public Scene() {
        this.map = new int[Consts.NUM_CELLS][Consts.NUM_CELLS];
        this.balls = new ArrayList<>();
        this.points = 0;
    }
    
    // Imagem
    public void setBlock(String imageName) {
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
    
    public int map(int x, int y) {
        return map[x][y];
    }
    
    // Pintar cenario
    public abstract void paintScene(Graphics g);

    // Termina de definir cenario
    protected abstract void drawScene();

    // Obter elementos bolinhas
    public List<Ball> getBalls() {
        return balls;
    }

    // Obter total de bolinhas
    public int getTotalBall() {
        return this.balls.size();
    }
    
    // Obter total de pontos
    public int getPoints() {
        return points;
    }
    
}
