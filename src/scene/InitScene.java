/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import utils.Consts;

/**
 *
 * @author lllgabrielll
 */
public class InitScene extends Scene {

    private Image imgStart;
    private Image imgExit;

    public InitScene(String[] imgs) {
        try {
            this.imgStart = Toolkit.getDefaultToolkit().getImage(
                    new java.io.File(".").getCanonicalPath() + Consts.PATH + imgs[0]);
            this.imgExit = Toolkit.getDefaultToolkit().getImage(
                    new File(".").getCanonicalPath() + Consts.PATH + imgs[1]);
        } catch (IOException e) {
            System.err.println("Erro: Imagens da tela inicial não encontradas\n " + e.getMessage());
        }
    }

    @Override
    public void paintScene(Graphics g) {
        int aux = Consts.NUM_CELLS * Consts.CELL_SIZE;
        g.fillRect(0, 0, aux, aux);
        g.drawImage(imgStart, (aux / 2) - 150, 200, 300, 90, null);
        g.drawImage(imgExit, (aux / 2) - 150, 320, 300, 90, null);
    }

    @Override
    protected void drawScene() {
    }

}
