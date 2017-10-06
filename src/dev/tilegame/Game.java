package dev.tilegame;

import dev.tilegame.display.Display;
import dev.tilegame.gfx.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{

    //Variable declarations
    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;


    //Game constructor
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    //initializes graphics
    private void init() {
        display = new Display(title, width, height);

    }

    //1st main action of loop
    private void tick() {

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





        //STOP DRAWING
        bs.show();
        g.dispose();

    }

    //from Runnable impl
    public void run() {

        init();

        while(running) {
            tick();
            render();
        }

        stop();

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
