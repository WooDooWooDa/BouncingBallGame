package cegepst.engine;

public abstract class Game {

    private boolean playing = true;

    private RenderingEngine renderingEngine;
    private GameTime gameTime;

    public Game() {
        renderingEngine = new RenderingEngine();
    }

    public void start() {
        initialize();
        run();
        conclude();
    }

    public void stop() {
        playing = false;
    }

    public abstract void initialize();
    public abstract void conclude();
    public abstract void update();
    public abstract void draw(Buffer buffer);

    private void run() {
        renderingEngine.start();
        gameTime = new GameTime();
        while (playing) {
            update();
            draw(renderingEngine.getRenderingBuffer());
            renderingEngine.renderBufferOnScreen();
            gameTime.synchronize();
        }
        renderingEngine.stop();
    }

}
