package com.pig.gameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.pig.objects.Raft;
import com.pig.objects.Stone;
import com.pig.screens.GameScreen;

import java.util.Random;

public class GameWorld {

    private Raft raft;

    private int NumberStones = 5;
    private int deltaY = 150;
    private Stone[] stone;
    private float speed;
    private float distanceY;
    private float distanceYmin;
    private float distanceYmax;
    private Random y;
    private int number_frame;

    private float runTime = 0;
    private State CurrentState = State.PLAY;
    public enum State {PLAY, LOSE};

    public GameWorld() {
       // deltaY = getYStones();
        raft = new Raft(Gdx.graphics.getWidth() / 2 - 50, 10, 100, 150);
        y = new Random();
        number_frame = 1;
        distanceYmin = raft.getHeight();

        distanceYmax = 200;
        distanceY = distanceYmax;

        stone = new Stone[NumberStones];

        for (int i = 0; i < NumberStones; i++) {
            stone[i] = new Stone(70, 30, i, distanceY);
        }

        speed = stone[0].getSpeed();
    }

    public Raft getRaft() {
        return raft;
    }

    public Stone getStone(int i) {
        return stone[i];
    }

    private float TimeToChangeSpeed = 0;
    private float timeSpeedDelta = 30;
    private int j = 0;

    public void update(float delta) {

        if (delta > .15f) {
            delta = .15f;
        }
        runTime += delta;

        if(stone[NumberStones - 1].isUnder()){
            number_frame++;
        }

        if (number_frame % 2 == 0) {
            distanceY -= 10;
        }


        for(int i = 0; i < NumberStones-1; i++){
            reset(i);
            stone[i].update(delta);
        }

    }

    private int getYStones(){
        Random RandomTime = new Random();
        int deltaY = RandomTime.nextInt(15) + 50;
        return deltaY;
    }

    private void reset(int i){
        if(stone[i].isUnder()){
            if(stone[i+1].getY() > distanceY) {
                stone[i].stop();
            }
            else
            {
                stone[i].setSpeed(speed);
                stone[i].reset(i,distanceY);
            }
        }
    }

    private void changeSpeed(int i){

        float speedDelta = 50;
        float MAX_SPEED = 100;

        if(stone[i].getSpeed() <= MAX_SPEED)
            stone[i].addSpeed(speedDelta);
    }

    private void stop(){
        raft.stop();
        for(int i = 0; i<NumberStones; i++){
            stone[i].stop();
        }
    }

    public void restart(){
        for(int i = 0; i<NumberStones; i++){
            stone[i].restart(i,distanceY);
        }
        CurrentState = State.PLAY;
    }

    private boolean collides(){

        for(int i = 0; i<NumberStones; i++) {

            if (Intersector.overlaps(raft.getBoundingRectangle(), stone[i].getBoundingRectangle()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean isLose(){
        return CurrentState == State.LOSE;
    }

    public boolean isPlay(){
        return CurrentState == State.PLAY;
    }

    public int getNumberStones() {
        return NumberStones;
    }

}
