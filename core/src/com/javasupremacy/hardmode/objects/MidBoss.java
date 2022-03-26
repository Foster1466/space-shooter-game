package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.patterns.MidBossPattern;
import com.javasupremacy.hardmode.tracks.EnemyShipATrack;
import com.javasupremacy.hardmode.tracks.MidBossTrack;
import com.javasupremacy.hardmode.utils.Constant;

public class MidBoss extends Enemy {
    public MidBoss() {
        super();
        // Every type of ship should have its default value
        this.hp = 20;
        this.hitbox = MidBossTrack.getInit();
        this.track = new MidBossTrack();
        this.pattern = new MidBossPattern();
        this.shipTexture = new Texture("midboss.png");
    }
}
