package com.javasupremacy.hardmode.factories;

import com.javasupremacy.hardmode.objects.Enemy;
import com.javasupremacy.hardmode.objects.EnemyShipA;
import com.javasupremacy.hardmode.objects.EnemyShipB;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EnemyShipFactory extends EnemyFactory{

    private float nextTime;
    private Enemy nextEnemy;

    private Queue<Float> releaseTime;
    private Queue<Enemy> toBeReleased;

    public EnemyShipFactory() {
        super();
        initialize();
    }

    private void initialize() {
        releaseTime = new LinkedList<>();
        toBeReleased = new LinkedList<>();

        float timestamp = 5;
        for (int i = 0; i < 10; i++) {
            releaseTime.offer(timestamp);
            toBeReleased.offer(new EnemyShipA());
            timestamp += 2;
        }

        timestamp = 80;
        for (int i = 0; i < 10; i++) {
            releaseTime.offer(timestamp);
            toBeReleased.offer(new EnemyShipB());
            timestamp += 1;
        }

        if (!releaseTime.isEmpty()) {
            nextTime = releaseTime.poll();
            nextEnemy = toBeReleased.poll();
        }
    }

    @Override
    public void update(float deltaTime, List<Enemy> list) {
        this.clock += deltaTime;
        if (nextEnemy != null && this.clock > nextTime) {
            list.add(nextEnemy);
            nextEnemy = null;

            if (!releaseTime.isEmpty()) {
                nextTime = releaseTime.poll();
                nextEnemy = toBeReleased.poll();
            }
        }
    }
}
