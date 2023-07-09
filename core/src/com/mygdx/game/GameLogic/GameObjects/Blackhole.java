package com.mygdx.game.GameLogic.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.GameEngine.AssetManager.AssetsManager;
import com.mygdx.game.GameEngine.EntityManager.CollidableEntity;

import static com.mygdx.game.GameEngine.AssetManager.AssetsManager.blackholeSound;

public class Blackhole extends CollidableEntity {
    private float outer_radius;
    private float gravity;
    private Texture blackholeImg;
    private Music blackholeSong;

    public Blackhole(float x, float y, float speed, float width, float height, float outer_radius, float gravity) {
        super(x, y, speed, width, height);
        this.outer_radius = outer_radius;
        this.gravity = gravity;
        blackholeImg = new Texture(Gdx.files.internal("entity/black_hole_old.png"));
        blackholeSong = AssetsManager.getManager().get(blackholeSound);
    }

    public float getOuterRadius() {
        return outer_radius;
    }
    public float getGravity() {
        return gravity;
    }

    @Override
    public boolean isCollideCircle(CollidableEntity object) {
        double dist_sqr = Math.pow(this.getX() - object.getX(), 2) + Math.pow(this.getY() - object.getY(), 2);
        double radius_total_sqr = Math.pow(getOuterRadius() + object.getRadius(), 2);
        if (dist_sqr < radius_total_sqr) {
            return true;
        } else {
            return false;
        }
    }

    public void blackholeGravity(Player player) {
        float x = this.getX() - player.getX();
        float y = this.getY() - player.getY();
        float norm = (float) Math.sqrt((x * x) + (y * y)); // distance

        if (norm <= this.getOuterRadius()/10) {

            player.setPlayerAlive(false);
            System.out.print("Ded!\n");
            blackholeSong.play();
        }
        else {
            x = x / norm;    // unit vector x
            y = y / norm;    // unit vector y

            float gravitationalForce = (this.getGravity() * 1000) / (norm * norm);
            player.setX(player.getX() + (gravitationalForce * x));
            player.setY(player.getY() + (gravitationalForce * y));
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(blackholeImg, getX()-(getWidth()/2), getY()-(getHeight()/2), getWidth(), getHeight());
    }
}
