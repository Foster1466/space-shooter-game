package com.javasupremacy.hardmode.systems;

import com.javasupremacy.hardmode.utils.Constant;

public class ScoreSystem {

    private int score;
    private int lives;

    public ScoreSystem() {
        score = 0;
        lives = Constant.NUM_LIVES;
    }

    public void updateScore(int enemyCollision) {
        score = enemyCollision;
    }

    public void updateLives(int numPlayerCollision) {
        lives = numPlayerCollision;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}
