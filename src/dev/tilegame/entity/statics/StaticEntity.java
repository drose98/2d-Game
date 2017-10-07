package dev.tilegame.entity.statics;

import dev.tilegame.Handler;
import dev.tilegame.entity.Entity;

public abstract class StaticEntity extends Entity {


    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }


}
