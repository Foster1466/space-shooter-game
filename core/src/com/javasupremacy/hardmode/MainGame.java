package com.javasupremacy.hardmode;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javasupremacy.hardmode.screens.GameScreen;
import com.javasupremacy.hardmode.screens.MainGameScreen;
import com.javasupremacy.hardmode.screens.MenuScreen;

public class MainGame extends Game {

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}
}
