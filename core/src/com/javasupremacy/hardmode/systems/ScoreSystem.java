package com.javasupremacy.hardmode.systems;

import com.javasupremacy.hardmode.utils.Constant;

public class ScoreSystem {

    private int score;
    private int lives;
    private boolean win;

    public ScoreSystem() {
        score = 0;
        lives = Constant.NUM_LIVES;
        win = false;
    }

    public void updateScore(int change) {
        score += change;
    }

    public void updateLives(int change) {
        lives += change;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void end(boolean win) {
        this.win = win;
    }

    public boolean canEnd() {
        return this.win || lives <= 0;
    }

    public boolean isWin() {
        return this.win;
    }
}
