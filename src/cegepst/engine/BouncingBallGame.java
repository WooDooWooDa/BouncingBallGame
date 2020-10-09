package cegepst.engine;

import cegepst.Ball;

import java.awt.*;

public class BouncingBallGame extends Game {

    private final Ball smallBall;
    private final Ball bigBall;
    private int score = 0;

    public BouncingBallGame() {
        smallBall = new Ball(20, 5);
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
    public void draw(Buffer buffer) {
        bigBall.draw(buffer, Color.RED);
        smallBall.draw(buffer, Color.GREEN);

        buffer.drawText("score : " + score, 10, 20, Color.white);
        buffer.drawText("FPS : " + GameTime.getCurrentFps(), 10, 40, Color.white);
        buffer.drawText(GameTime.getElapsedFormattedTime(), 10, 60, Color.white);
    }

}
