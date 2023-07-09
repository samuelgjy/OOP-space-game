package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Yoda73 extends Yoda {
    private Texture yoda73Img;

    public Yoda73(float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        yoda73Img = new Texture(Gdx.files.internal("entity/yoda73.png"));
    }

    public void render(SpriteBatch batch) {
        batch.draw(yoda73Img, getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
    }
}
