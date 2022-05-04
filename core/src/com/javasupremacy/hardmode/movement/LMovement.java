package com.javasupremacy.hardmode.movement;

import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.utils.Constant;

public class LMovement implements Movement{
    public float timestamp;
    public float timeToTurn;
    public float speed;

    public LMovement() {
        timestamp = 0;
        timeToTurn = 5;
        speed = 50;
    }

    @Override
    public void move(float deltaTime, Rectangle hitbox) {
        timestamp += deltaTime;
        if (timestamp < timeToTurn)
            hitbox.y -= speed * deltaTime;
        else
            hitbox.x += speed * deltaTime;
    }

    public static Rectangle getInit() {
        float width = 30;
        float height = 30;
        float x = 50;
        float y = Constant.WINDOW_HEIGHT - 5;
        return new Rectangle(x, y, width, height);
    }
}
