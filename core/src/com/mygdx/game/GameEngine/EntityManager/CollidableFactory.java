package com.mygdx.game.GameEngine.EntityManager;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.GameEngine.CollisionManager.iCollidable;
import com.mygdx.game.GameLogic.GameObjects.*;

public class CollidableFactory {

    public static CollidableEntity getEntity(String entity) {
        if (entity == "Oxygen") {
            return new Oxygen(MathUtils.random(800,4000), MathUtils.random(0,600),400,50);
        }
        if (entity == "Asteroid") {
            return new Asteroid(MathUtils.random(800,4000), MathUtils.random(0,600),400,150,60);
        }
        if (entity == "Satellite") {
            return new Satellite(MathUtils.random(800,4000), MathUtils.random(0,600),300,70,125);
        }
        if (entity == "Blackhole") {
            return new Blackhole(MathUtils.random(800,2000), MathUtils.random(0,600),120,160,160,320,100);
        }
        if (entity == "Wormhole") {
            return new Wormhole(MathUtils.random(800,7000), MathUtils.random(0,600),120,MathUtils.random(100,150),220);
        }
        return null;
    }
}
