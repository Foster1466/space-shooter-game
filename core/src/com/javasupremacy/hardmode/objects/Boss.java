package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.javasupremacy.hardmode.patterns.BossPattern;
import com.javasupremacy.hardmode.tracks.BossTrack;

public class Boss extends Enemy {
    public Boss() {
        super();
        // Every type of ship should have its default value
        this.hp = 50;
        this.hitbox = BossTrack.getInit();
        this.track = new BossTrack();
        this.pattern = new BossPattern();
        this.shipTexture = new Texture("boss.png");
    }
}
