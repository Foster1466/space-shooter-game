package com.javasupremacy.hardmode.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class Laser {
    // position and dimensions
    //float xPosition, yPosition, width, height;
    Rectangle boundingBox;

    // laser physical characteristics
    float movementSpeed;

    // graphics
    Texture textureReg;

    public Laser(float xPosition, float yPosition, float width, float height, float movementSpeed, Texture textureReg) {
        this.boundingBox = new Rectangle(xPosition - width/2,yPosition, width, height);
        this.movementSpeed = movementSpeed;
        this.textureReg = textureReg;
    }

    public void draw(Batch batch){
        batch.draw(textureReg, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
}
