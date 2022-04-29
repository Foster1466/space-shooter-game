package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.utils.Constant;

import java.util.Random;

public class EnemyLaser {
    // position and dimensions
    //float xPosition, yPosition, width, height;
    public Rectangle hitbox;

    // laser physical characteristics
    private float movementSpeed;
    // direction between 0 - 1
    private float xDirection; // right positive, left negative
    private float yDirection; // down positive, up negative
    private float xCLusterDirection, yCLusterDirection;

    private float acceleration;

    // graphics
    private Texture textureReg;
    private boolean isParent;
    private Random random = new Random();
    private int changeCLusterDistance;

    public EnemyLaser(Builder builder, boolean isParent) {
        this.hitbox = builder.hitbox;
        this.movementSpeed = builder.movementSpeed;
        this.xDirection = builder.xDirection;
        this.yDirection = builder.yDirection;
        this.xCLusterDirection = builder.xCLusterDirection;
        this.yCLusterDirection = builder.yCLusterDirection;
        this.acceleration = builder.acceleration;
        this.textureReg = builder.textureReg;
        this.changeCLusterDistance = 150+(random.nextInt(8)*50);
        this.isParent = isParent;
    }

    /*public boolean isCluster(){
        return this.isCLusterLaser;
    }*/

    public boolean canRemove() {
        if(!isParent)
            return hitbox.x < 0 || hitbox.x > Constant.WINDOW_WIDTH
                || hitbox.y > Constant.WINDOW_HEIGHT || hitbox.y < 0;
        else
            return hitbox.x < 0 || hitbox.x > Constant.WINDOW_WIDTH
                ||  hitbox.y > Constant.WINDOW_HEIGHT || hitbox.y < this.changeCLusterDistance;
    }

    public void move(float deltaTime) {
        if(hitbox.y < this.changeCLusterDistance) { ///problem
            hitbox.x += movementSpeed * xCLusterDirection * deltaTime;
            hitbox.y -= movementSpeed * yCLusterDirection * deltaTime;
        }
        else {
            hitbox.x += movementSpeed * xDirection * deltaTime;
            hitbox.y -= movementSpeed * yDirection * deltaTime;
        }
        movementSpeed = movementSpeed * acceleration;
    }

    public void draw(Batch batch) {
        batch.draw(textureReg, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }


    public static class Builder {
        private Rectangle hitbox;
        private float movementSpeed;
        private float xDirection;
        private float yDirection;
        private float xCLusterDirection;
        private float yCLusterDirection;
        private float acceleration;
        private Texture textureReg;

        public Builder(Texture texture) {
            this.textureReg = texture;
            xDirection = 0;
            yDirection = 1;
            xCLusterDirection = xDirection;
            yCLusterDirection = yDirection;
            acceleration = 1;
        }

        public Builder hitbox(Rectangle hitbox) {
            this.hitbox = hitbox;
            return this;
        }

        public Builder hitbox(float x, float y, float width, float height) {
            this.hitbox = new Rectangle(x, y, width, height);
            return this;
        }

        public Builder clusterDirection(float x, float y){
            this.xCLusterDirection = x;
            this.yCLusterDirection = y;
            return this;
        }

        public Builder direction(float x, float y) {
            this.xDirection = x;
            this.yDirection = y;
            this.xCLusterDirection = x;
            this.yCLusterDirection = y;
            return this;
        }

        public Builder speed(float speed) {
            this.movementSpeed = speed;
            return this;
        }

        public Builder acceleration(float acc) {
            this.acceleration = acc;
            return this;
        }

        public EnemyLaser build(boolean isParent) {
            return new EnemyLaser(this, isParent);
        }
    }
}
