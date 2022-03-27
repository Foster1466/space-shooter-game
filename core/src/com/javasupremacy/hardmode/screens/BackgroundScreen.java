package com.javasupremacy.hardmode.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.javasupremacy.hardmode.utils.Constant;

public class BackgroundScreen {
    private final Camera cameraBackground;
    private final Viewport viewportBackground;
    private final Texture background;
    private final Texture heart;
    private final BitmapFont font0, font1;
    private SpriteBatch sbatch;
    private String mode;


    public BackgroundScreen(int heartCount) {
        this.cameraBackground = new OrthographicCamera();
        ((OrthographicCamera) cameraBackground).setToOrtho(false, Constant.EXT_WINDOW_WIDTH, Constant.EXT_WINDOW_HEIGHT);
        this.viewportBackground = new StretchViewport(Constant.EXT_WINDOW_WIDTH, Constant.EXT_WINDOW_HEIGHT,cameraBackground);
        this.background = new Texture("mainScreen.jpg");
        this.heart = new Texture("heart.png");
        this.font0 = new BitmapFont();
        this.font1 = new BitmapFont();
        this.font0.setColor(0, 0, 0, 1);
        this.font0.getData().setScale(2f);
        this.font1.setColor(1,1,1,1);
        this.font1.getData().setScale(2f);
        this.mode = "Normal speed";
        //score=0;
        sbatch = new SpriteBatch();
    }

    public void renderBackground(int heartCount, int score){
        sbatch.setProjectionMatrix(cameraBackground.combined);
        Gdx.gl.glViewport(0,0, Constant.EXT_WINDOW_WIDTH, Constant.EXT_WINDOW_HEIGHT);
        sbatch.begin();
        //System.out.print("/////\n");
        sbatch.draw(this.background, 0, 0, Constant.EXT_WINDOW_WIDTH, Constant.EXT_WINDOW_HEIGHT);
        this.checkMode();
        font0.draw(sbatch, mode, Constant.WINDOW_WIDTH+55, Constant.WINDOW_HEIGHT-20);
        //font1.draw(sbatch, "HiScore: "+String.format("%08d", HiScore), Constant.WINDOW_WIDTH+15, Constant.WINDOW_HEIGHT-60);
        font1.draw(sbatch, "Score: "+String.format("%08d", score), Constant.WINDOW_WIDTH+15, Constant.WINDOW_HEIGHT-60);
        font0.draw(sbatch, "HP: ", Constant.WINDOW_WIDTH+15, Constant.WINDOW_HEIGHT-100);
        for(int i=0; i<heartCount; i++)
            sbatch.draw(heart, Constant.WINDOW_WIDTH+15+((i%6)*50), Constant.WINDOW_HEIGHT-(170+50*(i/6)), 40,40);
        sbatch.end();
    }

    private void checkMode(){
        if (Gdx.input.isKeyPressed(Constant.SLOW_MODE))
            this.mode = "Slow speed";
        else
            this.mode = "Normal speed";
    }
}
