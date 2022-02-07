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

import static com.javasupremacy.hardmode.utils.Constant.WINDOW_HEIGHT;
import static com.javasupremacy.hardmode.utils.Constant.WINDOW_WIDTH;
import static java.awt.Color.*;

public class MenuScreen implements Screen {
    private MainGame game;
    Texture background;
    private int backgroundOffset;
    private Stage stage;
    private Skin skin;
    // Have to dig deeper sigh good night man
    public MenuScreen(MainGame game) {
        this.game = game;
        background = new Texture("backk.jpg");

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
        Gdx.input.setInputProcessor(stage);

        loadButtons();
    }

    private void loadButtons() {
        int sizeUnit = 60;

        // start button
        final TextButton button1 = new TextButton("Start", skin, "small");
        button1.setSize(sizeUnit * 4, sizeUnit);
        button1.setPosition((Gdx.graphics.getWidth() - button1.getWidth()) / 2,350);
        button1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        button1.getLabel().setFontScale(1.5f, 1.5f);

        // options button
        final TextButton button2 = new TextButton("Options", skin, "small");
        button2.setSize(sizeUnit * 4, sizeUnit);
        button2.setPosition((Gdx.graphics.getWidth() - button2.getWidth()) / 2,250);
        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                button2.setText("Options");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                button2.setText("In Progress");
                return true;
            }
        });
        button2.getLabel().setFontScale(1.5f, 1.5f);

        // exit button
        final TextButton button3 = new TextButton("Exit", skin, "small");
        button3.setSize(sizeUnit * 4, sizeUnit);
        button3.setPosition((Gdx.graphics.getWidth() - button3.getWidth()) / 2,150);
        button3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
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
        game.batch.begin();


        backgroundOffset++;

        if(backgroundOffset % WINDOW_HEIGHT == 0)
        {

            backgroundOffset=0;
        }
        game.batch.draw(background,  0, -backgroundOffset, WINDOW_WIDTH, WINDOW_HEIGHT);
        game.batch.draw(background,  0,  (-backgroundOffset+WINDOW_HEIGHT), WINDOW_WIDTH, WINDOW_HEIGHT);        game.batch.end();
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
