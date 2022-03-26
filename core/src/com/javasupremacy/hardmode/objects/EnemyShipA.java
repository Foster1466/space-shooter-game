package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.javasupremacy.hardmode.patterns.EnemyShipAPattern;
import com.javasupremacy.hardmode.tracks.EnemyShipATrack;

public class EnemyShipA extends Enemy{
    public EnemyShipA() {
        // Every type of ship should have its default value
        this.hp = 1;
        this.hitbox = EnemyShipATrack.getInit();
        this.track = new EnemyShipATrack();
        this.pattern = new EnemyShipAPattern();
        this.shipTexture = new Texture("enemyRed3.png");
    }
}
