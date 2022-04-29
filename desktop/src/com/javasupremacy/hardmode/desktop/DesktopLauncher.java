package com.javasupremacy.hardmode.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.javasupremacy.hardmode.MainGame;
import com.javasupremacy.hardmode.utils.Constant;
import com.javasupremacy.hardmode.utils.JsonReader;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constant.WINDOW_WIDTH;
		//changed
		config.height = Constant.WINDOW_HEIGHT;
		config.resizable = false;
		new LwjglApplication(new MainGame(), config);
	}
}
