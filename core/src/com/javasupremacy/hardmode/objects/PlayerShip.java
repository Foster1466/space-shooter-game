package com.javasupremacy.hardmode.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.javasupremacy.hardmode.utils.Constant;
import com.sun.org.apache.bcel.internal.Const;

import java.util.ArrayList;
import java.util.List;


public class PlayerShip {

    public float movementSpeed;

    public float xPosition, yPosition;
    float width, height;

    private Texture spaceship;

    private float shootInterval = 0.05f;
    private float shootTimestamp = 0;


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

    public void draw(Batch batch, float deltaTime) {
        update(deltaTime);
        if (Gdx.input.isKeyPressed(Constant.UP)) {
            yPosition += movementSpeed;
        }
        if (Gdx.input.isKeyPressed(Constant.DOWN)) {
            yPosition -= movementSpeed;
        }
        if (Gdx.input.isKeyPressed(Constant.RIGHT)) {
            xPosition += movementSpeed;
        }
        if (Gdx.input.isKeyPressed(Constant.LEFT)) {
            xPosition -= movementSpeed;
        }
        batch.draw(spaceship, xPosition, yPosition, width, height);
    }
}


