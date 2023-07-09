package com.mygdx.game.GameEngine.EntityManager;

abstract class EntityParent {
    private float x, y, speed, width, height;
    public EntityParent(float x, float y, float speed, float width, float height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public EntityParent() {
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getSpeed() {
        return speed;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public void setHeight(float height) {
        this.height = height;
    }
}