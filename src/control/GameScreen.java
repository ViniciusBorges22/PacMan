package control;

import elements.Blinky;
import elements.PacMan;
import elements.Element;
import elements.Cherry;
import elements.Clyde;
import elements.Inky;
import elements.Pinky;
import elements.Strawberry;

import utils.Consts;
import utils.Drawing;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import scene.InitScene;

import scene.Scene;
import scene.Scene1;
import scene.Scene2;
import scene.Scene3;

public class GameScreen extends JFrame implements KeyListener, MouseListener {

    private final PacMan pacMan;

    private final Blinky blinky;
    private final Clyde clyde;
    private final Inky inky;
    private final Pinky pinky;

    private final Strawberry strawberry;
    private final Cherry cherry;

    private final ArrayList<Element> elemArray;
    private final GameController controller = new GameController();

    private Scene scene;

    // Controle de tela
    // 0 - Tela inicial
    // 1 - Primeira tela
    // 2 - Segunda tela
    // 3 - Terceira tela
    // 4 - Tela de fim do jogo
    private int controlScene;

    // Construtor
    public GameScreen() {
        Drawing.setGameScreen(this);
        initComponents();

        this.addKeyListener(this);
        this.addMouseListener(this);

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

        // Cherry
        this.cherry = new Cherry();
        this.cherry.setPosition(Math.round(Math.random() * Consts.NUM_CELLS),
                Math.round(Math.random() * Consts.NUM_CELLS));

        // Blinky
        this.blinky = new Blinky();
        this.blinky.setPosition(10, 10);

        // Clyde
        this.clyde = new Clyde();
        this.clyde.setPosition(10, 10);

        // Inky
        this.inky = new Inky();
        this.inky.setPosition(10, 10);

        // Pinky
        this.pinky = new Pinky();
        this.pinky.setPosition(10, 10);

        // Tela inicial
        this.controlScene = 1;
        newScene(controlScene);
    }

    // Cria cenario com todos os seus elementos
    private void newScene(final int sc) {
        switch (sc) {
            // Tela Inicial
            case 0:
                this.scene = new InitScene(new String[]{"button_start.png",
                    "button_start.png", "background_pacman1.jpg"});
                break;

            // Tela 1
            case 1:
                this.scene = new Scene1();
                this.scene.setBlock("brick.png");

                // Determinar posição strawberry
                int aux1,
                 aux2;
                do {
                    aux1 = Math.round((float) Math.random() * Consts.NUM_CELLS);
                    aux2 = Math.round((float) Math.random() * Consts.NUM_CELLS);
                } while (scene.map(aux1, aux2) == 1);
                this.strawberry.setPosition(aux1, aux2);
                this.addElement(strawberry);

                // Determinar posição cherry
                do {
                    aux1 = Math.round((float) Math.random() * Consts.NUM_CELLS);
                    aux2 = Math.round((float) Math.random() * Consts.NUM_CELLS);
                } while (scene.map(aux1, aux2) == 1);
                this.cherry.setPosition(aux1, aux2);
                this.addElement(cherry);
                break;

            // Tela 2
            case 2:
                this.scene = new Scene2();
                this.scene.setBlock("brick.png");
                break;

            // Tela 3
            case 3:
                this.scene = new Scene3();
                this.scene.setBlock("brick.png");
                break;

            // Game Over
            case 4:
                this.scene = new GameOver(new String[]{"button_start.png",
                    "background_game_over.jpg"});
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

        // Se estiver na primeira ou ultima tela, não é necessario desenhar todo os elementos
        if (controlScene == 0 || controlScene == 4) {
            // Desenhar tela inicial
            scene.paintScene(g);
        } else {
            // Desenha todos os elementos
            this.controller.drawAllElements(scene, elemArray, g);

            // Verificar colisao entre elementos
            this.controller.processAllElements(scene, elemArray);
        }

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
        TimerTask timerStrawberry = new TimerTask() {
            @Override
            public void run() {
                if (!strawberry.isVisible()) {
                    // Determinar uma nova posição para strawberry
                    int aux1, aux2;
                    do {
                        aux1 = Math.round((float) Math.random() * Consts.NUM_CELLS);
                        aux2 = Math.round((float) Math.random() * Consts.NUM_CELLS);
                    } while (scene.map(aux1, aux2) == 1);
                    
                    strawberry.setPosition(aux1, aux2);
                    
                    // Deixar fruta visivel
                    strawberry.setVisible(true);
                    strawberry.setTransposable(false);
                
                } else {
                    strawberry.setVisible(false);
                    strawberry.setTransposable(true);
                }
            }
        };

        // Time para cherry
        TimerTask timerCherry = new TimerTask() {
            @Override
            public void run() {
                if (!cherry.isVisible()) {
                    // Determinar uma nova posição para cherry
                    int aux1, aux2;
                    do {
                        aux1 = Math.round((float) Math.random() * Consts.NUM_CELLS);
                        aux2 = Math.round((float) Math.random() * Consts.NUM_CELLS);
                    } while (scene.map(aux1, aux2) == 1);
                    
                    strawberry.setPosition(aux1, aux2);
                    
                    // Deixar fruta visivel
                    cherry.setVisible(true);
                    cherry.setTransposable(false);

                } else {
                    cherry.setVisible(false);
                    cherry.setTransposable(true);
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(repaint, 0, Consts.DELAY);
        timer.schedule(timerStrawberry, Consts.TIMER_STRAWBERRY, 2000);
        timer.schedule(timerCherry, Consts.TIMER_CHERRY, 30000);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int aux = controlScene;
        switch (aux) {
            // Tela Inicial
            case 0:
                // Iniciar Jogo
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    controlScene = 1;
                    newScene(controlScene);
                } // Fim fo jogo 
                else if (e.getKeyCode() == KeyEvent.VK_Q) {
                    if (JOptionPane.showConfirmDialog(null,
                            "Deseja realmente sair ?", "Sair", JOptionPane.YES_NO_OPTION) == 0) {
                        System.exit(0);
                    }
                }
                break;

            // Tela Final
            case 4:
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    if (JOptionPane.showConfirmDialog(null,
                            "Deseja realmente sair ?", "Sair", JOptionPane.YES_NO_OPTION) == 0) {
                        System.exit(0);
                    }
                }
                break;

            // Qualquer outra tela
            default:
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

                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int aux = controlScene;
        switch (aux) {
            // Tela inicial
            case 0:
                // Verifica se clicou em algum botao
                int a1 = (Consts.NUM_CELLS * Consts.CELL_SIZE) / 2;
                int x1 = e.getPoint().x;
                int y1 = e.getPoint().y;

                if ((200 <= y1 && y1 <= 300) && (a1 - 150 <= x1 && x1 <= a1 + 150)) {
                    controlScene = 1;
                    newScene(controlScene);
                } else if ((340 <= y1 && y1 <= 440) && (a1 - 150 <= x1 && x1 <= a1 + 150)) {
                    if (JOptionPane.showConfirmDialog(null,
                            "Deseja realmente sair ?", "Sair", JOptionPane.YES_NO_OPTION) == 0) {
                        System.exit(0);
                    }
                }

                break;

            // Game Over
            case 4:
                // Verifica se clicou em algum botao
                int a2 = (Consts.NUM_CELLS * Consts.CELL_SIZE) / 2;
                int x2 = e.getPoint().x;
                int y2 = e.getPoint().y;

                // Volta para a tela inicial
                if ((500 <= y2 && y2 <= 600) && (a2 - 150 <= x2 && x2 <= a2 + 150)) {
                    controlScene = 0;
                    newScene(controlScene);
                }
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

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
