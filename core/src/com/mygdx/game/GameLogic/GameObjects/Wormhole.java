package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.EntityManager.CollidableEntity;

public class Wormhole extends CollidableEntity {
    private Texture wormholeImg;

    public Wormhole(float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        wormholeImg = new Texture(Gdx.files.internal("entity/wormhole_entry.png"));
    }

    @Override
    public boolean isCollideBox(CollidableEntity object) {
        float leftA = getX() - (getWidth()/8);
        float leftB = object.getX() - (object.getWidth()/2);
        float rightA = getX() + (getWidth()/8);
        float rightB = object.getX() + (object.getWidth()/2);
        float topA = getY() + (getHeight()/8);
        float topB = object.getY() + (object.getHeight()/2);
        float bottomA = getY() - (getHeight()/8);
        float bottomB = object.getY() - (object.getHeight()/2);

        if (leftA < rightB) {
            if (leftB < rightA) {
                if (topA > bottomB) {
                    if (topB > bottomA) {
                        System.out.print("Collided\n");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(wormholeImg, getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
    }
}
