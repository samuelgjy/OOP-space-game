package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.EntityManager.CollidableEntity;

public class Player extends CollidableEntity {
    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightKey;
    private float radius;
    private float health;
    private boolean playerAlive = true;

    public Player(Vector2 position, float speed, float width, float height, float health, int upKey, int downKey, int leftKey, int rightKey) {
        super(position.x, position.y, speed, width, height);
        this.health = health;
        this.upKey = upKey;
        this.downKey = downKey;
        this.rightKey = rightKey;
        this.leftKey = leftKey;
        astro = new Texture(Gdx.files.internal("entity/astro.png"));
        radius = (float)(Math.sqrt(Math.pow(height, 2) + Math.pow(width,2))/2);
    }
    public float getRadius() {
        return radius;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }
    public void changeHealthBy(float health) {
        this.health += health;
    }
    public void accelerateForward(float speed) {
        setX(getX() + speed);
    }

    private Texture astro;

    public void render(SpriteBatch batch) {
        if (health <= 0) {
            setHealth(0);
        }
        if (health >= 100) {
            setHealth(100);
        }
        batch.draw(astro, getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
    }

    public int getUpKey() {
        return upKey;
    }

    public int getDownKey() {
        return downKey;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public boolean isPlayerAlive() {
        return playerAlive;
    }

    public void setPlayerAlive(boolean playerAlive) {
        this.playerAlive = playerAlive;
    }
}
