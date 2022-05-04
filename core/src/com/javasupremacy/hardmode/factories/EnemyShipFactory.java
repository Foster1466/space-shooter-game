package com.javasupremacy.hardmode.factories;

import com.javasupremacy.hardmode.objects.Enemy;
import com.javasupremacy.hardmode.objects.EnemyShip;
import org.json.simple.JSONObject;

public class EnemyShipFactory implements EnemyFactory{

    @Override
    public Enemy produce(JSONObject object) {
        return new EnemyShip.Builder()
                .hp(((Long) object.get("hp")).intValue())
                .score(((Long) object.get("reward")).intValue())
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
