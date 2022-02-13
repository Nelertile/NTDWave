package io.github.nottoxicdev;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    public static String v = GameMeta.ConstructGameMeta(1, 0, "dev", 6);
    public static String m = "";
    // Construct mod
    // GameMeta.ConstructModMeta("modName", 1, 0, "dev", 1);

    public static final int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Random r;

    public static boolean showCollisionBoxes = false;

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        String title = "";

        if (m == "") {
            title = "Test game; " + v;
        } else {
            title = "Test game; " + v + " | " + m;
        }
        hud = new HUD();
        spawner = new Spawn(handler, hud);
        r = new Random();

        new Window(WIDTH, HEIGHT, title, this);

        handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run() {
        // gameloop
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println(frames);
                frames = 0;
            }

        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        hud.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
