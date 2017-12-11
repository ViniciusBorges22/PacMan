/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author lllgabrielll
 */
public class InitScene extends Scene {

    private Point pStart;
    private Point pExit;
    private ImageIcon imgStart;
    private ImageIcon imgExit;

    public InitScene(String[] imgs) {

    }

    @Override
    public void paintScene(Graphics g) {
    }

    @Override
    protected void drawSceneFinal() {
    }

    // Botao de sair
    public Point getpExit() {
        return pExit;
    }

    // Botao de Iniciar
    public Point getpStart() {
        return pStart;
    }

}
