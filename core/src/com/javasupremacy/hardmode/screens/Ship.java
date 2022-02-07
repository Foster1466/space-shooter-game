package com.javasupremacy.hardmode.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.concurrent.RecursiveAction;

abstract class Ship {

    // Ship Characteristics
    float movementSpeed;

    // Position and dimension
    /*
    float xPosition, yPosition;     //lower-left corner
    float width, height;
     */
    Rectangle boundingBox;

    // laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot = 0;
    float laserMovementSpeed;

    // Graphics
    Texture shipTexture, laserTexture;

    public Ship(float xCenter, float yCenter,
                float width, float height,
                float movementSpeed,
                float laserWidth, float laserHeight, float laserMovementSpeed,
                float timeBetweenShots,
                Texture shipTexture, Texture laserTexture) {
        this.movementSpeed = movementSpeed;
        this.boundingBox = new Rectangle(xCenter - width/2,yCenter - height/2, width, height);
        this.laserWidth = laserWidth;
        this.laserHeight = laserHeight;
        this.laserMovementSpeed = laserMovementSpeed;
        this.timeBetweenShots = timeBetweenShots;
        this.shipTexture = shipTexture;
        this.laserTexture = laserTexture;
    }

    public void update(float deltaTime){
        timeSinceLastShot += deltaTime;
    }

    public boolean canFireLaser(){
        boolean result = (timeSinceLastShot-timeBetweenShots>=0);
        return result;
    }

    public abstract Laser[] fireLasers();

    public void translate(float xChange, float yChange) {
        boundingBox.setPosition(boundingBox.x+xChange, boundingBox.y+yChange);
    }

    public void draw(Batch batch){
        batch.draw(shipTexture, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
}
