package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameEngine.EntityManager.NonCollidableEntity;

public class Yoda extends NonCollidableEntity {
    private Texture yoda;

    public Yoda(float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        yoda = new Texture(Gdx.files.internal("entity/yoda.png"));
    }

    public void render(SpriteBatch batch) {
        batch.draw(yoda, getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
    }

}
