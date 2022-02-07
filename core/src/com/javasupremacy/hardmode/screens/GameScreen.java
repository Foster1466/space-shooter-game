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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class GameScreen implements Screen {

    private MainGame game;

    private Texture background;
    private int backgroundOffset;

    PlayerShip playerShip;

    private Texture spaceship;
    private float speed = (float) 2;
    private float x;
    private float y;

    // game objects
    private List<EnemyShip> enemyShipList;
    private List<Laser> enemyLaserList;
    private List<PlayerBullet> bullets;
    private float timeBetweenEnemySpawns = 3f;
    private float enemySpawnTimer = 0;

    public GameScreen(MainGame game)
    {
        background= new Texture("back.jpg");

        backgroundOffset=0;

        spaceship= new Texture("man.png");
        playerShip = new PlayerShip(0.5F,5,5,Constant.WINDOW_WIDTH/3,
                Constant.WINDOW_HEIGHT/85, spaceship);

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
        renderShip(deltaTime);
        renderEnemy(deltaTime);
        renderLasers(deltaTime);
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

    private void renderShip(float deltaTime) {
        if (Gdx.input.isKeyPressed(Constant.SPACE)) {
            bullets.add(new PlayerBullet(playerShip.xPosition + 1));
        }
        ArrayList<PlayerBullet> bulletsToRemove = new ArrayList<PlayerBullet>();
        for (PlayerBullet bullet : bullets) {
            bullet.update(deltaTime);
            if (bullet.remove) {
                bulletsToRemove.add(bullet);
            }
            bullets.removeAll(bulletsToRemove);
        }
        if (Gdx.input.isKeyPressed(Constant.UP)) {
            playerShip.yPosition += playerShip.movementSpeed;
        }
        if (Gdx.input.isKeyPressed(Constant.DOWN)) {
            playerShip.yPosition -= playerShip.movementSpeed;
        }
        if (Gdx.input.isKeyPressed(Constant.RIGHT)) {
            playerShip.xPosition += playerShip.movementSpeed;
        }
        if (Gdx.input.isKeyPressed(Constant.LEFT)) {
            playerShip.xPosition -= playerShip.movementSpeed;
        }

        backgroundOffset++;
        if (backgroundOffset % Constant.WINDOW_HEIGHT == 0) {
            backgroundOffset = 0;
        }

        game.batch.draw(background, 0, -backgroundOffset, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        game.batch.draw(background, 0, -backgroundOffset + Constant.WINDOW_HEIGHT, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);

        for (PlayerBullet bullet : bullets) {
            bullet.render(game.batch);
        }

        playerShip.draw(game.batch);
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

    private void renderLasers(float deltaTime){
        for (Laser laser : enemyLaserList) {
            laser.draw(game.batch);
            laser.boundingBox.y -= laser.movementSpeed * deltaTime;
            if (laser.boundingBox.y > Constant.WINDOW_HEIGHT || laser.boundingBox.x < 0){
                enemyLaserList.remove(laser);
            }
        }
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
