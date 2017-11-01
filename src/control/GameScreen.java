package control;

import elements.Element;
import elements.PacMan;
import elements.Ghost;
import elements.Wall;
import elements.Cherry;
import elements.Fruit;
import elements.Strawberry;
import utils.Consts;
import utils.Drawing;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Projeto de POO 2017
 *
 * @author Luiz Eduardo
 * Baseado em material do Prof. Jose Fernando Junior
 */
public class GameScreen extends javax.swing.JFrame implements KeyListener {

    private final PacMan pacMan;
    private final Ghost blinky;
    private final Ghost pinky;
    private final Ghost inky;
    private final Ghost clyde;
    private final Cherry cherry;
    private final Strawberry strawberry;
    private final ArrayList<Element> elemArray;
    private final GameController controller = new GameController();

    //construtor
    public GameScreen() {
        Drawing.setGameScreen(this);
        initComponents();

        this.addKeyListener(this);   /*teclado*/

        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                     Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().top + getInsets().bottom);

        elemArray = new ArrayList<>();

        /*Cria e adiciona elementos*/
        pacMan = new PacMan();
        pacMan.setPosition(0, 0);
        this.addElement(pacMan);
        
        blinky = new Ghost("blinky.png");
        blinky.setPosition(10, 12);
        this.addElement(blinky);
        
        pinky = new Ghost("pinky.png");
        pinky.setPosition(10, 8);
        this.addElement(pinky);
        
        inky = new Ghost("inky.png");
        inky.setPosition(8, 10);
        this.addElement(inky);
        
        clyde = new Ghost("clyde.png");
        clyde.setPosition(12, 10);
        this.addElement(clyde);
        
        cherry = new Cherry();
        
        strawberry = new Strawberry(); 
    }
    
    //métodos
    public final void addElement(Element elem) {
        elemArray.add(elem);
    }

    public void removeElement(Element elem) {
        elemArray.remove(elem);
    }
    
    public void controlFruit(Fruit fruit, int spawnTime){
        if(fruit.getState() == false){
            if(fruit.getCooldown() == 0){
                fruit.setState(true);
                fruit.setCooldown(Consts.TIMER_FRUIT/Consts.DELAY);
                fruit.spawnFruit();
                addElement(fruit);
            }
            else{
                fruit.decreaseCooldown();
            }
       }
       else if(fruit.getState() == true){
            if(fruit.getCooldown() == 0){
                fruit.setState(false);
                fruit.setCooldown(spawnTime/Consts.DELAY);
                removeElement(fruit);
            }
            else{
                fruit.decreaseCooldown();
            }
        }
    }

    @Override
    public void paint(Graphics gOld) {
        Graphics g = getBufferStrategy().getDrawGraphics();

        /*Criamos um contexto grafico*/
        Graphics g2 = g.create(getInsets().right, getInsets().top, getWidth() - getInsets().left, getHeight() - getInsets().bottom);

        /* DESENHA CENARIO
           Trocar essa parte por uma estrutura mais bem organizada
           Utilizando a classe Stage
        */
        for (int i = 0; i < Consts.NUM_CELLS; i++) {
            for (int j = 0; j < Consts.NUM_CELLS; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "fundo.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIZE, i * Consts.CELL_SIZE, Consts.CELL_SIZE, Consts.CELL_SIZE, null);

                } catch (IOException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
        
        this.controller.drawAllElements(elemArray, g2);
        this.controller.processAllElements(elemArray);
        this.setTitle("-> Cell: " + pacMan.getStringPosition());

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go(){
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                controlFruit(cherry, Consts.SPAWN_CHERRY);
                controlFruit(strawberry, Consts.SPAWN_STRAWBERRY);
                repaint();     
            }
        };   
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.DELAY);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                pacMan.setMovDirection(PacMan.MOVE_UP);
                pacMan.changeDirection(3);
                break;
        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
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
