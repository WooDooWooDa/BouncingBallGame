package cegepst.engine;

import cegepst.Ball;

import java.awt.*;

public class Game {

    private static final int SLEEP = 25;
    private long before;

    private RenderingEngine renderingEngine;

    private boolean playing = true;
    private Ball smallBall;
    private Ball bigBall;
    private int score = 0;

    public Game() {
        renderingEngine = new RenderingEngine();

        smallBall = new Ball(20, 4);
        bigBall = new Ball(50, 2);
    }

    public void start() {
        renderingEngine.start();
        updateSyncTime();
        while (playing) {
            update();
            drawOnBuffer(renderingEngine.getRenderingBuffer());
            renderingEngine.renderBufferOnScreen();
            sleep();
        }
        renderingEngine.stop();
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

    private void drawOnBuffer(Graphics2D buffer) {
        bigBall.draw(buffer, Color.BLUE);
        smallBall.draw(buffer, Color.RED);

        buffer.setPaint(Color.WHITE);
        buffer.drawString("score : " + score, 10, 20);
    }

    private void sleep() {
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateSyncTime();
    }

    private long getSleepTime() {
        long sleep = SLEEP - (System.currentTimeMillis() - before);
        if (sleep < 0) {
            sleep = 4;
        }
        return sleep;
    }

    private void updateSyncTime() {
        before = System.currentTimeMillis();
    }
}
