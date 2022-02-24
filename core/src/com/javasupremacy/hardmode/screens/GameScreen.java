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
import com.javasupremacy.hardmode.utils.Constant;

import java.util.*;


public class GameScreen implements Screen {
    private BackgroundScreen backScreen;
    private final Camera cameraForeground;
    private final Viewport viewportForeground;
    private MainGame game;
    private int foregroundOffset;
    //private int countSpecialLaser;
    private float timestamp;


    private Texture foreground;
    private SpriteBatch sbatch = new SpriteBatch();

    // game objects
    PlayerShip playerShip;
    private List<Enemy> enemyShipList;
    private List<EnemyLaser> enemyLaserList;
    private List<EnemyLaser> specailLaserList;
    private List<PlayerBullet> bullets;

    // factories
    private List<EnemyFactory> factoryList;

    public GameScreen(MainGame game) {
        this.backScreen = new BackgroundScreen();
        this.cameraForeground = new OrthographicCamera();
        ((OrthographicCamera) cameraForeground).setToOrtho(false, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        this.viewportForeground = new StretchViewport(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT,cameraForeground);

        foregroundOffset = 0;
        //countSpecialLaser = 0;
        foreground = new Texture("back.jpg");

        // Objects
        playerShip = new PlayerShip();
        enemyShipList = new ArrayList<>();
        enemyLaserList = new ArrayList<>();
        specailLaserList = new ArrayList<>();
        bullets = new ArrayList<>();

        // Factory
        factoryList = new ArrayList<>();
        factoryList.add(new EnemyShipFactory());
        factoryList.add(new BossFactory());

        timestamp = 0;
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
        spawnEnemy(deltaTime);
        renderEnemy(deltaTime);
        renderEnemyLasers(deltaTime);
        renderShip(deltaTime);
        renderShipBullet(deltaTime);
        sbatch.end();
        //game.batch.end();
        timestamp += deltaTime;
        if (timestamp > Constant.GAME_LENGTH) {
            gameEnd();
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

    /**
     * Iterate through list and draw enemy
     * Also add lasers if can fire
     * Remove out of screen enemies
     * @param deltaTime
     */
    private void renderEnemy(float deltaTime) {
        List<Enemy> removeList = new ArrayList<>();
        for (Enemy enemy : enemyShipList) {
            enemy.draw(sbatch, deltaTime);
            if (enemy.canFireLaser()) {
                enemyLaserList.addAll(Arrays.asList(enemy.fireLasers()));
                if(enemy.checkFinalBoss()&& timestamp>120)
                    specailLaserList.addAll(Arrays.asList(enemy.SpeicalfireLasers()));
            }
            if (enemy.isOutOfBounds()) {
                removeList.add(enemy);
            }
        }
        enemyShipList.removeAll(removeList);
    }

    /**
     * Render all lasers
     * Remove out of screen lasers
     * @param deltaTime
     */
    private void renderEnemyLasers(float deltaTime) {
        int countSpecialLaser=0;
        List<EnemyLaser> removeList1 = new ArrayList<>();
        List<EnemyLaser> removeList2 = new ArrayList<>();
        for (EnemyLaser enemyLaser : enemyLaserList) {
            enemyLaser.draw(sbatch);
            enemyLaser.boundingBox.y -= enemyLaser.movementSpeed * deltaTime;
            if (enemyLaser.canRemove()) {
                removeList1.add(enemyLaser);
            }
        }
        if(!specailLaserList.isEmpty() && timestamp>120) {
            for (EnemyLaser enemyLaser : specailLaserList) {
                enemyLaser.draw(sbatch);
                if(countSpecialLaser%3 ==0) {
                    enemyLaser.boundingBox.y -= enemyLaser.movementSpeed * deltaTime;
                    enemyLaser.boundingBox.x -= enemyLaser.movementSpeed * deltaTime;///
                }
                if(countSpecialLaser%3 ==1){
                    enemyLaser.boundingBox.y -= enemyLaser.movementSpeed * deltaTime;
                }
                if(countSpecialLaser%3 ==2){
                    enemyLaser.boundingBox.y -= enemyLaser.movementSpeed * deltaTime;
                    enemyLaser.boundingBox.x += enemyLaser.movementSpeed * deltaTime;///
                }
                countSpecialLaser++;
            }
        }
        enemyLaserList.removeAll(removeList1);
        if(countSpecialLaser%3 == 0) {
            specailLaserList.removeAll(removeList2);
        }
    }

    /**
     * Render player's ship
     * @param deltaTime
     */
    private void renderShip(float deltaTime) {
        playerShip.draw(sbatch, deltaTime);
    }

    /**
     * Render player's bullet
     * @param deltaTime
     */
    private void renderShipBullet(float deltaTime) {
        if (playerShip.isFiring()) {
            bullets.addAll(playerShip.fireBullet());
        }
        List<PlayerBullet> removeList = new ArrayList<>();
        for (PlayerBullet bullet : bullets) {
            bullet.update(deltaTime);
            bullet.render(sbatch);
            if (bullet.canRemove()) {
                removeList.add(bullet);
            }
        }
        bullets.removeAll(removeList);
    }

    /**
     * For each factory, add enemy into list based on deltaTime
     * @param deltaTime
     */
    private void spawnEnemy(float deltaTime) {
        for (EnemyFactory factory : factoryList) {
            factory.produce(deltaTime, this.enemyShipList);
        }
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
