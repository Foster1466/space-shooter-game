package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.utils.Constant;

import java.util.Random;

public class BossShip extends Enemy {
    Vector2 directionVector;
    float timeSinceLastDirectionChange = 0;
    float directionChangeFrequency = 10f;

    Random random = new Random();

    private long timeToExit;

    public long getTimeToExit() {
        return timeToExit;
    }

    public BossShip(int timeToExit) {
        this.timeToExit = System.currentTimeMillis() + (timeToExit * 1000);
    }

    public Vector2 getDirectionVector() {
        return directionVector;
    }

    private void randomizeDirectionVector() {
        double bearing = this.random.nextDouble() * 2 * Math.PI;
        directionVector.x = (float) Math.sin(bearing);
        directionVector.x = (float) Math.cos(bearing);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        timeSinceLastDirectionChange += deltaTime;
        if (timeSinceLastDirectionChange > directionChangeFrequency) {
            randomizeDirectionVector();
            timeSinceLastDirectionChange += directionChangeFrequency;
        }
    }

    public boolean canFireLaser() {
        boolean result = (timeSinceLastShot - timeBetweenShots >= 0);
        return result;
    }

    /**
     * We will need different patterns for different enemy ship
     *
     * @return
     */
    @Override
    public EnemyLaser[] fireLasers() {
        EnemyLaser[] enemyLaser = new EnemyLaser[10];
        for (int i = 0; i < 10; i++) {
            enemyLaser[i] = new EnemyLaser(boundingBox.x + boundingBox.width / 2,
                    boundingBox.y,
                    laserWidth,
                    laserHeight,
                    laserMovementSpeed,
                    laserTexture);
        }
        timeSinceLastShot = 0;
        return enemyLaser;
    }

    public void move(float deltaTime) {
        this.boundingBox = this.track.update(deltaTime, this.boundingBox);
    }

    public void draw(Batch batch, float deltaTime) {
        move(deltaTime);
        batch.draw(shipTexture, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
}
