package com.javasupremacy.hardmode.factories;

import com.javasupremacy.hardmode.objects.Enemy;
import com.javasupremacy.hardmode.objects.EnemyShipA;
import com.javasupremacy.hardmode.objects.EnemyShipB;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EnemyShipFactory extends EnemyFactory{

    private float nextTime;
    private Enemy nextEnemy;

    private Queue<Float> releaseTime;
    private Queue<Class> toBeReleased;

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
            toBeReleased.offer(EnemyShipA.class);
            timestamp += 2;
        }

        timestamp = 80;
        for (int i = 0; i < 10; i++) {
            releaseTime.offer(timestamp);
            toBeReleased.offer(EnemyShipB.class);
            timestamp += 1;
        }

        updateNext();
    }

    @Override
    public void produce(float deltaTime, List<Enemy> list) {
        this.clock += deltaTime;
        if (nextEnemy != null && this.clock > nextTime) {
            list.add(nextEnemy);
            nextEnemy = null;
            updateNext();
        }
    }

    private void updateNext() {
        if (!releaseTime.isEmpty()) {
            nextTime = releaseTime.poll();
            Class cls = toBeReleased.poll();
            try {
                nextEnemy = (Enemy) cls.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
