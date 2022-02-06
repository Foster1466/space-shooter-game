package com.javasupremacy.hardmode.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.javasupremacy.hardmode.MainGame;
import com.javasupremacy.hardmode.utils.Constant;

public class MainGameScreen implements Screen {

    Texture img;
    Texture background;
    float x;
    float y;
    float speed = 5;

    MainGame game;

    public MainGameScreen(MainGame game) {
        this.game = game;
    } // get main game instance from outside

    @Override
    public void show() {
        img = new Texture("badlogic.jpg");
        background = new Texture("background.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Constant.UP)) {
            y += speed;
        }
        if (Gdx.input.isKeyPressed(Constant.DOWN)) {
            y -= speed;
        }
        if (Gdx.input.isKeyPressed(Constant.RIGHT)) {
            x += speed;
        }
        if (Gdx.input.isKeyPressed(Constant.LEFT)) {
            x -= speed;
        }
        // use MainGame's batch to draw things in screen rather than in MainGame itself
        game.batch.begin();
        game.batch.draw(background, 0, 0);
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
