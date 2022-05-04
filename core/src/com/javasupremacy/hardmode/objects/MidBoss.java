package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.javasupremacy.hardmode.patterns.MidBossPattern;
import com.javasupremacy.hardmode.systems.ScoreSystem;
import com.javasupremacy.hardmode.tracks.EnemyShipATrack;
import com.javasupremacy.hardmode.tracks.MidBossTrack;
import com.javasupremacy.hardmode.utils.Constant;

public class MidBoss extends Enemy {
    public MidBoss() {
        super();
        // Every type of ship should have its default value
        this.hp = 20;
        this.score = 2000;
        this.hitbox = MidBossTrack.getInit();
        this.shipTexture = new Texture("midboss.png");
    }

    public void die(ScoreSystem ss) {
        ss.updateScore(this.score);
        // Final boss die ends game
        if (this.isFinalBoss) {
            ss.updateEnd(true);
        }
    }
}
