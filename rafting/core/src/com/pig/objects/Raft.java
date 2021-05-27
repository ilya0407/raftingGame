package com.pig.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Raft{
    private Vector2 position;

    private float width;
    private float height;
    private float s = 0;
    private Rectangle boundingRectangle;

    public Raft(float x, float y, float width, float height){
        this.width = width;
        this.height = height;

        position = new Vector2(x,y);
        boundingRectangle = new Rectangle();
    }


    public void update(float deltaX,float deltaY){
        s = position.x - deltaX;

        position.set(deltaX,deltaY);

        if(position.x <= 0){
            position.x = 0;
        }
        else
            if(position.x >= Gdx.graphics.getWidth()-width){
            position.x = Gdx.graphics.getWidth()-width;
        }

        if(position.y <= 0){
            position.y = 0;
        }else
            if(position.y >= Gdx.graphics.getHeight() - height){
            position.y = Gdx.graphics.getHeight() - height;
            }

        boundingRectangle.set(position.x ,position.y ,width - 10,height - 10);
    }


    public void stop(){

    }

    public boolean isLeft (){
        if(s > 0){
            s = 0;
            return true;
        }
        else return false;
    }

    public boolean isRight (){

        if(s < 0){
            s = 0;
            return true;
        }
        else return false;
    }



    public float getS() {
        return s;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight() {
        return height;
    }



    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }
}