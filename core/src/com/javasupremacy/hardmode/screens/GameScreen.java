package com.javasupremacy.hardmode.screens;

//This is my class where I wrote code
//This class consists of two execution one is scrolling back and character included

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.javasupremacy.hardmode.MainGame;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

import com.javasupremacy.hardmode.factories.BossFactory;
import com.javasupremacy.hardmode.factories.EnemyFactory;
import com.javasupremacy.hardmode.factories.EnemyShipFactory;
import com.javasupremacy.hardmode.objects.*;
import com.javasupremacy.hardmode.utils.Constant;

import java.util.*;


public class GameScreen implements Screen {

    private MainGame game;
    private int foregroundOffset;

    private float timestamp;


    private Texture background, foreground;
    private Texture heart;
    BitmapFont font0 = new BitmapFont();
    BitmapFont font1 = new BitmapFont();

    // game objects
    PlayerShip playerShip;
    private List<Enemy> enemyShipList;
    private List<EnemyLaser> enemyLaserList;
    private List<PlayerBullet> bullets;

    // factories
    private List<EnemyFactory> factoryList;

    //timing
    //private int foregroundOffset;
    float speed = (float) 0.4;
    float x;
    float y;
    int HiScore, score, heartCount;
    private String mode;

    public GameScreen(MainGame game) {
        foregroundOffset = 0;

        //Setup for the background
        background = new Texture("mainScreen.jpg");
        foreground = new Texture("back.jpg");
        heart = new Texture("heart.png");

        font0.setColor(0, 0, 0, 1);
        font0.getData().setScale(2f);
        font1.setColor(1, 1, 1, 1);
        font1.getData().setScale(2f);


        //background scrolling starts here at below initialization
        //foregroundOffset=0;
        x = 10;
        y = 10;
        HiScore = 0;
        score = 0;
        mode = "Normal speed";
        heartCount = 5;

        // Objects
        playerShip = new PlayerShip();
        enemyShipList = new ArrayList<>();
        enemyLaserList = new ArrayList<>();
        bullets = new ArrayList<>();

        // Factory
        factoryList = new ArrayList<>();
        factoryList.add(new EnemyShipFactory());
        factoryList.add(new BossFactory());

        timestamp = 0;
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        game.batch.begin();
        renderBackground();
        spawnEnemy(deltaTime);
        renderEnemy(deltaTime);
        renderEnemyLasers(deltaTime);
        renderShip(deltaTime);
        renderShipBullet(deltaTime);
        game.batch.end();
        timestamp += deltaTime;
        if (timestamp > Constant.GAME_LENGTH) {
            gameEnd();
        }
    }

    private void renderBackground() {
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        foregroundOffset++;
        if (foregroundOffset % Constant.WINDOW_HEIGHT == 0) {
            //This if judges till where it should increment, increment is nothing but
            //offsetting down as you go through the screen
            foregroundOffset = 0;
        }
        game.batch.draw(background, 0, 0, Constant.EXT_WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        game.batch.draw(foreground, 5, -foregroundOffset, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        game.batch.draw(foreground, 5, -foregroundOffset + Constant.WINDOW_HEIGHT, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);

        font0.draw(game.batch, mode, Constant.WINDOW_WIDTH + 50, Constant.WINDOW_HEIGHT - 20);
        font1.draw(game.batch, "HiScore: " + String.format("%08d", HiScore), Constant.WINDOW_WIDTH + 10, Constant.WINDOW_HEIGHT - 60);
        font1.draw(game.batch, "Score: " + String.format("%08d", score), Constant.WINDOW_WIDTH + 10, Constant.WINDOW_HEIGHT - 100);
        font0.draw(game.batch, "HP: ", Constant.WINDOW_WIDTH + 10, Constant.WINDOW_HEIGHT - 140);
        for (int i = 0; i < heartCount; i++)
            game.batch.draw(heart, Constant.WINDOW_WIDTH + 10 + (i * 50), Constant.WINDOW_HEIGHT - 210, 40, 40);
    }

    /**
     * Iterate through list and draw enemy
     * Also add lasers if can fire
     * Remove out of screen enemies
     * @param deltaTime
     */
    private void renderEnemy(float deltaTime) {
        List<Enemy> removeList = new ArrayList<>();
        for (Enemy enemy : enemyShipList) {
            enemy.draw(game.batch, deltaTime);
            if (enemy.canFireLaser()) {
                enemyLaserList.addAll(Arrays.asList(enemy.fireLasers()));
            }
            if (enemy.isOutOfBounds()) {
                removeList.add(enemy);
            }
        }
        enemyShipList.removeAll(removeList);
    }

    /**
     * Render all lasers
     * Remove out of screen lasers
     * @param deltaTime
     */
    private void renderEnemyLasers(float deltaTime) {
        List<EnemyLaser> removeList = new ArrayList<>();
        for (EnemyLaser enemyLaser : enemyLaserList) {
            enemyLaser.draw(game.batch);
            enemyLaser.boundingBox.y -= enemyLaser.movementSpeed * deltaTime;
            if (enemyLaser.canRemove()) {
                removeList.add(enemyLaser);
            }
        }
        enemyLaserList.removeAll(removeList);
    }

    /**
     * Render player's ship
     * @param deltaTime
     */
    private void renderShip(float deltaTime) {
        playerShip.draw(game.batch, deltaTime);
    }

    /**
     * Render player's bullet
     * @param deltaTime
     */
    private void renderShipBullet(float deltaTime) {
        if (playerShip.isFiring()) {
            bullets.addAll(playerShip.fireBullet());
        }
        List<PlayerBullet> removeList = new ArrayList<>();
        for (PlayerBullet bullet : bullets) {
            bullet.update(deltaTime);
            bullet.render(game.batch);
            if (bullet.canRemove()) {
                removeList.add(bullet);
            }
        }
        bullets.removeAll(removeList);
    }

    /**
     * For each factory, add enemy into list based on deltaTime
     * @param deltaTime
     */
    private void spawnEnemy(float deltaTime) {
        for (EnemyFactory factory : factoryList) {
            factory.produce(deltaTime, this.enemyShipList);
        }
    }

    private void gameEnd() {
        this.dispose();
        game.setScreen(new GameOverScreen(game));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
