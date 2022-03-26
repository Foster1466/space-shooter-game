package com.javasupremacy.hardmode.patterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.objects.EnemyLaser;
import com.javasupremacy.hardmode.objects.EnemyShipA;

import java.util.List;

public class MidBossPattern implements Pattern{
    // laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot;
    float laserMovementSpeed;
    Texture laserTexture;

    public MidBossPattern() {
        laserWidth = 50;
        laserHeight = 50;
        timeBetweenShots = 1.0f;
        timeSinceLastShot = 0;
        laserMovementSpeed = 200f;
        laserTexture = new Texture("midboss_fire.png");
    }

    @Override
    public void fire(float deltaTime, List<EnemyLaser> list, Rectangle hitbox) {
        timeSinceLastShot += deltaTime;
        if (canFire()) {
            timeSinceLastShot = 0;
            list.add(new EnemyLaser.Builder(laserTexture).hitbox(hitbox.x + hitbox.width * 0.18f,
                        hitbox.y - laserHeight,
                        laserWidth,
                        laserHeight)
                    .speed(laserMovementSpeed)
                    .build());
            list.add(new EnemyLaser.Builder(laserTexture).hitbox(hitbox.x + hitbox.width * 0.82f,
                            hitbox.y - laserHeight,
                            laserWidth,
                            laserHeight)
                    .speed(laserMovementSpeed)
                    .build());
        }
    }

    private boolean canFire() {
        return timeSinceLastShot - timeBetweenShots >= 0;
    }
}
