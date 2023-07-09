package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.GameEngine.EntityManager.CollidableEntity;

public class Oxygen extends CollidableEntity {
    private Texture oxygenImg;

    public Oxygen(float x, float y, float speed, float width) {
        super(x, y, speed, width, width);
        oxygenImg = new Texture(Gdx.files.internal("entity/oxygen.png"));
    }

    public float getRadius() {
        return getWidth()/2;
    }

    public void render(SpriteBatch batch) {
        batch.draw(oxygenImg, getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
    }

}
