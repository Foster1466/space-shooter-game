package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

abstract class Enemy {

    // Ship Characteristics
    float movementSpeed;

    // Position and dimension
    /*
    float xPosition, yPosition;     //lower-left corner
    float width, height;
     */
    public Rectangle boundingBox;

    // laser information
    float laserWidth, laserHeight;
    float timeBetweenShots;
    float timeSinceLastShot = 0;
    float laserMovementSpeed;

    // Graphics
    Texture shipTexture, laserTexture;

    public Enemy() {
    }

    public void update(float deltaTime){
        timeSinceLastShot += deltaTime;
    }

    public abstract Laser[] fireLasers();

    public void translate(float xChange, float yChange) {
        boundingBox.setPosition(boundingBox.x+xChange, boundingBox.y+yChange);
    }
}
