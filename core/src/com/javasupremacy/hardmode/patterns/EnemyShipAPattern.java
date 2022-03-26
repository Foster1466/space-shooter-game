package com.javasupremacy.hardmode.patterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.objects.EnemyLaser;
import com.javasupremacy.hardmode.objects.EnemyShipA;

import java.util.List;

public class EnemyShipAPattern implements Pattern{
    // laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot;
    float laserMovementSpeed;
    Texture laserTexture;

    public EnemyShipAPattern() {
        laserWidth = 4.0f;
        laserHeight = 20f;
        timeBetweenShots = 1.0f;
        timeSinceLastShot = 0;
        laserMovementSpeed = 200f;
        laserTexture = new Texture("laserRed03.png");
    }

    @Override
    public void fire(float deltaTime, List<EnemyLaser> list, Rectangle hitbox) {
        timeSinceLastShot += deltaTime;
        if (canFire()) {
            timeSinceLastShot = 0;
            list.add(new EnemyLaser(hitbox.x+ hitbox.width*0.18f,
                    hitbox.y-laserHeight,
                    laserWidth,
                    laserHeight,
                    laserMovementSpeed,
                    laserTexture));
            list.add(new EnemyLaser(hitbox.x+hitbox.width*0.82f,
                    hitbox.y-laserHeight,
                    laserWidth,
                    laserHeight,
                    laserMovementSpeed, laserTexture));
        }
    }

    private boolean canFire() {
        return timeSinceLastShot - timeBetweenShots >= 0;
    }
}
