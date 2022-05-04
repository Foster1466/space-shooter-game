package com.javasupremacy.hardmode.factories;

import com.javasupremacy.hardmode.objects.Enemy;
import com.javasupremacy.hardmode.objects.EnemyShip;
import com.javasupremacy.hardmode.objects.EnemyShipA;
import com.javasupremacy.hardmode.objects.EnemyShipB;
import org.json.simple.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EnemyShipFactory implements EnemyFactory{

    @Override
    public Enemy produce(JSONObject object) {
        return new EnemyShip.Builder()
                .hp(((Long) object.get("hp")).intValue())
                .texture((String) object.get("texture"))
                .hitbox(((Long) object.get("x")).intValue(),
                        ((Long) object.get("y")).intValue(),
                        ((Long) object.get("width")).intValue(),
                        ((Long) object.get("height")).intValue())
                .movement((String) object.get("movement"))
                .laserStrategy((String) object.get("laser"))
                .laserMovement((String) object.get("laserMovement"))
                .isFinalBoss((boolean) object.get("isFinalBoss"))
                .build();
    }
}
