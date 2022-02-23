package com.javasupremacy.hardmode.tracks;

import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.utils.Constant;

public class BossTrack implements Track{
    public float timestamp;
    public float timeToTurn;
    public float switchTime;
    public float ttl;
    public float speed;
    public boolean ltr;

    public BossTrack() {
        timestamp = 0;
        timeToTurn = 5;
        switchTime = 15;
        ttl = 50;
        speed = 50;
        ltr = true;
    }

    public static Rectangle getInit() {
        float width = 200;
        float height = 200;
        float x = (Constant.WINDOW_WIDTH - width) / 2;
        float y = Constant.WINDOW_HEIGHT - 5;
        return new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle update(float deltaTime, Rectangle hitbox) {
        timestamp += deltaTime;
        if (timestamp > ttl) {
            speed = 500;
            hitbox.x += speed * deltaTime;
        } else {
            if (timestamp < timeToTurn) {
                hitbox.y -= speed * deltaTime;
            }
            else {
                if (timestamp > switchTime) {
                    this.speed = 300;
                }
                if (ltr) {
                    hitbox.x += speed * deltaTime;
                    if (timestamp > switchTime)
                        hitbox.y -= speed * deltaTime;
                } else {
                    hitbox.x -= speed * deltaTime;
                    if (timestamp > switchTime)
                        hitbox.y += speed * deltaTime;
                }

                if (hitbox.x >= Constant.WINDOW_WIDTH - hitbox.width) {
                    ltr = false;
                }
                if (hitbox.x <= 0) {
                    ltr = true;
                }
            }
        }
        return hitbox;
    }
}
