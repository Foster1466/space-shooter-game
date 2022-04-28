package com.javasupremacy.hardmode.patterns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.objects.ClusterLaser;
import com.javasupremacy.hardmode.objects.EnemyLaser;
import com.javasupremacy.hardmode.objects.EnemyShipA;
import com.javasupremacy.hardmode.utils.Constant;

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
            float Xhitbox = hitbox.x + hitbox.width * 0.18f;
            float Yhitbox = hitbox.y - laserHeight;
            list.addAll(new ClusterLaser().ClusterLaser(laserTexture, 2, 3, Xhitbox+15,
                    Yhitbox+15, laserWidth, laserHeight, laserMovementSpeed));
            list.add(new EnemyLaser.Builder(laserTexture).hitbox(Xhitbox,
                            Yhitbox,
                        laserWidth,
                        laserHeight)
                    .speed(laserMovementSpeed)
                    .build(Constant.HAS_CLUSTER));
            Xhitbox = hitbox.x + hitbox.width * 0.82f;
            list.addAll(new ClusterLaser().ClusterLaser(laserTexture, 2, 3, Xhitbox+15,
                    Yhitbox+15, laserWidth, laserHeight, laserMovementSpeed));
            list.add(new EnemyLaser.Builder(laserTexture).hitbox(Xhitbox,
                            Yhitbox,
                            laserWidth,
                            laserHeight)
                    .speed(laserMovementSpeed)
                    .build(Constant.HAS_CLUSTER));
        }
    }

    private boolean canFire() {
        return timeSinceLastShot - timeBetweenShots >= 0;
    }
}
