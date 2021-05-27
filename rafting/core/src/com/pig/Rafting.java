package com.pig;

import com.badlogic.gdx.Game;
import com.pig.assetLoader.AssetLoader;
import com.pig.screens.GameScreen;

public class Rafting extends Game {

    public void create(){
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    public void dispose(){
        super.dispose();
        AssetLoader.dispose();
    }
}
