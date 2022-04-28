package com.javasupremacy.hardmode.utils;

import com.badlogic.gdx.Input;

public class Constant {
    public static final int EXT_WINDOW_WIDTH = 836;
    public static final int EXT_WINDOW_HEIGHT  = 820;
    public static final int WINDOW_WIDTH = 536;
    public static final int WINDOW_HEIGHT = 800;
    public static final int GAME_LENGTH = 150; // 2:30

    //public static JsonReader reader = new JsonReader();
    //reader.initialContent();
    public static int UP = Input.Keys.UP;
    public static int DOWN = Input.Keys.DOWN;
    public static int LEFT = Input.Keys.LEFT;
    public static int RIGHT = Input.Keys.RIGHT;
    public static int FIRE = Input.Keys.SPACE;
    public static int BOMB = Input.Keys.B;
    public static int SLOW_MODE = Input.Keys.SHIFT_LEFT;
    public static int NUM_LIVES = 13;
    public static int NUM_BOMB = 5;
    public static boolean HAS_CLUSTER = false;
    public static JsonReader reader;
    public Constant(int gameLevel) {
        reader = new JsonReader(gameLevel);
        NUM_LIVES = reader.getLivesNumber();
        NUM_BOMB = reader.getBombsNumber();
        HAS_CLUSTER = reader.getIsCluster();
    }
}
