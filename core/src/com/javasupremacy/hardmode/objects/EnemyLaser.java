package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.utils.Constant;

public class EnemyLaser {
    // position and dimensions
    //float xPosition, yPosition, width, height;
    public Rectangle boundingBox;

    // laser physical characteristics
    public float movementSpeed;

    // graphics
    Texture textureReg;

    public EnemyLaser(float xPosition, float yPosition, float width, float height, float movementSpeed, Texture textureReg) {
        this.boundingBox = new Rectangle(xPosition - width / 2, yPosition, width, height);
        this.movementSpeed = movementSpeed;
        this.textureReg = textureReg;
    }

    public boolean canRemove() {
        return boundingBox.x < 0 || boundingBox.x > Constant.WINDOW_WIDTH
                || boundingBox.y > Constant.WINDOW_HEIGHT || boundingBox.y < 0;
    }

    public void draw(Batch batch) {
        batch.draw(textureReg, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
}
