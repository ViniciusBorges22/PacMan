package control;

import elements.PacMan;
import elements.Element;
import elements.Cherry;
import elements.Strawberry;

import utils.Consts;
import utils.Drawing;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

import scene.Scene;
import scene.Scene1;
import scene.Scene2;
import scene.Scene3;

public class GameScreen extends JFrame implements KeyListener {

    private final PacMan pacMan;
    private final Strawberry strawberry;
    private final Cherry cherry;
    private final ArrayList<Element> elemArray;
    private final GameController controller = new GameController();

    private Scene scene;

    // Construtor
    public GameScreen() {
        Drawing.setGameScreen(this);
        initComponents();

        this.addKeyListener(this);

        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);

        // Lista de elementos
        this.elemArray = new ArrayList<>();

        // Pacman
        this.pacMan = new PacMan();
        this.pacMan.setPosition(1, 1);
        this.addElement(pacMan);

        // Strawberry
        this.strawberry = new Strawberry();
        this.strawberry.setPosition(Math.round(Math.random() * Consts.NUM_CELLS),
                Math.round(Math.random() * Consts.NUM_CELLS));
        this.addElement(strawberry);

        // Cherry
        this.cherry = new Cherry();
        this.cherry.setPosition(Math.round(Math.random() * Consts.NUM_CELLS),
                Math.round(Math.random() * Consts.NUM_CELLS));
        this.addElement(cherry);

        // Cria cenario 1
        newScene(1);
    }

    // Cria cenario com todos os seus elementos
    private void newScene(final int scene) {
        switch (scene) {
            // Tela 1
            case 1:
                this.scene = new Scene1("brick.png");
                break;

            // Tela 2
            case 2:
                this.scene = new Scene2("brick.png");
                break;

            // Tela 3
            case 3:
                this.scene = new Scene3("brick.png");
                break;
        }
    }

    // Adicionar elementos na lista
    public final void addElement(Element elem) {
        elemArray.add(elem);
    }

    // Remover elementos na lista
    public void removeElement(Element elem) {
        elemArray.remove(elem);
    }

    @Override
    public void paint(Graphics gOld) {
        Graphics g = getBufferStrategy().getDrawGraphics();
        Graphics g2 = g.create(getInsets().right, getInsets().top,
                getWidth() - getInsets().left, getHeight() - getInsets().bottom);

        // Pintar elementos
        this.controller.drawAllElements(scene, elemArray, g2);

        // Verificar colisao entre elementos
        this.controller.processAllElements(scene, elemArray);

        // Titulo da janela
        this.setTitle("-> Cell: " + pacMan.getStringPosition()
                + " Total de bolinhas: " + this.scene.getTotalBall());

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go() {
        // Time para pintar a tela
        TimerTask repaint = new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        };

        // Time para strawberry
        TimerTask eraseStrawberry = new TimerTask() {
            @Override
            public void run() {
                elemArray.remove(strawberry);
            }
        };

        // Time para cherry
        TimerTask eraseCherry = new TimerTask() {
            @Override
            public void run() {
                elemArray.remove(cherry);
            }
        };

        Timer timer = new Timer();
        timer.schedule(repaint, 0, Consts.DELAY);
        timer.schedule(eraseStrawberry, Consts.TIMER_STRAWBERRY);
        timer.schedule(eraseCherry, Consts.TIMER_CHERRY);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                pacMan.setMovDirection(PacMan.MOVE_UP);
                pacMan.changeDirection(3);
                break;
            case KeyEvent.VK_DOWN:
                pacMan.setMovDirection(PacMan.MOVE_DOWN);
                pacMan.changeDirection(1);
                break;
            case KeyEvent.VK_LEFT:
                pacMan.setMovDirection(PacMan.MOVE_LEFT);
                pacMan.changeDirection(2);
                break;
            case KeyEvent.VK_RIGHT:
                pacMan.setMovDirection(PacMan.MOVE_RIGHT);
                pacMan.changeDirection(0);
                break;
            case KeyEvent.VK_SPACE:
                pacMan.setMovDirection(PacMan.STOP);
                break;
            default:
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0604 - Pacman");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(20, 20));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
