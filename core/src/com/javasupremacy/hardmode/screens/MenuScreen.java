package com.javasupremacy.hardmode.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.javasupremacy.hardmode.MainGame;
import com.badlogic.gdx.ApplicationListener;

import static java.awt.Color.*;

public class MenuScreen implements Screen {
    Texture img;
    MainGame game;
    float x;
    float y;
    public MenuScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        img = new Texture("background.jpg");
    }




    @Override
    public void render(float delta) {
        // Gdx.gl.glClearColor(.1f,1,0,1);
        Gdx.gl.glClearColor(255/255f, 255/255f, 255/255f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(img, x, y);



        game.batch.end();
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
