package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.tracks.Track;
import com.javasupremacy.hardmode.utils.Constant;

abstract public class Enemy {
    public Rectangle hitbox;
    public Track track;

    // laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot = 0;
    float laserMovementSpeed;

    // Graphics
    Texture shipTexture;
    Texture laserTexture;

    public Enemy() {

    }

    /**
     * true if can fire
     * @return
     */
    public boolean canFireLaser(){
        boolean result = (timeSinceLastShot-timeBetweenShots>=0);
        return result;
    }

    /**
     * Fire laser objects, reset fire timer
     * @return
     */
    public EnemyLaser[] fireLasers() {
        timeSinceLastShot = 0;
        EnemyLaser[] enemyLaser = new EnemyLaser[2];
        enemyLaser[0] = new EnemyLaser(hitbox.x+ hitbox.width*0.18f,
                hitbox.y-laserHeight,
                laserWidth,
                laserHeight,
                laserMovementSpeed,
                laserTexture);
        enemyLaser[1] = new EnemyLaser(hitbox.x+hitbox.width*0.82f,
                hitbox.y-laserHeight,
                laserWidth,
                laserHeight,
                laserMovementSpeed, laserTexture);

        return enemyLaser;
    }

    /**
     * Move current position based on Track, then render
     * @param batch
     * @param deltaTime
     */
    public void draw(Batch batch, float deltaTime){
        timeSinceLastShot += deltaTime; // Increment timer
        track.update(deltaTime, this.hitbox);
        batch.draw(shipTexture, hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    /**
     * Check if current object is out of screen
     * @return
     */
    public boolean isOutOfBounds() {
        if (this.hitbox.x + hitbox.width < 0 || this.hitbox.x > Constant.WINDOW_WIDTH || this.hitbox.y + hitbox.height < 0 || this.hitbox.y > Constant.WINDOW_HEIGHT) {
            return true;
        }
        return false;
    }
}

