package com.javasupremacy.hardmode.patterns;

import com.badlogic.gdx.math.Rectangle;
import com.javasupremacy.hardmode.objects.EnemyLaser;

import java.util.List;

public interface Pattern {
    public void fire(List<EnemyLaser> list, Rectangle boundingBox);
}
