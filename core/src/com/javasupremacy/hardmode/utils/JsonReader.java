package com.javasupremacy.hardmode.utils;

import com.badlogic.gdx.Gdx;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    private Object obj = null;
    private JSONObject js;
    private int phoneNumber;
    public JsonReader(int level) {
        try {
            switch (level) {
                case 1:
                    obj = new JSONParser().parse(Gdx.files.internal("glassy/skin/level1.json").readString());
                    break;
                case 2:
                    obj = new JSONParser().parse(Gdx.files.internal("glassy/skin/level2.json").readString());
                    break;
            }
       } catch (ParseException e) {
            e.printStackTrace();
        }
        js = (JSONObject) obj;
        JSONObject subObj = (JSONObject) js.get("player");
        phoneNumber = Integer.valueOf((String) subObj.get("lives"));
    }

    public int getPhoneNumber(){
        return this.phoneNumber;
    }
}
