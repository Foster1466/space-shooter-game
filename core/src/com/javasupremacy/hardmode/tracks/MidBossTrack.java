package com.javasupremacy.hardmode.tracks;

import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.utils.Constant;

public class MidBossTrack implements Track{
    public float timestamp;
    public float timeToTurn;
    public float switchTime;
    public float ttl;
    public float speed;
    public boolean ltr;

    public MidBossTrack() {
        timestamp = 0;
        timeToTurn = 5;
        switchTime = 15;
        ttl = 30;
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
    public Rectangle update(float deltaTime, Rectangle boundingBox) {
        timestamp += deltaTime;
        if (timestamp > ttl) {
            speed = 500;
            boundingBox.x += speed * deltaTime;
        } else {
            if (timestamp < timeToTurn) {
                boundingBox.y -= speed * deltaTime;
            }
            else {
                if (timestamp > switchTime) {
                    this.speed = 300;
                }
                if (ltr) {
                    boundingBox.x += speed * deltaTime;
                    if (timestamp > switchTime)
                        boundingBox.y -= speed * deltaTime;
                } else {
                    boundingBox.x -= speed * deltaTime;
                    if (timestamp > switchTime)
                        boundingBox.y += speed * deltaTime;
                }

                if (boundingBox.x >= Constant.WINDOW_WIDTH - boundingBox.width) {
                    ltr = false;
                }
                if (boundingBox.x <= 0) {
                    ltr = true;
                }
            }
        }
        return boundingBox;
    }
}
