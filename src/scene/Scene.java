/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import elements.Element;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import utils.Consts;

public abstract class Scene {
    
    // Mapa
    protected int map[][];
    
    // Numero de frutas da tela
    protected int fruits;
    
    // Numero de bolinhas da tela
    protected int balls;
    
    // Bloco
    protected Image brick;
    
    public Scene(final String imageName) {
        this.map = new int[Consts.NUM_CELLS][Consts.NUM_CELLS];
        
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
        
        map[3][3] = 1;
        map[4][8] = 1;
        map[10][11] = 1;
        
        // Imagem
        try {
            this.brick = Toolkit.getDefaultToolkit().getImage(
                    new java.io.File(".").getCanonicalPath() + Consts.PATH + imageName);
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
        
        this.balls = 0;
    }
    
    public int[][] getMap() {
        return map;
    }

    public boolean overlap(final Element elem) {
        for (int y = 0; y < Consts.NUM_CELLS; y++) {
            for (int x = 0; x < Consts.NUM_CELLS; x++) {
                if (map[y][x] == 1) {
                    double x_aux = Math.abs(y - elem.getPos().getX());
                    double y_aux = Math.abs(x - elem.getPos().getY());
                    
                    if (x_aux < 0.9 && y_aux < 0.9) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    // Pintar cenario
    public abstract void paintScene(Graphics g);
    
    // Termina de definir cenario
    protected abstract void drawSceneFinal();
    
    // Total de bolinhas na tela
    public int getBalls() {
        return balls;
    }

    public void setTotalBalls(int balls) {
        this.balls = balls;
    }
    
    // Mapa das bolinhas na tela
    public int[][] getBallsMap() {
        int balls_map[][] = new int[Consts.NUM_CELLS][Consts.NUM_CELLS];
        
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                if (this.map[i][j] == 1) {
                    balls_map[i][j] = 0;
                } else {
                    balls_map[i][j] = 1;
                }
            }
        }
        
        // Posicao inicial do pacman
        balls_map[1][1] = 0;
        
        return balls_map;
    }

    // Total de frutas na tela
    public int getFruits() {
        return fruits;
    }
    
}
