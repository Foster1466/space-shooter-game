package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.utils.Constant;

public class MidBoss extends BossShip {
    public MidBoss() {
        super(10);
        // Every type of ship should have its default value
        float xCenter = Constant.WINDOW_WIDTH/2;
        float yCenter = Constant.WINDOW_HEIGHT - 5;
        float width = 100;
        float height = 100;
        this.movementSpeed = 100;
        this.boundingBox = new Rectangle(xCenter - width/2,yCenter - height/2, width, height);
        this.laserWidth = 15;
        this.laserHeight = 15;
        this.laserMovementSpeed = 200;
        this.timeBetweenShots = 3.0f;
        this.shipTexture = new Texture("midboss.png");;
        this.laserTexture = new Texture("midboss_fire.png");
        directionVector = new Vector2(0,-1);
    }
}
