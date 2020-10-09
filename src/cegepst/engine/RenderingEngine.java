package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderingEngine {

    private JFrame frame;
    private JPanel panel;
    private BufferedImage bufferedImage;

    private final int windowWidth = 800;
    private final int windowHeight = 600;

    public RenderingEngine() {
        initializeFrame();
        initializePanel();
    }

    public void start() {
        frame.setVisible(true);
    }

    public void stop() {
        frame.dispose();
    }

    public Buffer getRenderingBuffer() {
        bufferedImage = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHints(getRenderingHints());
        return new Buffer(graphics);
    }

    public void renderBufferOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0,0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }

    private void initializeFrame() {
        frame = new JFrame();
        frame.setSize(windowWidth, windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);    //empeche la redimension
        frame.setTitle("Bouncing Ball Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //programme le bouton X pour quitter le programme
        //setUndecorated(true); enleve la bar en haut
    }

    private void initializePanel() {
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        frame.add(panel);
    }

    private RenderingHints getRenderingHints() {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }
}
