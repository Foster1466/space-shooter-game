package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.utils.Constant;
import org.jetbrains.annotations.NotNull;

public class PlayerSpecialBomb {

    public Rectangle hitbox;

    private float movementSpeed, bombingSpeed;
    private float xDirection, yDirection;
    private float acceleration;
    private boolean isBombing;

    private Texture textureReg;

    public PlayerSpecialBomb(Builder builder) {
        this.hitbox = builder.hitbox;
        this.movementSpeed = builder.movementSpeed;
        this.xDirection = builder.xDirection;
        this.yDirection = builder.yDirection;
        this.acceleration = builder.acceleration;
        this.textureReg = builder.textureReg;
        this.bombingSpeed = 400;
        this.isBombing = false;
    }

    public boolean canRemove() {
        return (hitbox.x+hitbox.width) > Constant.WINDOW_WIDTH
                || (hitbox.y+hitbox.height) > Constant.WINDOW_HEIGHT || hitbox.y < 0;
    }

    public void move(float deltaTime) {
        hitbox.x += movementSpeed * xDirection * deltaTime;
        if(hitbox.y < 200 && !this.isBombing) {
            hitbox.y += movementSpeed * yDirection * deltaTime;
        }
        else if((hitbox.y) >= 200 && !this.isBombing) {
            this.textureReg = new Texture("bombScenes.png");
            this.hitbox =  new Rectangle(hitbox.x-33, hitbox.y, 100, 200);
            this.isBombing = true;
        }
        else{
            bombing(deltaTime);
        }
        movementSpeed = movementSpeed * acceleration;
    }

    public void draw(Batch batch) {
        batch.draw(textureReg, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void bombing(float deltaTime) {
        hitbox.width += this.bombingSpeed * deltaTime;
        hitbox.height += this.bombingSpeed * deltaTime;
        hitbox.x = hitbox.x - (this.bombingSpeed * deltaTime/2);
    }

    public static class Builder {
        private Rectangle hitbox;
        private float movementSpeed;
        private float xDirection, yDirection;
        private float acceleration;
        private Texture textureReg;

        public Builder(Texture texture) {
            this.textureReg = texture;
            this.xDirection = 0;
            this.yDirection = 1;
            this.acceleration = 1;
        }

        public Builder hitbox(Rectangle hitbox){
            this.hitbox = hitbox;
            return this;
        }

        public Builder hitbox(float x, float y, float width, float height) {
            this.hitbox = new Rectangle(x, y, width, height);
            return this;
        }

        public Builder direction(float x, float y) {
            this.xDirection = x;
            this.yDirection = y;
            return this;
        }

        public Builder speed(float speed) {
            this.movementSpeed = speed;
            return this;
        }

        public PlayerSpecialBomb build() {
            return new PlayerSpecialBomb(this);
        }
    }
}
