package dev.tilegame.entity.creatures;
import dev.tilegame.Game;
import dev.tilegame.Handler;
import dev.tilegame.gfx.Animation;
import dev.tilegame.gfx.Assets;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animations
    private Animation animDown, animUp, animLeft, animRight;
    private BufferedImage curPosition;


    public Player(Handler handler, float x, float y) {
        super(handler, x,y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 22;
        bounds.y = 32;
        bounds.width = 19;
        bounds.height = 31;
        curPosition = Assets.player_down[0];

        //Animations
        animDown = new Animation(200, Assets.player_down);
        animUp = new Animation(200, Assets.player_up);
        animLeft = new Animation(200, Assets.player_left);
        animRight = new Animation(200, Assets.player_right);
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up) {
            yMove = -speed;
        }
        if(handler.getKeyManager().down) {
            yMove = speed;
        }
        if(handler.getKeyManager().left) {
            xMove = -speed;
        }
        if(handler.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0) {
            curPosition = Assets.player_left[0];
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            curPosition = Assets.player_right[0];
            return animRight.getCurrentFrame();
        } else if(yMove < 0) {
            curPosition = Assets.player_up[0];
            return animUp.getCurrentFrame();
        } else if(yMove > 0) {
            curPosition = Assets.player_down[0];
            return animDown.getCurrentFrame();
        } else {
            return curPosition;
        }

    }


}
