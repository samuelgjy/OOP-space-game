package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameEngine.EntityManager.NonCollidableEntity;

public class Healthbar extends NonCollidableEntity {
    private float health;
    private float startHealth;

    public Healthbar(float x, float y, float speed, float width, float height, Player player) {
        super(x, y, speed, width, height);
        this.health = player.getHealth();
        this.startHealth = health;
        bg = new Texture(Gdx.files.internal("entity/healthbar_background.png"));
        inner = new Texture(Gdx.files.internal("entity/healthbar_inside.png"));
        batch = new SpriteBatch();
    }

    private Texture inner;
    private Texture bg;
    private SpriteBatch batch;
    private float healthPercentage;

    public void render(Player player, SpriteBatch batch) {
        health = player.getHealth();
        if (health <= 0) {
            healthPercentage = 0;
        }
        else {
            healthPercentage = health / startHealth;
        }
        batch.draw(bg, 20, 550, getWidth(), getHeight());
        batch.draw(inner, 20, 550, getWidth() * healthPercentage, getHeight());
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}
