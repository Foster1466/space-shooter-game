package com.javasupremacy.hardmode.tracks;

import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.utils.Constant;

public class EnemyShipATrack implements Track{
    public float timestamp;
    public float timeToTurn;
    public float ttl;
    public float speed;

    public EnemyShipATrack() {
        timestamp = 0;
        timeToTurn = 5;
        speed = 50;
    }

    public static Rectangle getInit() {
        float width = 30;
        float height = 30;
        float x = 50;
        float y = Constant.WINDOW_HEIGHT - 5;
        return new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle update(float deltaTime, Rectangle boundingBox) {
        timestamp += deltaTime;
        if (timestamp < timeToTurn)
            boundingBox.y -= speed * deltaTime;
        else
            boundingBox.x += speed * deltaTime;
        return boundingBox;
    }
}
