package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.tracks.EnemyShipATrack;
import com.javasupremacy.hardmode.tracks.MidBossTrack;
import com.javasupremacy.hardmode.utils.Constant;

public class MidBoss extends BossShip {
    public MidBoss() {
        super(10);
        // Every type of ship should have its default value
        this.boundingBox = MidBossTrack.getInit();
        this.track = new MidBossTrack();
        this.laserWidth = 15;
        this.laserHeight = 15;
        this.laserMovementSpeed = 200;
        this.timeBetweenShots = 3.0f;
        this.shipTexture = new Texture("midboss.png");;
        this.laserTexture = new Texture("midboss_fire.png");
        directionVector = new Vector2(0,-1);
    }
}
