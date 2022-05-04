package com.javasupremacy.hardmode.strategy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.movement.Movement;
import com.javasupremacy.hardmode.objects.EnemyLaser;

import java.util.List;

public class NormalLaserStrategy implements LaserStrategy{
    // laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot;
    float laserMovementSpeed;
    Texture laserTexture;
    Movement movement;

    public NormalLaserStrategy() {
        laserWidth = 4.0f;
        laserHeight = 20f;
        timeBetweenShots = 1.0f;
        timeSinceLastShot = 0;
        laserMovementSpeed = 200f;
        laserTexture = new Texture("laserRed03.png");
    }

    private boolean canFire() {
        return timeSinceLastShot - timeBetweenShots >= 0;
    }

    @Override
    public void setLaserMovement(Movement movement) {
        this.movement = movement;
    }

    @Override
    public void fire(float deltaTime, Rectangle hitbox, List<EnemyLaser> list) {
        timeSinceLastShot += deltaTime;
        if (canFire()) {
            timeSinceLastShot = 0;
            list.add(new EnemyLaser.Builder(laserTexture).hitbox(hitbox.x + hitbox.width * 0.18f,
                            hitbox.y - laserHeight,
                            laserWidth,
                            laserHeight)
                    .speed(laserMovementSpeed)
                    .build(false));
            list.add(new EnemyLaser.Builder(laserTexture).hitbox(hitbox.x + hitbox.width * 0.82f,
                            hitbox.y - laserHeight,
                            laserWidth,
                            laserHeight)
                    .speed(laserMovementSpeed)
                    .build(false));
        }
    }
}
