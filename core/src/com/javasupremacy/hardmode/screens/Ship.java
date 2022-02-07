package com.javasupremacy.hardmode.screens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


class Ship {

    //ship characteristics
    float movementSpeed;  //world units per second
    int shield;

    //position & dimension
    float xPosition, yPosition; //lower-left corner
    float width, height;

    //graphics
    private Texture spaceship;


    public Ship(float movementSpeed,
                float width, float height,
                float xCentre, float yCentre,
                Texture spaceship) {
        this.movementSpeed = movementSpeed;
        this.xPosition = xCentre - width / 2;
        this.yPosition = yCentre - height / 2;
        this.width = width;
        this.height = height;
        this.spaceship = spaceship;

    }

    public void drawl(Batch batch) {
        batch.draw(spaceship, xPosition, yPosition, width, height);

    }
}
//    private MainGame game;
//    //screen
//    private Camera camera;
//    private Viewport viewport;
//
//
//
//    private Texture spaceship;
//    private float backgroundHeight;
//
//    private Texture playerShipTexture;
//    // World Parameters
//    private final int WORLD_WIDTH= 72;
//    private final int WORLD_HEIGHT= 128;
//
//    float speed = (float) 0.4;
//    float x= (float) (WORLD_WIDTH/2.5);
//    float y=WORLD_HEIGHT/85;
//
//
//
//
//
//    public void show() {
//
//        spaceship= new Texture("man.png");
//
//
//    }
//
//
//
//
//    public void render(float deltaTime) {
//
//        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
//
//        if (!Gdx.input.isKeyPressed(Constant.UP)) {
//        } else {
//            y += speed;
//        }
//        if (!Gdx.input.isKeyPressed(Constant.DOWN)) {
//        } else {
//            y -= speed;
//        }
//        if (Gdx.input.isKeyPressed(Constant.RIGHT)) {
//            x += speed;
//        }
//        if (Gdx.input.isKeyPressed(Constant.LEFT)) {
//            x -= speed;
//        }
//
//
//
//        game.batch.begin();
//
//        //scrolling background
//
//        //Below is a incrementor
//
//        game.batch.draw(spaceship, x, y);
//
//
//
//        game.batch.end();
//
//    }
//
//    public void resize(int width, int height) {
//        viewport.update(width,height,true);
//        game.batch.setProjectionMatrix(camera.combined);
//    }
//
//    public void pause() {
//
//    }
//
//    public void resume() {
//
//    }
//
//    public void hide() {
//
//    }
//
//    public void dispose() {
//
//    }
//


