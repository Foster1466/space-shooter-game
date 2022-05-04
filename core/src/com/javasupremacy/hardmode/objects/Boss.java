package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.javasupremacy.hardmode.patterns.BossPattern;
import com.javasupremacy.hardmode.systems.ScoreSystem;
import com.javasupremacy.hardmode.tracks.BossTrack;

public class Boss extends Enemy {
    public Boss() {
        super();
        // Every type of ship should have its default value
        this.hp = 30;
        this.score = 3000;
        this.hitbox = BossTrack.getInit();
        this.shipTexture = new Texture("boss.png");
    }

    public void die(ScoreSystem ss) {
        ss.updateScore(this.score);
        // Final boss die ends game
        if (this.isFinalBoss) {
            ss.updateEnd(true);
        }
    }
}
