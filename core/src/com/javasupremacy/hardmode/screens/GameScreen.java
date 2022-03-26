package com.javasupremacy.hardmode.screens;

//This is my class where I wrote code
//This class consists of two execution one is scrolling back and character included

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javasupremacy.hardmode.MainGame;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.javasupremacy.hardmode.factories.BossFactory;
import com.javasupremacy.hardmode.factories.EnemyFactory;
import com.javasupremacy.hardmode.factories.EnemyShipFactory;
import com.javasupremacy.hardmode.objects.*;
import com.javasupremacy.hardmode.systems.GameSystem;
import com.javasupremacy.hardmode.systems.ScoreSystem;
import com.javasupremacy.hardmode.utils.Constant;
import com.javasupremacy.hardmode.utils.PlayerCommand;

import java.util.*;


public class GameScreen implements Screen {
    private BackgroundScreen backScreen;
    private final Camera cameraForeground;
    private final Viewport viewportForeground;
    private MainGame game;
    private int foregroundOffset;

    private Texture foreground;
    private SpriteBatch sbatch;

    private GameSystem gameSystem;
    private ScoreSystem scoreSystem;

    public GameScreen(MainGame game) {
        sbatch = new SpriteBatch();
        this.backScreen = new BackgroundScreen();
        this.cameraForeground = new OrthographicCamera();
        ((OrthographicCamera) cameraForeground).setToOrtho(false, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        this.viewportForeground = new StretchViewport(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT,cameraForeground);

        foregroundOffset = 0;
        foreground = new Texture("back.jpg");

        gameSystem = new GameSystem();
        scoreSystem = new ScoreSystem();
        gameSystem.setScoreSystem(scoreSystem);

        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        this.backScreen.renderBackground();
        sbatch.setProjectionMatrix(cameraForeground.combined);
        Gdx.gl.glViewport(10,10, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);

        sbatch.begin();
        rollingForeground();
        gameSystem.render(sbatch, deltaTime);
        sbatch.end();

        if (gameSystem.canEnd()) {
            this.gameEnd();
        }
    }

    private void rollingForeground() {
        foregroundOffset++;
        if (foregroundOffset % Constant.WINDOW_HEIGHT == 0) {
            foregroundOffset = 0;
        }
        sbatch.draw(foreground, 0, -foregroundOffset, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        sbatch.draw(foreground, 0, -foregroundOffset + Constant.WINDOW_HEIGHT, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
    }

    private void gameEnd() {
        this.dispose();
        game.setScreen(new GameOverScreen(game));
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
