package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.tracks.Track;
import com.javasupremacy.hardmode.utils.Constant;

abstract public class Enemy {

    // Ship Characteristics
    float movementSpeed;

    // Position and dimension
    public Rectangle boundingBox;
    public Track track;

    // laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot = 0;
    float laserMovementSpeed;

    // Graphics
    Texture shipTexture, laserTexture;

    public Enemy() {
    }

    public void update(float deltaTime){
        timeSinceLastShot += deltaTime;
    }

    public abstract EnemyLaser[] fireLasers();

    public abstract void draw(Batch batch, float deltaTime);

    public abstract boolean canFireLaser();

    public boolean isOutOfBounds() {
        if (this.boundingBox.x + boundingBox.width < 0 || this.boundingBox.x > Constant.WINDOW_WIDTH || this.boundingBox.y + boundingBox.height < 0 || this.boundingBox.y > Constant.WINDOW_HEIGHT) {
            return true;
        }
        return false;
    }
}

