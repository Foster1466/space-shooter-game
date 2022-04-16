package com.javasupremacy.hardmode.systems;

import com.javasupremacy.hardmode.observer.Observer;
import com.javasupremacy.hardmode.utils.Constant;

public class ScoreSystem{

    private int score;
    private int lives;
    private boolean win;
    private Observer backScreen;

    public ScoreSystem() {
        score = 0;
        lives = Constant.NUM_LIVES;
        win = false;
    }

    public void attachBackScreen(Observer backScreen){
        this.backScreen = backScreen;
    }

    public void updateScore(int change) {
        score += change;
        this.backScreen.updateScore();
    }

    public void updateLives(int change) {
        lives += change;
        this.backScreen.updateLives();
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void updateEnd(boolean win) {
        this.win = win;
    }

    public boolean canEnd() {
        return this.win || lives <= 0;
    }

    public boolean isWin() {
        return this.win;
    }
}
