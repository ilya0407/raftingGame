package com.pig.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.pig.gameWorld.GameRenderer;
import com.pig.gameWorld.GameWorld;
import com.pig.input.InputHandler;

public class GameScreen implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;
    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();

    private int i = 0;

    public GameScreen(){
        float gameWidth = screenWidth;
        float gameHeight = screenHeight;
        world = new GameWorld();
        Gdx.input.setInputProcessor(new InputHandler(world));
        renderer = new GameRenderer(world,gameHeight,gameWidth);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(delta,runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
