package com.javasupremacy.hardmode.factories;

import com.javasupremacy.hardmode.objects.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BossFactory extends EnemyFactory {

    private float nextTime;
    private Enemy nextEnemy;

    private Queue<Float> releaseTime;
    private Queue<Enemy> toBeReleased;

    public BossFactory() {
        super();
        initialize();
    }

    private void initialize() {
        releaseTime = new LinkedList<>();
        toBeReleased = new LinkedList<>();

        releaseTime.add(45f);
        toBeReleased.add(new MidBoss());

        releaseTime.add(90f);
        toBeReleased.add(new Boss());

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
