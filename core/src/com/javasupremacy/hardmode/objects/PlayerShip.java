
package com.javasupremacy.hardmode.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.javasupremacy.hardmode.utils.Constant;
//import com.sun.org.apache.bcel.internal.Const;

import java.util.ArrayList;
import java.util.List;


public class PlayerShip {

    public float movementSpeed;

    public float xPosition, yPosition;
    float width, height;

    private Texture spaceship;

    private float shootInterval = 0.2f;
    private float shootTimestamp = 0;

    private float slowMultiplier=0.5f;


    public PlayerShip() {
        this.movementSpeed = 2f;
        this.width = 20;
        this.height = 40;
        this.xPosition = (Constant.WINDOW_WIDTH - width) / 2;
        this.yPosition = 50;
        this.spaceship = new Texture("man.png");
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
        return Gdx.input.isKeyPressed(Constant.SPACE) && canFire;
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
    public void move(){

        float actualSpeed = movementSpeed;
        if (Gdx.input.isKeyPressed(Constant.SLOW_MODE))
        {
            actualSpeed *= slowMultiplier;
        }

        if (Gdx.input.isKeyPressed(Constant.UP)) {
            yPosition += actualSpeed;
            if(yPosition>Constant.WINDOW_HEIGHT-40)
                yPosition=Constant.WINDOW_HEIGHT-40;
        }
        if (Gdx.input.isKeyPressed(Constant.DOWN)) {
            yPosition -= actualSpeed;
            if(yPosition<0)
                yPosition=0;
        }
        if (Gdx.input.isKeyPressed(Constant.RIGHT)) {
            xPosition += actualSpeed;
            if(xPosition>Constant.WINDOW_WIDTH-15)
                xPosition=Constant.WINDOW_WIDTH-15;
        }
        if (Gdx.input.isKeyPressed(Constant.LEFT)) {
            xPosition -= actualSpeed;
            if(xPosition<5)
                xPosition=5;
        }
    }
    public void draw(Batch batch, float deltaTime) {
        update(deltaTime);
        move();
        batch.draw(spaceship, xPosition, yPosition, width, height);
    }
}

