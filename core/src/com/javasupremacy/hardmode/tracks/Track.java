package com.javasupremacy.hardmode.tracks;

import com.badlogic.gdx.math.Rectangle;

public interface Track {
    public Rectangle update(float deltaTime, Rectangle boundingBox);
}
