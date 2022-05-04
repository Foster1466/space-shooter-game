package com.javasupremacy.hardmode.movement;

import com.badlogic.gdx.math.Rectangle;

public class DefaultMovement implements Movement{


    // direction between 0 - 1
    private float xDirection; // right positive, left negative
    private float yDirection; // up positive, down negative

    private float movementSpeed;
    private float acceleration;

    public DefaultMovement(Builder builder) {
        this.xDirection = builder.xDirection;
        this.yDirection = builder.yDirection;
        this.acceleration = builder.acceleration;
        this.movementSpeed = builder.movementSpeed;
    }

    @Override
    public void move(float deltaTime, Rectangle hitbox) {
        hitbox.x += movementSpeed * xDirection * deltaTime;
        hitbox.y += movementSpeed * yDirection * deltaTime;
        movementSpeed = movementSpeed * acceleration;
    }

    public static class Builder {
        private float xDirection;
        private float yDirection;
        private float movementSpeed;
        private float acceleration;

        public Builder() {
            xDirection = 0;
            yDirection = 1;
            acceleration = 1;
            movementSpeed = 200;
        }

        public DefaultMovement.Builder direction(float x, float y) {
            this.xDirection = x;
            this.yDirection = y;
            return this;
        }

        public DefaultMovement.Builder speed(float speed) {
            this.movementSpeed = speed;
            return this;
        }

        public DefaultMovement.Builder acceleration(float acc) {
            this.acceleration = acc;
            return this;
        }

        public Movement build() {
            return new DefaultMovement(this);
        }
    }
}
