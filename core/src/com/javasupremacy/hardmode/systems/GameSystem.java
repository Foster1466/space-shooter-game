package com.javasupremacy.hardmode.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javasupremacy.hardmode.factories.BossFactory;
import com.javasupremacy.hardmode.factories.EnemyFactory;
import com.javasupremacy.hardmode.factories.EnemyShipFactory;
import com.javasupremacy.hardmode.objects.Enemy;
import com.javasupremacy.hardmode.objects.EnemyLaser;
import com.javasupremacy.hardmode.objects.PlayerBullet;
import com.javasupremacy.hardmode.objects.PlayerShip;
import com.javasupremacy.hardmode.utils.Constant;
import com.javasupremacy.hardmode.utils.PlayerCommand;

import java.util.ArrayList;
import java.util.List;

public class GameSystem {
    private float timestamp;
    // Game Objects
    PlayerShip playerShip;
    private List<PlayerBullet> bullets;
    private List<Enemy> enemyShipList;
    private List<EnemyLaser> enemyLaserList;
    private List<EnemyLaser> specailLaserList;

    // Input command
    private PlayerCommand command;

    // Factories
    private List<EnemyFactory> factoryList;
    private ScoreSystem scoreSystem;

    public GameSystem() {
        timestamp = 0;

        bullets = new ArrayList<>();
        playerShip = new PlayerShip(bullets);
        enemyLaserList = new ArrayList<>();
        enemyShipList = new ArrayList<>();
        specailLaserList = new ArrayList<>();

        command = new PlayerCommand();
        command.add(playerShip);

        factoryList = new ArrayList<>();
        factoryList.add(new BossFactory());
        factoryList.add(new EnemyShipFactory());

        scoreSystem = new ScoreSystem();
    }

    /**
     * Read JSON for milestone 3
     */
    private void initialize() {

    }

    public void render(SpriteBatch sbatch, float deltaTime) {
        updateGame(deltaTime);
        renderEnemy(sbatch, deltaTime);
        renderEnemyLasers(sbatch, deltaTime);
        renderShip(sbatch, deltaTime);
        renderShipBullet(sbatch, deltaTime);
    }

    private void updateGame(float deltaTime) {
        timestamp += deltaTime;
        spawnEnemy(deltaTime);
        int destroyedScore = enemyCollision();
        this.scoreSystem.addScore(destroyedScore);
        if(playerCollision()) {
            this.scoreSystem.updateLives(-1);
        }
        command.run();
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

    /**
     * Find all enemy collision and remove destroyed
     * @return score for destroyed enemies
     */
    private int enemyCollision() {
        int score = 0;
        return score;
    }

    /**
     *
     * @return true if player is hit by any enemy laser
     */
    private boolean playerCollision() {
        return false;
    }

    /**
     * Iterate through list and draw enemy
     * Also add lasers if can fire
     * Remove out of screen enemies
     * @param deltaTime
     */
    private void renderEnemy(SpriteBatch sbatch, float deltaTime) {
        List<Enemy> removeList = new ArrayList<>();
        for (Enemy enemy : enemyShipList) {
            enemy.draw(sbatch, deltaTime);
            enemy.fire(deltaTime, enemyLaserList);
        }
        enemyShipList.removeAll(removeList);
    }

    /**
     * Render all lasers
     * Remove out of screen lasers
     * @param deltaTime
     */
    private void renderEnemyLasers(SpriteBatch sbatch, float deltaTime) {
        int countSpecialLaser=0;
        List<EnemyLaser> removeList1 = new ArrayList<>();
        List<EnemyLaser> removeList2 = new ArrayList<>();
        for (EnemyLaser enemyLaser : enemyLaserList) {
            enemyLaser.move(deltaTime);
            enemyLaser.draw(sbatch);
            if (enemyLaser.canRemove()) {
                removeList1.add(enemyLaser);
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
    private void renderShip(SpriteBatch sbatch, float deltaTime) {
        playerShip.draw(sbatch, deltaTime);
    }

    /**
     * Render player's bullet
     * @param deltaTime
     */
    private void renderShipBullet(SpriteBatch sbatch, float deltaTime) {
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

    public boolean canEnd() {
        return timestamp > Constant.GAME_LENGTH;
    }
}
