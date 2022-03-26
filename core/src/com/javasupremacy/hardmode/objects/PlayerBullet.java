package com.javasupremacy.hardmode.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javasupremacy.hardmode.utils.Constant;


public class PlayerBullet {

    public static final int SPEED = 300;
    public static final int DEFAULT_Y = 1;

    private static Texture texture;

    float x, y;

    public PlayerBullet(float x, float y) {
        this.x = x;
        this.y = y;
//        this.rect = new CollisionRect(x, y, WIDTH, HEIGHT);

        texture = new Texture("bulletBeige.png");
    }

    public void update (float deltaTime) {
        y += SPEED * deltaTime;
    }

    public boolean canRemove() {
        return  x < 0 || x > Constant.WINDOW_WIDTH || y < 0 || y > Constant.WINDOW_HEIGHT;
    }

    public void render (SpriteBatch batch) {
        batch.draw(texture, x, y);
    }
}
