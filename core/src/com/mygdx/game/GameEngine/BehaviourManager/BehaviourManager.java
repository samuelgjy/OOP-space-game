package com.mygdx.game.GameEngine.BehaviourManager;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameEngine.EntityManager.CollidableEntity;
import com.mygdx.game.GameLogic.GameObjects.Blackhole;
import com.mygdx.game.GameLogic.GameObjects.Healthbar;
import com.mygdx.game.GameLogic.GameObjects.Player;

import static com.badlogic.gdx.Gdx.graphics;

public class BehaviourManager {

    private Vector2 position;

    public static BehaviourManager instance;
    public BehaviourManager() {
    }

    public static BehaviourManager getInstance() {
        if (instance == null)
        {
            instance = new BehaviourManager();
        }
        return instance;
    }

    public Vector2 getPosition() {
        return position;
    }

    public static void handleCollision(CollidableEntity object) {
        if (object.getX() < object.getWidth()/2) {
            object.setX(object.getWidth()/2);
        }
        if (object.getX() > 800 - object.getWidth()/2) {
            object.setX(800 - object.getWidth()/2);
        }
        if (object.getY() > 600 - object.getHeight()/2) {
            object.setY(600 - object.getWidth()/2);
        }
        if (object.getY() < object.getHeight()/2) {
            object.setY(object.getWidth()/2);
        }
    }

    public static void handleCollision(CollidableEntity object1, CollidableEntity object2) {
        float x1 = object1.getX();
        float x2 = object2.getX();
        float y1 = object1.getY();
        float y2 = object2.getY();

        if (x1 >= x2) {
            object1.setX(object1.getX() + object1.getSpeed());
        }
        if (x1 < x2) {
            object1.setX(object1.getX() - object1.getSpeed());
        }
        if (y1 >= y2) {
            object1.setY(object1.getY() + object1.getSpeed());
        }
        if (y1 < y2) {
            object1.setY(object1.getY() - object1.getSpeed());
        }
    }

    public static void handleBorder(Player player, float os_left, float os_right, float os_top, float os_bottom) {
        // LEFT
        if (player.getX() < player.getWidth()/2 + os_left) {
            player.setX((player.getWidth()/2) + os_left);
            System.out.print("Left Border\n");
        }
        // RIGHT
        if (player.getX() > 800 - player.getWidth()/2 - os_right) {
            player.setX(800 - player.getWidth()/2 - os_right);
            System.out.print("Right Border\n");
        }
        // TOP
        if (player.getY() > 600 - player.getHeight()/2 - os_top) {
            player.setY(600 - player.getHeight()/2 - os_top);
            System.out.print("Top Border\n");
        }
        // BOTTOM
        if (player.getY() < player.getHeight()/2 + os_bottom) {
            player.setY(player.getHeight()/2 + os_bottom);
            System.out.print("Bottom Border\n");
        }
    }

    public void moveUp(CollidableEntity obj1) {
        obj1.setY(obj1.getY() + obj1.getSpeed());
    }
    public void moveDown(CollidableEntity obj1) {
        obj1.setY(obj1.getY() - obj1.getSpeed());
    }
    public void moveLeft(CollidableEntity obj1) {
        obj1.setX(obj1.getX() - obj1.getSpeed());
    }
    public void moveRight(CollidableEntity obj1) {
        obj1.setX(obj1.getX() + obj1.getSpeed());
    }

    private final float depleteRate = 1; // depletion rate per second
    private float timePassed = 0f;
    public void checkTime(float delta, Player player) {
        timePassed += delta;

        if (timePassed >= depleteRate) {
            timePassed -= depleteRate;
            player.changeHealthBy(-1);
        }
    }
}
