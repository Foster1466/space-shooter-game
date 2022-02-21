package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.tracks.EnemyShipATrack;

public class EnemyShipA extends EnemyShip{
    public EnemyShipA() {
        // Every type of ship should have its default value
        this.boundingBox = EnemyShipATrack.getInit();
        this.track = new EnemyShipATrack();
        this.laserWidth = 2.0f;
        this.laserHeight = 15;
        this.laserMovementSpeed = 200;
        this.timeBetweenShots = 1.0f;
        this.shipTexture = new Texture("enemyRed3.png");;
        this.laserTexture = new Texture("laserRed03.png");
        directionVector = new Vector2(0,-1);
    }
}
