package com.pig.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Stone {
    private Vector2 position;
    private float speed;


    private int screenWidth, screenHeight;
    private Random rX,rY;
    private float height = 0,width = 0;
    private boolean isUnder = false;
    private Rectangle boundingRectangle;

    public Stone(float width, float height, int i, float distanceY){
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        this.height = height;
        this.width = width;
        speed = 100;
        rX = new Random();
        rY = new Random();

        position = new Vector2(/*rX.nextInt(screenWidth - (int)width)*/ screenWidth / 2,screenHeight + distanceY * i);
        boundingRectangle = new Rectangle();
    }

    public void reset(int i, float distanceY){
            position.set(/*rX.nextInt(screenWidth - (int)width)*/ screenWidth / 2, screenHeight);
            boundingRectangle.set(position.x,position.y,width,height);
            isUnder = false;
    }

    public void update(float delta){
        if(position.y < -height){
            isUnder = true;
        }
        position.add(0,-speed * delta);

        boundingRectangle.set(position.x,position.y,width,height);
    }

    public void stop(){
        speed = 0;
    }


    public void restart(int i,float distanceY){
        speed = 100;
        reset(i, distanceY);
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getHeight(){
        return height;
    }

    public float getWidth(){
        return width;
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed){this.speed = speed;}


    public void addSpeed(float delta){
        this.speed += delta;
    }

    public boolean isUnder() {
        return isUnder;
    }
}
