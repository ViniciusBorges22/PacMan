package control;

import elements.Blinky;
import elements.PacMan;
import elements.Element;
import elements.Cherry;
import elements.Clyde;
import elements.Enemy;
import elements.Inky;
import elements.Pinky;
import elements.Strawberry;

import utils.Consts;
import utils.Drawing;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import scene.GameOver;
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
    private final ArrayList<Enemy> enemys;

    private final GameController controller = new GameController();
    private final Random random = new Random();
    private final Executor executor_scene_1;

    private Scene scene;

    private Image imgLife;

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

        this.setSize(Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().left + getInsets().right,
                Consts.NUM_CELLS * Consts.CELL_SIZE + getInsets().top + getInsets().bottom + 50);

        // Lista de elementos
        this.elemArray = new ArrayList<>();
        this.enemys = new ArrayList<>();

        this.executor_scene_1 = Executors.newCachedThreadPool();

        // Pacman
        this.pacMan = new PacMan();
        this.pacMan.setPosition(1, 1);
        this.addElement(pacMan);

        // Blinky
        this.blinky = new Blinky();
        this.blinky.setPosition(10, 10);
        this.elemArray.add(blinky);
        this.enemys.add(blinky);

        // Clyde
        this.clyde = new Clyde();
        this.clyde.setPosition(10, 10);
        this.enemys.add(clyde);

        // Inky
        this.inky = new Inky();
        this.inky.setPosition(10, 10);
        this.enemys.add(inky);

        // Pinky
        this.pinky = new Pinky();
        this.pinky.setPosition(10, 10);
        this.enemys.add(pinky);

        this.strawberry = new Strawberry();
        this.cherry = new Cherry();

        // Vida
        try {
            this.imgLife = Toolkit.getDefaultToolkit().getImage(
                    new File(".").getCanonicalPath() + Consts.PATH + "pacman_right.png");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Cria cenario
        this.controlScene = 1;
        newScene(controlScene);
    }

    // Cria cenario com todos os seus elementos
    private void newScene(int scene) {
        switch (scene) {
            // Tela Inicial
            case 0:
                this.scene = new InitScene();

                // Total de vidas do pacman
                this.pacMan.setLife(3);
                break;

            // Tela 1
            case 1:
                this.scene = new Scene1();
                this.scene.setBlock("brick.png");

                // Determinar posição para strawberry
                int aux1,
                 aux2;
                do {
                    aux1 = random.nextInt(Consts.NUM_CELLS - 1);
                    aux2 = random.nextInt(Consts.NUM_CELLS - 1);
                } while (this.scene.map(aux1, aux2) == 1);

                this.strawberry.setPosition(aux1, aux2);
                this.addElement(strawberry);

                // Determinar posição para cherry
                do {
                    aux1 = random.nextInt(Consts.NUM_CELLS - 1);
                    aux2 = random.nextInt(Consts.NUM_CELLS - 1);
                } while (this.scene.map(aux1, aux2) == 1);

                this.cherry.setPosition(aux1, aux2);
                this.addElement(cherry);

                // Thread para a animação do pacman
                this.executor_scene_1.execute(pacMan);
                this.blinky.setMap(this.scene.getMap());
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
                this.scene = new GameOver();
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
        this.controller.drawAllElements(scene, elemArray, g2, controlScene);

        // Se nao for a tela inicial nem a final
        if (controlScene != 0 && controlScene != 4) {

            // Verifica movimentação do blinky
            switch (pacMan.getMovDirection()) {
                case PacMan.MOVE_DOWN:
                    if (pacMan.getPos().getX() > blinky.getPos().getX()) {
                        blinky.setMoveDirection(Enemy.MOVE_DOWN);
                    } else {
                        blinky.setMoveDirection(Enemy.MOVE_UP);
                    }
                    break;

                case PacMan.MOVE_UP:
                    if (pacMan.getPos().getX() > blinky.getPos().getX()) {
                        blinky.setMoveDirection(Enemy.MOVE_DOWN);
                    } else {
                        blinky.setMoveDirection(Enemy.MOVE_UP);
                    }
                    break;

                case PacMan.MOVE_LEFT:
                    if (pacMan.getPos().getY() > blinky.getPos().getY()) {
                        blinky.setMoveDirection(Enemy.MOVE_RIGHT);
                    } else {
                        blinky.setMoveDirection(Enemy.MOVE_LEFT);
                    }
                    break;

                case PacMan.MOVE_RIGHT:
                    if (pacMan.getPos().getY() > blinky.getPos().getY()) {
                        blinky.setMoveDirection(Enemy.MOVE_RIGHT);
                    } else {
                        blinky.setMoveDirection(Enemy.MOVE_LEFT);
                    }
                    break;
            }

            // Verificar colisao entre elementos
            if (controller.processAllElements(scene, elemArray, enemys)) {

                // Remove uma vida do pacman
                pacMan.removeLife();

                // Verifica se acabou as vidas
                if (pacMan.getLife() == 0) {
                    this.controlScene = 4;
                    newScene(controlScene);
                }
            }

            // Desenhar informações
            int aux = Consts.CELL_SIZE * Consts.NUM_CELLS;

            // Vidas
            for (int i = 0; i < pacMan.getLife(); i++) {
                g2.drawImage(imgLife, 10 + (32 * i), aux + 10, 30, 30, null);
            }

            // Frutas
            if (elemArray.contains(strawberry)) {
                g2.drawImage(strawberry.getImgElement().getImage(), 140, aux + 7, 30, 33, null);
            }

            if (elemArray.contains(cherry)) {
                g2.drawImage(cherry.getImgElement().getImage(), 180, aux + 7, 30, 33, null);
            }
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
                if (controlScene != 0 && controlScene != 4) {
                    if (!strawberry.isVisible()) {

                        // Determinar uma nova posição para strawberry
                        // a cada nova aparição
                        int aux1, aux2;
                        do {
                            aux1 = random.nextInt(Consts.NUM_CELLS - 1);
                            aux2 = random.nextInt(Consts.NUM_CELLS - 1);
                        } while (scene.map(aux1, aux2) == 1);

                        strawberry.setPosition(aux1, aux2);

                        // Deixar fruta visivel
                        strawberry.setVisible(true);
                        strawberry.setTransposable(false);

                    } else {

                        // Deixar fruta invisivel
                        strawberry.setVisible(false);
                        strawberry.setTransposable(true);
                    }
                }
            }
        };

        // Time para cherry
        TimerTask timerCherry = new TimerTask() {
            @Override
            public void run() {
                if (controlScene != 0 && controlScene != 4) {
                    if (!cherry.isVisible()) {

                        // Determinar uma nova posição para cherry
                        // a cada nova aparição
                        int aux1, aux2;
                        do {
                            aux1 = random.nextInt(Consts.NUM_CELLS - 1);
                            aux2 = random.nextInt(Consts.NUM_CELLS - 1);
                        } while (scene.map(aux1, aux2) == 1);

                        strawberry.setPosition(aux1, aux2);

                        // Deixar fruta visivel
                        cherry.setVisible(true);
                        cherry.setTransposable(false);

                    } else {

                        // Deixar fruta invisivel
                        cherry.setVisible(false);
                        cherry.setTransposable(true);
                    }
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
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    controlScene = 1;
                    newScene(controlScene);
                } else if (e.getKeyCode() == KeyEvent.VK_Q) {
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
                        // Setar movimentação do pacman
						pacMan.setTurn(true);
						pacMan.setNextDirection(PacMan.MOVE_UP);
                        //pacMan.setMovDirection(PacMan.MOVE_UP);
                        //pacMan.changeDirection(4);
                        break;

                    case KeyEvent.VK_DOWN:
                        // Setar movimentação do pacman
						pacMan.setTurn(true);
						pacMan.setNextDirection(PacMan.MOVE_DOWN);
                        //pacMan.setMovDirection(PacMan.MOVE_DOWN);
                        //pacMan.changeDirection(2);
                        break;

                    case KeyEvent.VK_LEFT:
                        // Setar movimentaçao do pacman
                        pacMan.setTurn(true);
						pacMan.setNextDirection(PacMan.MOVE_LEFT);
						//pacMan.setMovDirection(PacMan.MOVE_LEFT);
                        //pacMan.changeDirection(3);
                        break;

                    case KeyEvent.VK_RIGHT:
                        // Setar movimentação do pacman
                        pacMan.setTurn(true);
						pacMan.setNextDirection(PacMan.MOVE_RIGHT);
						//pacMan.setMovDirection(PacMan.MOVE_RIGHT);
                        //pacMan.changeDirection(0);
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
    public void mouseClicked(MouseEvent me) {
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

                if ((100 <= y1 && y1 <= 200) && (a1 - 150 <= x1 && x1 <= a1 + 150)) {
                    controlScene = 1;
                    newScene(controlScene);
                } else if ((240 <= y1 && y1 <= 340) && (a1 - 150 <= x1 && x1 <= a1 + 150)) {
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
                if ((350 <= y2 && y2 <= 450) && (a2 - 150 <= x2 && x2 <= a2 + 150)) {
                    controlScene = 0;
                    newScene(controlScene);
                }
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
