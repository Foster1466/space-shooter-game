
package com.javasupremacy.hardmode.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.utils.Constant;
//import com.sun.org.apache.bcel.internal.Const;

import java.util.ArrayList;
import java.util.List;


public class PlayerShip {

    public float movementSpeed;

    public float xPosition, yPosition;
    float width, height;

    private boolean isSlow;

    private Texture spaceship;
    private Rectangle hitbox;

    private float shootInterval = 0.2f;
    private float shootTimestamp = 0;

    private float slowMultiplier=0.5f;

    private List<PlayerBullet> bulletList; // A reference to main game bullet list

    public PlayerShip(List<PlayerBullet> bulletList) {
        this.movementSpeed = 2f;
        this.width = 20;
        this.height = 40;
        this.xPosition = (Constant.WINDOW_WIDTH - width) / 2;
        this.yPosition = 50;
        this.spaceship = new Texture("man.png");
        this.bulletList = bulletList;
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
     * We will need more shooting patterns when power ups are introduced
     * @return
     */
    public List<PlayerBullet> fireBullet() {
        shootTimestamp = 0;
        List<PlayerBullet> bullets = new ArrayList<>();
        bullets.add(new PlayerBullet(xPosition + (width / 2), yPosition + height));
        return bullets;
    }

    /**
     * switch slow mode
     * @param isSLow
     */
    public void slowMode(boolean isSLow) {
        this.isSlow = isSlow;
    }

    public void moveUp() {
        this.yPosition = Math.min(yPosition + this.getSpeed(), Constant.WINDOW_HEIGHT- this.height);
    }

    public void moveDown() {
        this.yPosition = Math.max(yPosition - this.getSpeed(), 0);
    }

    public void moveLeft() {
        this.xPosition = Math.max(xPosition - this.getSpeed(), 0);
    }

    public void moveRight() {
        this.xPosition = Math.min(xPosition + this.getSpeed(), Constant.WINDOW_WIDTH - this.width);
    }

    public void fire() {
        if (shootTimestamp >= shootInterval) {
            shootTimestamp = 0;
            this.bulletList.add(new PlayerBullet(xPosition + (width / 2), yPosition + height));
        }
    }

    private float getSpeed() {
        float speed = this.movementSpeed;
        if (isSlow) {
            speed *= this.slowMultiplier;
        }
        return speed;
    }

    public void draw(Batch batch, float deltaTime) {
        update(deltaTime);
        batch.draw(spaceship, xPosition, yPosition, width, height);
    }
}

