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



    // World Parameters
    private final int WORLD_WIDTH= 72;
    private final int WORLD_HEIGHT= 128;

    public GameScreen(MainGame game)
    {
        camera = new OrthographicCamera();
        viewport= new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        //Setup for the background
        background= new Texture("mainScreen.jpg");


        //background scrolling starts here at below initialization
        backgroundOffset=0;

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
