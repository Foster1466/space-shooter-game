package com.javasupremacy.hardmode.screens;

//This is my class where I wrote code
//This class consists of two execution one is scrolling back and character included

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.javasupremacy.hardmode.MainGame;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

import com.javasupremacy.hardmode.objects.*;
import com.javasupremacy.hardmode.utils.Constant;

import java.util.*;


public class GameScreen implements Screen {

    private MainGame game;
    private Texture background;
    private int backgroundOffset;

    // game objects
    PlayerShip playerShip;
    private List<EnemyShip> enemyShipList;
    private List<EnemyLaser> enemyLaserList;
    private List<PlayerBullet> bullets;

    private float timeBetweenEnemySpawns = 3f;
    private float enemySpawnTimer = 0;

    public GameScreen(MainGame game)
    {
        background= new Texture("back.jpg");

        backgroundOffset=0;

        playerShip = new PlayerShip();
        enemyShipList = new ArrayList<>();
        enemyLaserList = new ArrayList<>();
        bullets = new ArrayList<>();

        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        game.batch.begin();
        renderBackground();
        renderEnemy(deltaTime);
        renderEnemyLasers(deltaTime);
        renderShip(deltaTime);
        renderShipBullet(deltaTime);
        game.batch.end();
    }

    private void renderBackground() {
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        backgroundOffset ++;
        if(backgroundOffset % Constant.WINDOW_HEIGHT == 0)
        {
            backgroundOffset=0;
        }
        game.batch.draw(background, 0, -backgroundOffset, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        game.batch.draw(background, 0, -backgroundOffset + Constant.WINDOW_HEIGHT, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
    }

    private void renderEnemy(float deltaTime) {
        spawnEnemyShips(deltaTime);
        for (EnemyShip enemyShip : enemyShipList) {
            enemyShip.update(deltaTime);
            enemyShip.draw(game.batch, deltaTime);
            if (enemyShip.canFireLaser()) {
                enemyLaserList.addAll(Arrays.asList(enemyShip.fireLasers()));
            }
        }
    }

    private void renderEnemyLasers(float deltaTime){
        List<EnemyLaser> removeList = new ArrayList<>();
        for (EnemyLaser enemyLaser : enemyLaserList) {
            enemyLaser.draw(game.batch);
            enemyLaser.boundingBox.y -= enemyLaser.movementSpeed * deltaTime;
            if (enemyLaser.canRemove()){
                removeList.add(enemyLaser);
            }
        }
        enemyLaserList.removeAll(removeList);
    }

    private void renderShip(float deltaTime) {
        playerShip.draw(game.batch, deltaTime);
    }

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

    // Need factory later
    private void spawnEnemyShips(float deltaTime){
        enemySpawnTimer += deltaTime;
        Random rand = new Random();
        if (enemySpawnTimer > timeBetweenEnemySpawns) {
            EnemyShip spawned;
            if (rand.nextBoolean()) {
                spawned = new EnemyShipA();
            } else {
                spawned = new EnemyShipB();
            }
            enemyShipList.add(spawned);
            enemySpawnTimer -= timeBetweenEnemySpawns;
        }
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
