package com.pig.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.pig.assetLoader.AssetLoader;
import com.pig.button.SimpleButton;
import com.pig.gameWorld.GameWorld;
import com.pig.objects.Raft;
import com.pig.objects.Stone;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.util.EventReaderDelegate;

public class InputHandler implements InputProcessor {
    private Raft raft;
    private GameWorld world;
    private List<SimpleButton> menuButtons;

    private SimpleButton playButton;

    public InputHandler(GameWorld myWorld){
        this.world = myWorld;
        raft = myWorld.getRaft();

        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()- AssetLoader.playmenu.getRegionHeight(),
                AssetLoader.playmenu.getRegionWidth(),
                AssetLoader.playmenu.getRegionHeight(),
                AssetLoader.playmenu
                );
        menuButtons.add(playButton);
    }

    @Override
    public boolean keyDown(int keycode) {
            return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(screenX < raft.getX())
        {
            if(screenY > raft.getY())
                raft.update(raft.getX() - 5,raft.getY() - 5);

            if(screenY < raft.getY())
                raft.update(raft.getX() - 5,raft.getY() + 5);
        }
        else
            if(screenX > raft.getX())
            {
                if(screenY > raft.getY())
                    raft.update(raft.getX() + 5,raft.getY() - 5);

                if(screenY < raft.getY())
                    raft.update(raft.getX() + 5,raft.getY() + 5);
            }

        if(world.isLose()){
            world.restart();
        }


        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float sX = screenX - raft.getX() - raft.getWidth()/2;
        float sY = screenY - raft.getY() - raft.getHeight()/2;

        if((sX < 50 && sX > -50) || (sY < 50 && sY > -50)){
            if((screenX != raft.getX() || screenY != raft.getY()))
                raft.update(screenX - raft.getWidth()/2, Gdx.graphics.getHeight() - screenY - raft.getHeight()/2);
        }

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
