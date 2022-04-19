
package com.javasupremacy.hardmode.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.utils.Constant;

import java.util.ArrayList;
import java.util.List;


public class PlayerShip implements Controllable{

    public float movementSpeed;

    private boolean isSlow, isthrow;

    private Texture spaceship, manmode, ghostmode;
    private Rectangle hitbox, manbox, ghostbox;

    private float shootInterval = 0.2f;
    private float bombInterval = 2.5f;
    private float shootTimestamp = 0;

    private float slowMultiplier=0.5f;

    private List<PlayerBullet> bulletList;
    private List<PlayerSpecialBomb> bomblist;

    public PlayerShip(List<PlayerBullet> bulletList, List<PlayerSpecialBomb> bomblist) {
        this.movementSpeed = 4f;
        float width = 20;
        float height = 40;
        float x = (Constant.WINDOW_WIDTH - width) / 2;
        float y = 50;
        this.hitbox = new Rectangle(x, y, width, height);
        this.manbox = this.hitbox;
        this.ghostbox = new Rectangle(hitbox.x, hitbox.y, 40, 60);
        this.manmode = new Texture("man.png");
        this.ghostmode = new Texture("ghost.png");
        this.spaceship = this.manmode;
        this.bulletList = bulletList;
        this.bomblist = bomblist;
        this.isthrow = false;
    }

    /**
     *
     * @return true if playership is firing
     */
    public boolean isFiring() {
        boolean canFire = false;
        if (shootTimestamp >= shootInterval) {
            canFire = true;
        }
        return Gdx.input.isKeyPressed(Constant.FIRE) && canFire;
    }

    public void update(float deltaTime) {
        shootTimestamp += deltaTime;
    }

    /**
     * switch slow mode
     * @param isSlow
     */
    @Override
    public void slowMode(boolean isSlow) {
        this.isSlow = isSlow;
    }

    @Override
    public void moveUp() {
        hitbox.y = Math.min(hitbox.y + this.getSpeed(), Constant.WINDOW_HEIGHT- hitbox.height);
        manbox.y = hitbox.y;
        ghostbox.y = hitbox.y;
    }

    @Override
    public void moveDown() {
        hitbox.y = Math.max(hitbox.y - this.getSpeed(), 0);
        manbox.y = hitbox.y;
        ghostbox.y = hitbox.y;
    }

    @Override
    public void moveLeft() {
        hitbox.x = Math.max(hitbox.x - this.getSpeed(), 0);
        manbox.x = hitbox.x;
        ghostbox.x = hitbox.x;
    }

    @Override
    public void moveRight() {
        hitbox.x = Math.min(hitbox.x + this.getSpeed(), Constant.WINDOW_WIDTH - hitbox.width);
        manbox.x = hitbox.x;
        ghostbox.x = hitbox.x;
    }

    @Override
    public void fire() {
        if (shootTimestamp >= shootInterval) {
            shootTimestamp = 0;
            this.bulletList.add(new PlayerBullet.Builder(new Texture("bulletBeige.png"))
                    .hitbox(new Rectangle(hitbox.x + (hitbox.width / 2),  hitbox.y + hitbox.height, 12, 26))
                    .speed(400)
                    .direction(0, 1)
                    .build());
        }
    }

    @Override
    public void throwBomb() {
        if (shootTimestamp >= bombInterval) {
            shootTimestamp = 0;
            isthrow = true;
            this.bomblist.add(new PlayerSpecialBomb.Builder(new Texture("specialBomb.png"))
                    .hitbox(new Rectangle(hitbox.x - (hitbox.width/3), hitbox.y + hitbox.height, 30, 30))
                    .speed(200)
                    .direction(0, 1)
                    .build());
        }
    }

    public boolean getIsThrow(){ return this.isthrow;}

    public void setIsThrow(boolean isthrow) {this.isthrow=isthrow;}

    private float getSpeed() {
        float speed = this.movementSpeed;
        if (isSlow) {
            speed *= this.slowMultiplier;
        }
        return speed;
    }

    public void draw(Batch batch, float deltaTime) {
        update(deltaTime);
        batch.draw(spaceship, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public boolean overlaps(Rectangle other) {
        return this.hitbox.overlaps(other);
    }

    public void changeMode(boolean isCheating){
        if(isCheating){
            this.hitbox = this.ghostbox;
            this.spaceship = this.ghostmode;
        }
        else{
            this.hitbox = this.manbox;
            this.spaceship = this.manmode;
        }
    }
}

