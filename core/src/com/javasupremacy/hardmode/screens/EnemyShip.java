package com.javasupremacy.hardmode.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EnemyShip extends Enemy {

    Vector2 directionVector;
    float timeSinceLastDirectionChange = 0;
    float directionChangeFrequency = 0.75f;

    public EnemyShip(float xCenter, float yCenter,
                     float width, float height,
                     float movementSpeed,
                     float laserWidth, float laserHeight,
                     float laserMovementSpeed, float timeBetweenShots,
                     Texture shipTexture, Texture laserTexture) {
        super(xCenter, yCenter,  width, height, movementSpeed, laserWidth, laserHeight, laserMovementSpeed, timeBetweenShots, shipTexture, laserTexture);

        directionVector = new Vector2(0,-1);
    }

    public Vector2 getDirectionVector() {
        return directionVector;
    }

    private void randomizeDirectionVector(){
        double bearing = MenuScreen.random.nextDouble()*2*Math.PI;
        directionVector.x = (float) Math.sin(bearing);
        directionVector.x = (float) Math.cos(bearing);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        timeSinceLastDirectionChange+=deltaTime;
        if(timeSinceLastDirectionChange>directionChangeFrequency){
            randomizeDirectionVector();
            timeSinceLastDirectionChange+=directionChangeFrequency;
        }
    }

    @Override
    public Laser[] fireLasers(){
        Laser[] laser = new Laser[2];
        laser[0] = new Laser(boundingBox.x+ boundingBox.width*0.18f, boundingBox.y-laserHeight, laserWidth, laserHeight,
                laserMovementSpeed, laserTexture);
        laser[1] = new Laser(boundingBox.x+boundingBox.width*0.82f, boundingBox.y-laserHeight, laserWidth, laserHeight,
                laserMovementSpeed, laserTexture);

        timeSinceLastShot = 0;

        return laser;
    }

}
