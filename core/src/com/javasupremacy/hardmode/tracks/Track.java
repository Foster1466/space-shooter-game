package com.javasupremacy.hardmode.tracks;

import com.badlogic.gdx.math.Rectangle;

public interface Track {
    /**
     * Update enemy hitbox position;
     * @param deltaTime
     * @param hitbox
     * @return
     */
    public Rectangle update(float deltaTime, Rectangle hitbox);
}
