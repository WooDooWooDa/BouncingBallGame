package cegepst.engine;

import cegepst.Ball;

import java.awt.*;

public class BouncingBallGame extends Game {

    private final Ball smallBall;
    private final Ball bigBall;
    private int score = 0;

    public BouncingBallGame() {
        smallBall = new Ball(20, 4);
        bigBall = new Ball(50, 2);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        smallBall.update();
        bigBall.update();
        if (smallBall.hasTouchBound()) {
            score += 20;
        }
        if (bigBall.hasTouchBound()) {
            score += 10;
        }
    }

    @Override
    public void draw(Graphics2D buffer) {
        bigBall.draw(buffer, Color.BLUE);
        smallBall.draw(buffer, Color.GREEN);

        buffer.setPaint(Color.WHITE);
        buffer.drawString("score : " + score, 10, 20);
    }

}
