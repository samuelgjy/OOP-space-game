package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameEngine.EntityManager.NonCollidableEntity;

public class WormholeExit extends NonCollidableEntity {
    private Texture wormholeImg;

    public WormholeExit(float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        wormholeImg = new Texture(Gdx.files.internal("entity/wormhole_exit.png"));
    }

    public void render(SpriteBatch batch) {
        batch.draw(wormholeImg, getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
    }
}
