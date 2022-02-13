package io.github.nottoxicdev;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    // dev v: 9 | beta v: 0
    public static String v = GameMeta.ConstructGameMeta(1, 0, "dev", 9);
    public static String m = "";
    // Construct mod
    // GameMeta.ConstructModMeta("modName", 1, 0, "dev", 1);

    public static final int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    public static boolean showCollisionBoxes = false;

    public enum STATE {
        Menu,
        Game
    };

    public STATE gameState = STATE.Menu;
    public static String title = "";

    public Game() {
        handler = new Handler();
        menu = new Menu(this, handler);

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        if (m == "") {
            title = "Test game; " + v;
        } else {
            title = "Test game; " + v + " | " + m;
        }
        new Window(WIDTH, HEIGHT, title, this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);

        if (gameState == STATE.Game) {
            handler.addObject(new Player((float) WIDTH / 2 - 32, (float) HEIGHT / 2 - 32, ID.Player, handler));
        }

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
        if (gameState == STATE.Game) {
            hud.render(g);

        } else if (gameState == STATE.Menu) {
            menu.render(g);
        }
        g.dispose();
        bs.show();

    }

    private void tick() {
        if (gameState == STATE.Game) {
            handler.tick();
            hud.tick();
            spawner.tick();
        } else if (gameState == STATE.Menu) {
            menu.tick();

        }

    }

    public static float clamp(Float var, Float min, Float max) {
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
