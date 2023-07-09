package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.GameEngine.EntityManager.CollidableEntity;

public class Satellite extends CollidableEntity {
    private Texture satelliteImg;

    public Satellite(float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        satelliteImg = new Texture(Gdx.files.internal("entity/satellite.png"));
    }

    public void render(SpriteBatch batch) {
        batch.draw(satelliteImg, getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
    }
}
