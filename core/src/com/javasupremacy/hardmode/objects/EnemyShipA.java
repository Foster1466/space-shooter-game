package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.javasupremacy.hardmode.patterns.EnemyShipAPattern;
import com.javasupremacy.hardmode.systems.ScoreSystem;
import com.javasupremacy.hardmode.tracks.EnemyShipATrack;

public class EnemyShipA extends Enemy{
    public EnemyShipA() {
        // Every type of ship should have its default value
        this.hp = 1;
        this.score = 100;
        this.hitbox = EnemyShipATrack.getInit();
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
