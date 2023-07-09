package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameEngine.EntityManager.CollidableEntity;


public class Asteroid extends CollidableEntity {
    private Texture asteroidImg;

    public Asteroid(float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        asteroidImg = new Texture(Gdx.files.internal("entity/meteor.png"));
    }

    public void render(SpriteBatch batch) {
        batch.draw(asteroidImg, getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
    }

}