package com.javasupremacy.hardmode.utils;

import com.badlogic.gdx.Gdx;
import com.javasupremacy.hardmode.objects.Controllable;
import com.javasupremacy.hardmode.objects.PlayerShip;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.ArrayList;
import java.util.List;

public class PlayerCommand {
    List<Controllable> subscribers;


    public PlayerCommand() {
        subscribers = new ArrayList<>();
    }

    public void add(Controllable subscriber) {
        subscribers.add(subscriber);
    }

    public void remove(Controllable subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Listen for input command and make corresponding calls
     */
    public void run() {
        for (Controllable sub : subscribers) {
            // slow mode by hold
            if (Gdx.input.isKeyPressed(Constant.SLOW_MODE)) {
                sub.slowMode(true);
            } else {
                sub.slowMode(false);
            }

            // Movements
            if (Gdx.input.isKeyPressed(Constant.UP)) {
                sub.moveUp();
            }
            if (Gdx.input.isKeyPressed(Constant.DOWN)) {
                sub.moveDown();
            }
            if (Gdx.input.isKeyPressed(Constant.LEFT)) {
                sub.moveLeft();
            }
            if (Gdx.input.isKeyPressed(Constant.RIGHT)) {
                sub.moveRight();
            }
            // Fire
            if (Gdx.input.isKeyPressed(Constant.FIRE)) {
                sub.fire();
            }
        }
    }
}
