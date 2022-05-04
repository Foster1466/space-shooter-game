package com.javasupremacy.hardmode.movement;

import com.badlogic.gdx.math.Rectangle;

public interface Movement {
    public void move(float deltaTime, Rectangle hitbox);
}
