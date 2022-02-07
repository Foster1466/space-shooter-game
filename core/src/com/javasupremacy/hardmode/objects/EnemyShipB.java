package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.utils.Constant;

import java.util.Random;

public class EnemyShipB extends EnemyShip{
    public EnemyShipB() {
        // Every type of ship should have its default value
        float xCenter = this.random.nextFloat() * (Constant.WINDOW_WIDTH - 10) + 5;
        float yCenter = Constant.WINDOW_HEIGHT - 5;
        float width = 75;
        float height = 75;
        this.movementSpeed = 20;
        this.boundingBox = new Rectangle(xCenter - width/2,yCenter - height/2, width, height);
        this.laserWidth = 2.0f;
        this.laserHeight = 15;
        this.laserMovementSpeed = 200;
        this.timeBetweenShots = 1.0f;
        this.shipTexture = new Texture("enemyRed3.png");;
        this.laserTexture = new Texture("laserRed03.png");
        directionVector = new Vector2(0,-1);
    }
}
