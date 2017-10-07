package dev.tilegame.states;
import dev.tilegame.Game;
import dev.tilegame.Handler;

import java.awt.*;

public abstract class State {

    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }
    public static State getState() {
        return currentState;
    }

    protected Game game;

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    //abstract methods
    public abstract void tick();
    public abstract void render(Graphics g);

}
