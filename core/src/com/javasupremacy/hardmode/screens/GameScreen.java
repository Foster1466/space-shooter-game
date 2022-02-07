package com.javasupremacy.hardmode.screens;

//This is my class where I wrote code
//This class consists of two execution one is scrolling back and character included

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.javasupremacy.hardmode.MainGame;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

import com.javasupremacy.hardmode.utils.Constant;

import java.util.ArrayList;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GameScreen implements Screen {
    private MainGame game;
    ArrayList<Bullet> bullets;

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private TextureAtlas textureAtlas;
    //private SpriteBatch batch;


    private Texture background;
    private Texture playerSpaceship;
    private float backgroundHeight;

    private Texture playerShipTexture;
    // World Parameters
    private final int WORLD_WIDTH= 72;
    private final int WORLD_HEIGHT= 128;

    //timing
    private int backgroundOffset;

    //game objects
    Ship playerShip;


    public GameScreen(MainGame game)
    {
        //batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport= new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        //Setup for the background
        background= new Texture("backk.jpg");
        playerSpaceship= new Texture("man.png");

        bullets = new ArrayList<Bullet>();


        //background scrolling starts here at below initialization
        backgroundOffset = 0;
        //set up game objects
        playerShip = new Ship(0.5F,5,5,WORLD_WIDTH/3,
                WORLD_HEIGHT/85,playerSpaceship);

        //Creating a screen constructor
        this.game = game;




    }

    @Override
    public void show() {
    }



    @Override
    public void render(float deltaTime) {

        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        //shooting code
        if(Gdx.input.isKeyPressed(Constant.SPACE))
        {
            bullets.add(new Bullet(playerShip.xPosition+1));
        }
        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for(Bullet bullet : bullets){
            bullet.update(deltaTime);
            if(bullet.remove)
            {
                bulletsToRemove.add(bullet);
            }
            bullets.removeAll(bulletsToRemove);
        }

        if (!Gdx.input.isKeyPressed(Constant.UP)) {
        } else {
            playerShip.yPosition += playerShip.movementSpeed;
        }
        if (!Gdx.input.isKeyPressed(Constant.DOWN)) {
        } else {
            playerShip.yPosition -= playerShip.movementSpeed;
        }
        if (Gdx.input.isKeyPressed(Constant.RIGHT)) {
            playerShip.xPosition += playerShip.movementSpeed;
        }
        if (Gdx.input.isKeyPressed(Constant.LEFT)) {
            playerShip.xPosition -= playerShip.movementSpeed;
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


        game.batch.draw(background, (float) 0, (float) -backgroundOffset, WORLD_WIDTH, WORLD_HEIGHT);
        game.batch.draw(background, (float) 0, (float) (-backgroundOffset+WORLD_HEIGHT), WORLD_WIDTH, WORLD_HEIGHT);

        for(Bullet bullet: bullets){
            bullet.render(game.batch);
        }

        playerShip.drawl(game.batch);


        game.batch.end();

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
