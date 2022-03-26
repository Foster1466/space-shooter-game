package com.javasupremacy.hardmode.patterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.objects.Boss;
import com.javasupremacy.hardmode.objects.EnemyLaser;

import java.util.List;

public class BossPattern implements Pattern{

    // laser information
    float timestamp;
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot;
    float laserMovementSpeed;
    Texture laserTexture;
    Texture SpecialLaserTexture;


    public BossPattern() {
        timestamp = 0;
        laserWidth = 50;
        laserHeight = 50;
        timeBetweenShots = 1.0f;
        timeSinceLastShot = 0;
        laserMovementSpeed = 200f;
        laserTexture = new Texture("boss_fire.png");
        SpecialLaserTexture = new Texture("boss-red-fire.png");
    }
    @Override
    public void fire(float deltaTime, List<EnemyLaser> list, Rectangle hitbox) {
        timestamp += deltaTime;
        timeSinceLastShot += deltaTime;
        if (canFire()) {
            timeSinceLastShot = 0;
            if (timestamp >= 15) {
                speicalfireLasers(list, hitbox);
            } else {
                list.add(new EnemyLaser(hitbox.x + hitbox.width * 0.18f,
                        hitbox.y - laserHeight,
                        laserWidth,
                        laserHeight,
                        laserMovementSpeed,
                        laserTexture));
                list.add(new EnemyLaser(hitbox.x + hitbox.width * 0.82f,
                        hitbox.y - laserHeight,
                        laserWidth,
                        laserHeight,
                        laserMovementSpeed, laserTexture));
            }
        }
    }

    private void speicalfireLasers(List<EnemyLaser> list, Rectangle hitbox){
        float specialLaserWidth = 50;
        float specialLaserHeight = 50;
        list.add(new EnemyLaser(hitbox.x+ hitbox.width*0.15f,
                hitbox.y-specialLaserHeight,
                specialLaserWidth,
                specialLaserHeight,
                laserMovementSpeed,
                SpecialLaserTexture));
        list.add(new EnemyLaser(hitbox.x+ hitbox.width*0.5f,
                hitbox.y-specialLaserHeight,
                specialLaserWidth,
                specialLaserHeight,
                laserMovementSpeed,
                laserTexture));
        list.add(new EnemyLaser(hitbox.x+ hitbox.width*0.85f,
                hitbox.y-specialLaserHeight,
                specialLaserWidth,
                specialLaserHeight,
                laserMovementSpeed,
                SpecialLaserTexture));

    }

    private boolean canFire() {
        return timeSinceLastShot - timeBetweenShots >= 0;
    }
}
