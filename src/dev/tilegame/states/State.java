package dev.tilegame.states;
import dev.tilegame.Game;

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

    public State(Game game){
        this.game = game;
    }

    //abstract methods
    public abstract void tick();
    public abstract void render(Graphics g);

}
