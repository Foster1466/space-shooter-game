package com.javasupremacy.hardmode.factories;

import com.javasupremacy.hardmode.objects.Enemy;

import java.util.List;

abstract public class EnemyFactory {
    float clock;

    public EnemyFactory() {
        clock = 0;
    }

    abstract public void produce(float deltaTime, List<Enemy> list);
}
