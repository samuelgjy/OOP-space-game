package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class YodaHexafluoria extends Yoda {
    private Texture yodaHexafluoriaImg;

    public YodaHexafluoria(float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        yodaHexafluoriaImg = new Texture(Gdx.files.internal("entity/yodaHexafluoria.png"));
    }

    public void render(SpriteBatch batch) {
        batch.draw(yodaHexafluoriaImg, getX() - (getWidth() / 2), getY() - (getHeight() / 2), getWidth(), getHeight());
    }
}
