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

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

import com.javasupremacy.hardmode.utils.Constant;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class GameScreen implements Screen {

    private MainGame game;

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private TextureAtlas textureAtlas;


    private Texture background;
    private Texture spaceship;
    private float backgroundHeight;

    private Texture playerShipTexture;


    //timing
    private int backgroundOffset;
    float speed = (float) 0.4;
    float x;
    float y;
    private float timeBetweenEnemySpawns = 3f;
    private float enemySpawnTimer = 0;



    // World Parameters
    private final int WORLD_WIDTH= 72;
    private final int WORLD_HEIGHT= 128;

    // game objects
    private LinkedList<EnemyShip> enemyShipList;
    private LinkedList<Laser> enemyLaserList;

    private Random random = new Random();

    public GameScreen(MainGame game)
    {
        camera = new OrthographicCamera();
        viewport= new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        //Setup for the background
        background= new Texture("mainScreen.jpg");


        //background scrolling starts here at below initialization
        backgroundOffset=0;

        // set up game objects
        enemyShipList = new LinkedList<>();

        enemyLaserList = new LinkedList<>();


        //Have to work on understanding what is this
        this.game = game;


    }

    @Override
    public void show() {
        spaceship= new Texture("man.png");
    }

    @Override
    public void render(float deltaTime) {

        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        if (!Gdx.input.isKeyPressed(Constant.UP)) {
        } else {
            y += speed;
        }
        if (!Gdx.input.isKeyPressed(Constant.DOWN)) {
        } else {
            y -= speed;
        }
        if (Gdx.input.isKeyPressed(Constant.RIGHT)) {
            x += speed;
        }
        if (Gdx.input.isKeyPressed(Constant.LEFT)) {
            x -= speed;
        }

        game.batch.begin();

        //scrolling background

        //Below is a incrementor
        backgroundOffset ++;
        if(backgroundOffset % WORLD_HEIGHT == 0)
        {
            //This if judges till where it should increment, increment is nothing but
            //offsetting down as you go through the screen
            backgroundOffset=0;
        }

        //above statements only dictate when the picture is drawn to the canvas this below is the
        //commands that help us draw what we want to be present
        game.batch.draw(background, 0, -backgroundOffset, WORLD_WIDTH, WORLD_HEIGHT);
        game.batch.draw(background, 0, -backgroundOffset+WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
        game.batch.draw(spaceship, x, y);

        spawnEnemyShips(deltaTime);

        ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator();
        while(enemyShipListIterator.hasNext()) {
            EnemyShip enemyShip = enemyShipListIterator.next();
            moveEnemy(enemyShip, deltaTime);
            enemyShip.update(deltaTime);
            // enemy ships
            enemyShip.draw(game.batch);
        }
        // lasers
        renderLasers(deltaTime);

        game.batch.end();

    }

    private void renderLasers(float deltaTime){
        // create new lasers
        // enemy lasers
        ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator();
        while(enemyShipListIterator.hasNext()){
            EnemyShip enemyShip = enemyShipListIterator.next();
            if (enemyShip.canFireLaser()) {
                Laser[] lasers = enemyShip.fireLasers();
                enemyLaserList.addAll(Arrays.asList(lasers));
                }
        }


        // draw lasers
        // remove old lasers
        ListIterator<Laser> iterator = enemyLaserList.listIterator();
        while(iterator.hasNext()){
            Laser laser = iterator.next();
            laser.draw(game.batch);
            laser.boundingBox.y-=laser.movementSpeed*deltaTime;
            if(laser.boundingBox.y>WORLD_HEIGHT){
                iterator.remove();
            }
        }

    }

    public void spawnEnemyShips(float deltaTime){
        Texture enemyShipTexture = new Texture("enemyRed3.png");
        Texture enemyLaserTexture = new Texture("laserRed03.png");

        enemySpawnTimer+=deltaTime;

        if(enemySpawnTimer>timeBetweenEnemySpawns) {
            enemyShipList.add(new EnemyShip(this.random.nextFloat() * (WORLD_WIDTH - 10) + 5, WORLD_HEIGHT - 5, 10, 10,
                    48, 0.3f, 5, 50, 0.8f,
                    enemyShipTexture, enemyLaserTexture));
            enemySpawnTimer-=timeBetweenEnemySpawns;
        }

    }

    private void moveEnemy(EnemyShip enemyShip, float deltaTime){
        // strategy: determine the max distance the ship can move

        float leftLimit, rightLimit,  upLimit, downLimit;
        leftLimit = -enemyShip.boundingBox.x;
        downLimit = (float)WORLD_HEIGHT/2-enemyShip.boundingBox.y;
        rightLimit = WORLD_WIDTH-enemyShip.boundingBox.x-enemyShip.boundingBox.width;
        upLimit = WORLD_HEIGHT-enemyShip.boundingBox.y-enemyShip.boundingBox.height;

        float xMove = enemyShip.getDirectionVector().x*enemyShip.movementSpeed*deltaTime;
        float yMove = enemyShip.getDirectionVector().y*enemyShip.movementSpeed*deltaTime;
        if (xMove>0) xMove=Math.min(xMove,rightLimit);
        else xMove = Math.max(xMove, leftLimit);

        if (yMove>0) yMove = Math.min(yMove, upLimit);
        else yMove = Math.max(yMove, downLimit);

        enemyShip.translate(xMove, yMove);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        game.batch.setProjectionMatrix(camera.combined);
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
