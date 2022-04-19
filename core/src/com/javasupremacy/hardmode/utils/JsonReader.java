package com.javasupremacy.hardmode.utils;

import com.badlogic.gdx.Gdx;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JsonReader {
    private Object obj = null;
    private JSONObject js;
    private int livesNumber, bombsNumber;
    public JsonReader(int level) {
        try {
            switch (level) {
                case 1:
                    obj = new JSONParser().parse(Gdx.files.internal("config-json/level1.json").readString());
                    break;
                case 2:
                    obj = new JSONParser().parse(Gdx.files.internal("config-json/level2.json").readString());
                    break;
            }
       } catch (ParseException e) {
            e.printStackTrace();
        }
        js = (JSONObject) obj;
        JSONObject subObj = (JSONObject) js.get("player");
        livesNumber = Integer.valueOf((String) subObj.get("lives"));
        bombsNumber = Integer.valueOf((String) subObj.get("bombs"));
    }

    public int getLivesNumber(){
        return this.livesNumber;
    }

    public int getBombsNumber() {return this.bombsNumber;}
}
