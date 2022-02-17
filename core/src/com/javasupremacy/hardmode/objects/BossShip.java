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
        float xMove = getDirectionVector().x * movementSpeed * deltaTime;
        float yMove = getDirectionVector().y * movementSpeed * deltaTime;
        if (System.currentTimeMillis() < timeToExit) {
            float leftLimit = -boundingBox.x;
            float downLimit = (float) Constant.WINDOW_HEIGHT / 2 - boundingBox.y;
            float rightLimit = Constant.WINDOW_WIDTH - boundingBox.x - boundingBox.width;
            float upLimit = Constant.WINDOW_HEIGHT - boundingBox.y - boundingBox.height;

            if (xMove > 0)
                xMove = Math.min(xMove, rightLimit);
            else
                xMove = Math.max(xMove, leftLimit);

            if (yMove > 0)
                yMove = Math.min(yMove, upLimit);
            else
                yMove = Math.max(yMove, downLimit);

        } else {
            yMove -= movementSpeed * deltaTime;
        }
        this.translate(xMove, yMove);
    }

    public void draw(Batch batch, float deltaTime) {
        move(deltaTime);
        batch.draw(shipTexture, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
}
