package com.javasupremacy.hardmode.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javasupremacy.hardmode.factories.BossFactory;
import com.javasupremacy.hardmode.factories.EnemyFactory;
import com.javasupremacy.hardmode.factories.EnemyShipFactory;
import com.javasupremacy.hardmode.objects.*;
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

        command = new PlayerCommand();
        command.add(playerShip);

        factoryList = new ArrayList<>();
        factoryList.add(new BossFactory());
        factoryList.add(new EnemyShipFactory());
    }

    public void setScoreSystem(ScoreSystem ss) {
        this.scoreSystem = ss;
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
        command.run();
        detectCollesion();
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

    private void detectCollesion() {
        playerCollision();
        enemyCollision();
    }

    private void playerCollision() {
        List<EnemyLaser> removeList = new ArrayList<>();
        for (EnemyLaser laser : enemyLaserList) {
            if (playerShip.overlaps(laser.hitbox)) {
                removeList.add(laser);
                scoreSystem.updateLives(-1);
            }
        }
        enemyLaserList.removeAll(removeList);
    }

    private void enemyCollision() {
        List<PlayerBullet> removeBulletList = new ArrayList<>();
        List<Enemy> removeEnemyList = new ArrayList<>();
        for (PlayerBullet bullet : bullets) {
            for (Enemy enemy : enemyShipList) {
                if (enemy.overlaps(bullet.hitbox)) {
                    enemy.hp -= 1;
                    removeBulletList.add(bullet);
                    if (enemy.hp <= 0) {
                        removeEnemyList.add(enemy);
                        enemy.die(scoreSystem);
                    }
                }
            }
        }
        bullets.removeAll(removeBulletList);
        enemyShipList.removeAll(removeEnemyList);
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
    private void renderEnemyLasers(SpriteBatch sbatch, float deltaTime) {
        List<EnemyLaser> removeList1 = new ArrayList<>();
        for (EnemyLaser enemyLaser : enemyLaserList) {
            enemyLaser.move(deltaTime);
            enemyLaser.draw(sbatch);
            if (enemyLaser.canRemove()) {
                removeList1.add(enemyLaser);
            }
        }
        enemyLaserList.removeAll(removeList1);
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
            bullet.move(deltaTime);
            bullet.draw(sbatch);
            if (bullet.canRemove()) {
                removeList.add(bullet);
            }
        }
        bullets.removeAll(removeList);
    }

    public boolean canEnd() {
        return timestamp > Constant.GAME_LENGTH || scoreSystem.canEnd();
    }
}
