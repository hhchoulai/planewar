package com.neuedu.base;

/**
 * 所以精灵的基类，里边只存放属性，方法用接口
 */
public abstract class BaseSprite {
    private int x;
    private int y;

    public BaseSprite() {
    }

    public BaseSprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
