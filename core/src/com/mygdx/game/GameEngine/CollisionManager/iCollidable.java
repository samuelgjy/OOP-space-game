package com.mygdx.game.GameEngine.CollisionManager;


import com.mygdx.game.GameEngine.EntityManager.CollidableEntity;

public interface iCollidable {

    public boolean isCollideCircle(CollidableEntity object);
    public boolean isCollideBox(CollidableEntity object);
    public boolean detectBorder(CollidableEntity object, float os_left, float os_right, float os_top, float os_bottom);

}
