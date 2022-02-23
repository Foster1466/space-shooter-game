package com.javasupremacy.hardmode.tracks;

import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.utils.Constant;

public class EnemyShipBTrack implements Track{
    public float timestamp;
    public float ttl;
    public float speed;

    public EnemyShipBTrack() {
        timestamp = 0;
        ttl = 5;
        speed = 50;
    }

    public static Rectangle getInit() {
        float width = 50;
        float height = 50;
        float x = (Constant.WINDOW_WIDTH - width) - 50;
        float y = Constant.WINDOW_HEIGHT - 5;
        return new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle update(float deltaTime, Rectangle hitbox) {
        timestamp += deltaTime;
        if (timestamp < ttl)
            hitbox.y -= speed * deltaTime;
        else
            hitbox.x -= speed * deltaTime;
        return hitbox;
    }
}
