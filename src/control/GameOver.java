/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import scene.Scene;
import utils.Consts;

/**
 *
 * @author lllgabrielll
 */
public class GameOver extends Scene {

    private Image imgStart;
    private Image background;

    public GameOver(String[] imgs) {
        try {
            this.imgStart = Toolkit.getDefaultToolkit().getImage(
                    new java.io.File(".").getCanonicalPath() + Consts.PATH + imgs[0]);
            this.background = Toolkit.getDefaultToolkit().getImage(
                    new java.io.File(".").getCanonicalPath() + Consts.PATH + imgs[1]);
        } catch (IOException e) {
            System.err.println("Erro: Imagens da tela inicial não encontradas\n " + e.getMessage());
        }
    }

    @Override
    public void paintScene(Graphics g) {
        int aux = Consts.NUM_CELLS * Consts.CELL_SIZE + 5;
        g.drawImage(background, 0, 0, aux, aux, null);
        g.drawImage(imgStart, (aux / 2) - 150, 500, 300, 100, null);
    }

    @Override
    protected void drawScene() {
    }

}
