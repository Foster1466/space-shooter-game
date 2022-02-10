package com.javasupremacy.hardmode.screens;

//This is my class where I wrote code
//This class consists of two execution one is scrolling back and character included

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.javasupremacy.hardmode.MainGame;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

import com.javasupremacy.hardmode.objects.*;
import com.javasupremacy.hardmode.utils.Constant;

import java.util.*;


public class GameScreen implements Screen {

    private MainGame game;
    private int backgroundOffset;

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private TextureAtlas textureAtlas;


    private Texture background, foreground;
    private Texture heart;
    BitmapFont font0 = new BitmapFont();
    BitmapFont font1 = new BitmapFont();
    private Texture spaceship;
    private float backgroundHeight;

    private Texture playerShipTexture;

    // game objects
    PlayerShip playerShip;
    private List<EnemyShip> enemyShipList;
    private List<EnemyLaser> enemyLaserList;
    private List<PlayerBullet> bullets;

    //timing
    private int foregroundOffset;
    float speed = (float) 0.4;
    float x;
    float y;
    int HiScore, score, heartCount;
    private String mode;
    private float timeBetweenEnemySpawns = 3f;
    private float enemySpawnTimer = 0;

    public GameScreen(MainGame game)
    {
        background= new Texture("back.jpg");

        backgroundOffset=0;
        camera = new OrthographicCamera();
        viewport= new StretchViewport(Constant.WINDOW_WIDTH+50,Constant.WINDOW_HEIGHT,camera);

        //Setup for the background
        background = new Texture("mainScreen.jpg");
        foreground= new Texture("back.jpg");
        heart = new Texture("heart.png");

        font0.setColor(0, 0, 0, 1);
        font0.getData().setScale(0.4f);
        font1.setColor(1,1,1,1);
        font1.getData().setScale(0.3f);


        //background scrolling starts here at below initialization
        foregroundOffset=0;
        x=10;
        y=10;
        HiScore=0;
        score=0;
        mode = "Normal speed";
        heartCount = 5;

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
        foregroundOffset ++;
        if(foregroundOffset % Constant.WINDOW_HEIGHT == 0)
        {
            //This if judges till where it should increment, increment is nothing but
            //offsetting down as you go through the screen
            foregroundOffset=0;
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
