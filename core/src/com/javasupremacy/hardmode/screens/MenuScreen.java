package com.javasupremacy.hardmode.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.javasupremacy.hardmode.MainGame;
import com.badlogic.gdx.ApplicationListener;

import static java.awt.Color.*;

public class MenuScreen implements Screen {
    private MainGame game;
    private Stage stage;
    private Skin skin;

    private Label outputLabel;

    public MenuScreen(MainGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
        Gdx.input.setInputProcessor(stage);
        loadButtons();
    }

    private void loadButtons() {
        int sizeUnit = 60;
        final TextButton button1 = new TextButton("Start", skin, "small");
        button1.setSize(sizeUnit * 4, sizeUnit);
        button1.setPosition((Gdx.graphics.getWidth() - button1.getWidth()) / 2,
                300);
        button1.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        button1.getLabel().setFontScale(1.5f, 1.5f);

        final TextButton button2 = new TextButton("Options", skin, "small");
        button2.setSize(sizeUnit * 4, sizeUnit);
        button2.setPosition((Gdx.graphics.getWidth() - button2.getWidth()) / 2,
                200);
        button2.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        button2.getLabel().setFontScale(1.5f, 1.5f);

        final TextButton button3 = new TextButton("Exit", skin, "small");
        button3.setSize(sizeUnit * 4, sizeUnit);
        button3.setPosition((Gdx.graphics.getWidth() - button3.getWidth()) / 2,
                100);
        button3.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });
        button3.getLabel().setFontScale(1.5f, 1.5f);

        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
    }

    @Override
    public void show() {

    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
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
