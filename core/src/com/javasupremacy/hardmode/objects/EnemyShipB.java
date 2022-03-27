package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.patterns.EnemyShipBPattern;
import com.javasupremacy.hardmode.tracks.EnemyShipBTrack;

public class EnemyShipB extends Enemy{
    public EnemyShipB() {
        // Every type of ship should have its default value
        this.hp = 2;
        this.score = 2;
        this.hitbox = EnemyShipBTrack.getInit();
        this.track = new EnemyShipBTrack();
        this.pattern = new EnemyShipBPattern();
        this.shipTexture = new Texture("enemyRed3.png");
    }
}
