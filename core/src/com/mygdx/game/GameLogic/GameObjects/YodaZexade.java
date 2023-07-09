package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class YodaZexade extends Yoda {
    private Texture yodaZexadeImg;

    public YodaZexade(float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        yodaZexadeImg = new Texture(Gdx.files.internal("entity/yodaZexade.png"));
    }

    public void render(SpriteBatch batch) {
        batch.draw(yodaZexadeImg, getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
    }
}
