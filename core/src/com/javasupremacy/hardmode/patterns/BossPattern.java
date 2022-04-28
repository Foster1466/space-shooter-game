package com.javasupremacy.hardmode.patterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.objects.Boss;
import com.javasupremacy.hardmode.objects.ClusterLaser;
import com.javasupremacy.hardmode.objects.EnemyLaser;
import com.javasupremacy.hardmode.utils.Constant;

import java.util.List;

public class BossPattern implements Pattern{

    // laser information
    float timestamp;
    float laserWidth, laserHeight, speciallaserWidth, speciallaserHeight;
    float timeBetweenShots;
    float timeSinceLastShot;
    float laserMovementSpeed;
    Texture laserTexture;
    Texture SpecialLaserTexture;


    public BossPattern() {
        timestamp = 0;
        laserWidth = 50;
        laserHeight = 50;
        speciallaserWidth = 150;
        speciallaserHeight = 150;
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
                float Xhitbox = hitbox.x + hitbox.width * 0.18f;
                float Yhitbox = hitbox.y - laserHeight;
                list.addAll(new ClusterLaser().ClusterLaser(laserTexture, 2, 5, Xhitbox+15,
                                Yhitbox+15, laserWidth, laserHeight, laserMovementSpeed));
                list.add(new EnemyLaser.Builder(laserTexture).hitbox(Xhitbox,
                                Yhitbox,
                                laserWidth,
                                laserHeight)
                        .speed(laserMovementSpeed)
                        .build(Constant.HAS_CLUSTER));
                Xhitbox = hitbox.x + hitbox.width * 0.82f;
                list.addAll(new ClusterLaser().ClusterLaser(laserTexture, 2, 5, Xhitbox+15,
                        Yhitbox+15, laserWidth, laserHeight, laserMovementSpeed));
                list.add(new EnemyLaser.Builder(laserTexture).hitbox(Xhitbox,
                                Yhitbox,
                                laserWidth,
                                laserHeight)
                        .speed(laserMovementSpeed)
                        .build(Constant.HAS_CLUSTER));
            }
        }
    }

    private void speicalfireLasers(List<EnemyLaser> list, Rectangle hitbox){
        //float specialLaserWidth = 50;
        //float specialLaserHeight = 50;
        list.add(new EnemyLaser.Builder(SpecialLaserTexture).hitbox(hitbox.x + hitbox.width * 0.15f,
                    hitbox.y,
                        speciallaserWidth,
                        speciallaserHeight)
                .speed(laserMovementSpeed)
                .direction(-1, 1)
                .build(false));
        float Xhitbox = hitbox.x + hitbox.width * 0.18f;
        float Yhitbox = hitbox.y - laserHeight;
        list.addAll(new ClusterLaser().ClusterLaser(laserTexture, 2, 5, Xhitbox+15,
                Yhitbox+15, laserWidth, laserHeight, laserMovementSpeed));
        list.add(new EnemyLaser.Builder(laserTexture).hitbox(Xhitbox,
                        Yhitbox,
                        laserWidth,
                        laserHeight)
                .speed(laserMovementSpeed)
                .build(Constant.HAS_CLUSTER));
        list.add(new EnemyLaser.Builder(SpecialLaserTexture).hitbox(hitbox.x + hitbox.width * 0.85f,
                        hitbox.y,
                        speciallaserWidth,
                        speciallaserHeight)
                .speed(laserMovementSpeed)
                .direction(1, 1)
                .build(false));

    }

    private boolean canFire() {
        return timeSinceLastShot - timeBetweenShots >= 0;
    }
}
