package com.mygdx.game.GameEngine.EntityManager;
import com.mygdx.game.GameEngine.CollisionManager.iCollidable;


public class CollidableEntity extends EntityParent implements iCollidable {
    public CollidableEntity (float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
    }

    public CollidableEntity(){
    }

    public float getRadius() {
        return (float)(Math.sqrt(Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2))/2);
    }

    @Override
    public boolean isCollideCircle(CollidableEntity object) {
        double dist_sqr = Math.pow(this.getX() - object.getX(), 2) + Math.pow(this.getY() - object.getY(), 2);
        double radius_total_sqr = Math.pow(this.getRadius() + object.getRadius(), 2);
        if (dist_sqr < radius_total_sqr) {
//            System.out.print("Collided\n");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isCollideBox(CollidableEntity object) {
        float leftA = this.getX() - (this.getWidth()/2);
        float leftB = object.getX() - (object.getWidth()/2);
        float rightA = this.getX() + (this.getWidth()/2);
        float rightB = object.getX() + (object.getWidth()/2);
        float topA = this.getY() + (this.getHeight()/2);
        float topB = object.getY() + (object.getHeight()/2);
        float bottomA = this.getY() - (this.getHeight()/2);
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

    @Override
    public boolean detectBorder(CollidableEntity object, float os_left, float os_right, float os_top, float os_bottom) {
        float left = object.getX() - (object.getWidth()/2);
        float right = object.getX() + (object.getWidth()/2);
        float top = object.getY() + (object.getHeight()/2);
        float bottom = object.getY() - (object.getHeight()/2);

        if (left >= 0 + os_left) {
            if (right <= 800 - os_right) {
                if (top <= 600 - os_top) {
                    if (bottom >= 0 + os_bottom) {
                        return false;
                    }
                }
            }
        }
//        System.out.print("Border\n");
        return true;
    }
}