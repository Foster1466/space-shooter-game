package com.javasupremacy.hardmode.systems;

public class ScoreSystem {

    private int score;
    private int lives;

    public ScoreSystem() {
        score = 0;
        lives = 5;
    }

    public void addScore(int update) {
        score += update;
    }

    public void updateLives(int update) {
        lives += update;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}
