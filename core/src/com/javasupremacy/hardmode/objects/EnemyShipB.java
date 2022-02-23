package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.tracks.EnemyShipBTrack;

public class EnemyShipB extends Enemy{
    public EnemyShipB() {
        // Every type of ship should have its default value
        this.hitbox = EnemyShipBTrack.getInit();
        this.track = new EnemyShipBTrack();
        this.laserWidth = 4.0f;
        this.laserHeight = 20;
        this.laserMovementSpeed = 200;
        this.timeBetweenShots = 1.0f;
        this.shipTexture = new Texture("enemyRed3.png");;
        this.laserTexture = new Texture("laserRed03.png");
    }
}
