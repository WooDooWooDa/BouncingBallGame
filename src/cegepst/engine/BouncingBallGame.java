package cegepst.engine;

import cegepst.Ball;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BouncingBallGame extends Game {

    private ArrayList<Ball> balls;
    private int score = 0;
    private int newBallThreshold = 0;

    public BouncingBallGame() {
        balls = new ArrayList<>();
        balls.add(new Ball(20, 5));
    }

    @Override
    public void initialize() {

    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            ball.update();
            if (ball.hasTouchBound()) {
                score += 15;
                newBallThreshold += 15;
            }
        }
        if (newBallThreshold > 200) {
            Random rand = new Random();
            balls.add(new Ball(rand.nextInt(20) + 10, rand.nextInt(5) + 1));
            newBallThreshold = 0;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).draw(buffer);
        }

        buffer.drawText("score : " + score, 10, 20, Color.white);
        buffer.drawText("FPS : " + GameTime.getCurrentFps(), 10, 40, Color.white);
        buffer.drawText(GameTime.getElapsedFormattedTime(), 10, 60, Color.white);
        buffer.drawText("Balls : " + balls.size(), 10, 80, Color.white);
    }

}
