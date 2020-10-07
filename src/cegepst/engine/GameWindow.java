package cegepst.engine;

import cegepst.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameWindow extends JFrame {

    private static final int SLEEP = 20;
    private long before;

    private final JPanel panel;
    private final int windowWidth = 800;
    private final int windowHeight = 600;
    private boolean playing = true;

    private Ball smallBall;
    private Ball bigBall;
    private int score = 0;

    private BufferedImage bufferedImage;
    private Graphics2D buffer;

    public GameWindow() {
        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);
        setResizable(false);    //empeche la redimension
        setTitle("Bouncing Ball Game");     // titre de la window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //programme le bouton X pour quitter le programme
        //setUndecorated(true); enleve la bar en haut

        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        add(panel);

        smallBall = new Ball(20, 4);
        bigBall = new Ball(50, 2);
    }

    public void start() {
        setVisible(true);   //affichage de la fenetre
        before = System.currentTimeMillis();
        while (playing) {
            bufferedImage = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
            buffer = bufferedImage.createGraphics();
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            buffer.setRenderingHints(rh);

            update();
            drawOnBuffer();
            drawOnScreen();

            long sleep = SLEEP - (System.currentTimeMillis() - before);
            if (sleep < 0) {
                sleep = 4;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            before = System.currentTimeMillis();
        }
    }

    private void update() {
        smallBall.update();
        bigBall.update();
        if (smallBall.hasTouchBound()) {
            score += 20;
        }
        if (bigBall.hasTouchBound()) {
            score += 10;
        }
    }

    private void drawOnBuffer() {
        smallBall.draw(buffer, Color.RED);

        bigBall.draw(buffer, Color.BLUE);

        buffer.setPaint(Color.WHITE);
        buffer.drawString("score : " + score, 10, 20);
    }

    private void drawOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0,0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }
}
