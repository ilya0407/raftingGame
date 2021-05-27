package com.pig.assetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture raft;
    public static Texture stone;
    public static Texture water;
    public static Texture raftLeft;
    public static Texture raftRight;
    public static Texture menu;
    public static TextureRegion playmenu;


    public static void load(){
        raft = new Texture(Gdx.files.internal("raft.png"));
        stone = new Texture(Gdx.files.internal("stone_1.png"));
        water = new Texture(Gdx.files.internal("water.png"));
        raftRight = new Texture(Gdx.files.internal("right.png"));
        raftLeft = new Texture(Gdx.files.internal("left.png"));
        menu = new Texture(Gdx.files.internal("menu.png"));

        playmenu = new TextureRegion(menu,0,0,440,150);
    }

    public static void dispose(){
        raft.dispose();
        stone.dispose();
        water.dispose();
        raftLeft.dispose();
        raftRight.dispose();
    }
}
