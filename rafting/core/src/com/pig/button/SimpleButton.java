package com.pig.button;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SimpleButton {
    private float x, y, width, height;

    private TextureRegion button;

    private Rectangle bounds;

    private boolean isPressed = false;

    public SimpleButton(float x, float y, float width, float height,
                        TextureRegion button) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.button = button;

        bounds = new Rectangle(x, y, width, height);

    }

    public boolean isClicked(int screenX, int screenY) {
        return bounds.contains(screenX, screenY);
    }

    public void draw(SpriteBatch batcher) {
        batcher.draw(button, x, y, width, height);
    }

    public boolean isTouchDown(int screenX, int screenY) {

        if (bounds.contains(screenX, screenY)) {
            isPressed = true;
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int screenX, int screenY) {

        // Мы будем учитывать только touchUp в нажатом состоянии.
        if (bounds.contains(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        }

        // Когда палец с кнопки уберут, мы очистим флаг, что кнопка нажатая.
        isPressed = false;
        return false;
    }

}



