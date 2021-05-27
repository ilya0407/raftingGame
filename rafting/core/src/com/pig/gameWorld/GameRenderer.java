package com.pig.gameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pig.assetLoader.AssetLoader;
import com.pig.objects.Raft;
import com.pig.objects.Stone;

public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private Raft raft;
    private Stone[] stone;

    private Texture Raft;
    private Texture RaftLeft;
    private Texture RaftRight;
    private Texture Stone;
    private Texture Water;
    private TextureRegion playMenu;

    private float timing;
    private int NumberStones = 10;

    public GameRenderer(GameWorld world, float gameHeight, float gameWidth){
        myWorld = world;

        cam = new OrthographicCamera();
        cam.setToOrtho(false,gameWidth,gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();
    }

    public void render(float delta, float runTime){
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(Water,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batcher.enableBlending();
        timing += delta;
        drawRaft(runTime);
        drawStones();
    //    drawMenu();
        batcher.end();
    }



    private void initGameObjects(){
        raft = myWorld.getRaft();
        NumberStones = myWorld.getNumberStones();
        stone = new Stone[NumberStones];
        for(int i = 0; i<NumberStones; i++) {
            stone[i] = myWorld.getStone(i);
        }
    }

    public void initAssets(){
        Raft = AssetLoader.raft;
        RaftLeft = AssetLoader.raftLeft;
        RaftRight = AssetLoader.raftRight;
        Stone = AssetLoader.stone;
        Water = AssetLoader.water;
        playMenu = AssetLoader.playmenu;
    }

    boolean left = false,right = true;

    private void drawRaft(float runTime){

        float drawRaftTime = 0.3f;

        if(raft.isLeft()){
            batcher.draw(RaftLeft,raft.getX(),raft.getY(),raft.getWidth(),raft.getHeight());
            left = true;
        }else
            if(raft.isRight()){
                batcher.draw(RaftRight,raft.getX(),raft.getY(),raft.getWidth(),raft.getHeight());
                right = true;
            }
            else if(timing < drawRaftTime){
                if(left)
                {
                    batcher.draw(RaftLeft,raft.getX(),raft.getY(),raft.getWidth(),raft.getHeight());
                }
                if(right)
                {
                    batcher.draw(RaftRight,raft.getX(),raft.getY(),raft.getWidth(),raft.getHeight());
                }
                if(!left && !right) {
                    batcher.draw(Raft, raft.getX(), raft.getY(), raft.getWidth(), raft.getHeight());
                }
            }else
                {
                    batcher.draw(Raft, raft.getX(), raft.getY(), raft.getWidth(), raft.getHeight());
                    timing = 0;
                    left = false;
                    right = false;
                }
    }

    private void drawStones() {
        for (int i = 0; i < NumberStones; i++) {
            batcher.draw(Stone, stone[i].getX(), stone[i].getY(), stone[i].getWidth(), stone[i].getHeight());
        }
    }

    private void drawMenu(){
        batcher.draw(playMenu,Gdx.graphics.getWidth()/2 - AssetLoader.playmenu.getRegionWidth()/2,Gdx.graphics.getHeight()/2);
    }
}
