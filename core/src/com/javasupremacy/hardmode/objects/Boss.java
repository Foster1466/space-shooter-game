package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.tracks.BossTrack;
import com.javasupremacy.hardmode.tracks.EnemyShipATrack;
import com.javasupremacy.hardmode.utils.Constant;

public class Boss extends Enemy {
    public Boss() {
        super();
        // Every type of ship should have its default value
        this.hitbox = BossTrack.getInit();
        this.track = new BossTrack();
        this.laserWidth = 30;
        this.laserHeight = 30;
        this.laserMovementSpeed = 200;
        this.timeBetweenShots = 3.0f;
        this.shipTexture = new Texture("boss.png");;
        this.laserTexture = new Texture("boss_fire.png");
        this.SpecialLaserTexture = new Texture("boss-red-fire.png");
        this.isFinalBoss = true;
    }
}
