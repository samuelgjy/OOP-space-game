package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class YodaBirthday extends Yoda {
    private Texture yodaBirthdayImg;

    public YodaBirthday(float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        yodaBirthdayImg = new Texture(Gdx.files.internal("entity/yodaBirthday.png"));
    }

    public void render(SpriteBatch batch) {
        batch.draw(yodaBirthdayImg, getX() - (getWidth() / 2), getY() - (getHeight() / 2), getWidth(), getHeight());
    }
}
