package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.patterns.EnemyShipBPattern;
import com.javasupremacy.hardmode.systems.ScoreSystem;
import com.javasupremacy.hardmode.tracks.EnemyShipBTrack;

public class EnemyShipB extends Enemy{
    public EnemyShipB() {
        // Every type of ship should have its default value
        this.hp = 3;
        this.score = 300;
        this.hitbox = EnemyShipBTrack.getInit();
        this.shipTexture = new Texture("enemyRed3.png");
    }

    public void die(ScoreSystem ss) {
        ss.updateScore(this.score);
        // Final boss die ends game
        if (this.isFinalBoss) {
            ss.updateEnd(true);
        }
    }
}
