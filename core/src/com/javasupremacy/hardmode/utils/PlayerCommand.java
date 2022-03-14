package com.javasupremacy.hardmode.utils;

import com.badlogic.gdx.Gdx;
import com.javasupremacy.hardmode.objects.PlayerShip;

public class PlayerCommand {
    PlayerShip ship;

    public PlayerCommand(PlayerShip ship) {
        this.ship = ship;
    }

    /**
     * Listen for input command and make corresponding calls
     */
    public void run() {
        ship.slowMode(false);
        if (Gdx.input.isKeyPressed(Constant.SLOW_MODE)) {
            ship.slowMode(true);
        }

        // Movements
        if (Gdx.input.isKeyPressed(Constant.UP)) {
            ship.moveUp();
        }
        if (Gdx.input.isKeyPressed(Constant.DOWN)) {
            ship.moveDown();
        }
        if (Gdx.input.isKeyPressed(Constant.LEFT)) {
            ship.moveLeft();
        }
        if (Gdx.input.isKeyPressed(Constant.RIGHT)) {
            ship.moveRight();
        }

        if (Gdx.input.isKeyPressed(Constant.FIRE)) {
            ship.fire();
        }
    }
}
