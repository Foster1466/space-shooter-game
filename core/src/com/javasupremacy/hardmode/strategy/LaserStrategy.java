package com.javasupremacy.hardmode.strategy;

import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.movement.Movement;
import com.javasupremacy.hardmode.objects.EnemyLaser;

import java.util.List;

public interface LaserStrategy {
    public void setLaserMovement(Movement movement);
    public void fire (float deltaTime, Rectangle hitbox, List<EnemyLaser> enemyLaserList);
}
