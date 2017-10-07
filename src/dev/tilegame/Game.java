package dev.tilegame;

import dev.tilegame.display.Display;
import dev.tilegame.gfx.*;
import dev.tilegame.input.KeyManager;
import dev.tilegame.states.GameState;
import dev.tilegame.states.MenuState;
import dev.tilegame.states.SettingsState;
import dev.tilegame.states.State;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game implements Runnable{

    //Variable declarations
    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gameState;
    private State menuState;
    private State settingsState;

    //Input
    private KeyManager keyManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;


    //Game constructor
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    //initializes graphics
    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);


        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        settingsState = new SettingsState(handler);
        State.setState(gameState);


    }

    //1st main action of loop
    private void tick() {
        keyManager.tick();
        if(State.getState() != null)
            State.getState().tick();
    }

    //2nd main action of loop
    private void render() {
        //buffer is imaginary screen right before actual screen
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0,0, width, height);
        //DRAW HERE

        if(State.getState() != null)
            State.getState().render(g);



        //STOP DRAWING
        bs.show();
        g.dispose();

    }

    //from Runnable impl
    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        //Game Loop
        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                delta--;
            }
        }

        stop();

    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    //starts thread loop & run
    public synchronized void start() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    //stops thread loop & run
    public synchronized void stop() {
        if(!running)
            return;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
