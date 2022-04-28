package com.javasupremacy.hardmode.objects;

import com.badlogic.gdx.graphics.Texture;
import com.javasupremacy.hardmode.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class ClusterLaser {
    private List<EnemyLaser> cluster = new ArrayList<>();
    float xCLusterDirection, yCLusterDirection;
    public List<EnemyLaser> ClusterLaser(Texture laserTexture, int reduceSize,
                        int numLaser, float x, float y,
                        float laserWidth,  float laserHeight,
                        float laserMovementSpeed){
        int startDegree = 180/(numLaser+1);
        int intervalDegree = startDegree;
        if(Constant.HAS_CLUSTER)
            for(int i=0; i<numLaser; i++){
                xCLusterDirection = (float) Math.cos(Math.toRadians(startDegree+intervalDegree*i));
                yCLusterDirection = (float) Math.sin(Math.toRadians(startDegree+intervalDegree*i));
                cluster.add(new EnemyLaser.Builder(laserTexture)
                        .hitbox(x, y, laserWidth/reduceSize, laserHeight/reduceSize)
                        .speed(laserMovementSpeed)
                        .clusterDirection(xCLusterDirection, yCLusterDirection).build(false));
            }
        return cluster;
    }
}
