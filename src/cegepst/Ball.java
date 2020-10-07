package cegepst;

import java.awt.*;
import java.util.Random;

public class Ball {

    private final int radius;
    private int x;
    private int y;
    private int velocityX;
    private int velocityY;

    public Ball(int radius, int speed) {
        this.radius = radius;

        x = getRandom(0 + (radius * 2), 800 - (radius * 2));
        y = getRandom(0 + (radius * 2), 600 - (radius * 2));
        velocityX = getRandom(0, 1) == 0 ? speed : -(speed);
        velocityY = getRandom(0, 1) == 0 ? speed : -(speed);
    }

    public void draw(Graphics2D buffer, Paint color) {
        Random rand = new Random();
        Color randomColor = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        buffer.setPaint(randomColor);
        buffer.fillOval(x, y, radius * 2, radius * 2);
    }

    public void update() {
        x += velocityX;
        y += velocityY;
        if(hasTouchVerticalBound()) {
            velocityY *= -1;
        }
        if(hasTouchHorizontalBound()) {
            velocityX *= -1;
        }
    }

    public boolean hasTouchBound() {
        return hasTouchHorizontalBound() || hasTouchVerticalBound();
    }

    private boolean hasTouchHorizontalBound() {
        return x <= 0 || x >= 800 - radius;
    }

    private boolean hasTouchVerticalBound() {
        return y <= 0 || y >= 600 - radius;
    }

    private int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
