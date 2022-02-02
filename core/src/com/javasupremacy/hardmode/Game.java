package com.javasupremacy.hardmode;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float x;
	float y;
	float speed = 5;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			y += speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			y -= speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			x += speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			x -= speed;
		}
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
}
