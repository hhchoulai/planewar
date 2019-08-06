package com.neuedu.runtime;
/**
 * 敌方飞机  3
 */

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.ImageMap;
import java.awt.*;
import java.util.Random;

/**
 * 敌方飞机  1
 */
public class EnemyPlane extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private int speed= FrameConstant.GAME_SPEED*4;
    private Random random=new Random();
    public EnemyPlane() {
        this(0,0, ImageMap.get("ep1"));
    }

    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }


    /**
     * 向GameFrame里添加子弹   初始化敌军子弹
     */
    public void fire(){
//        随机生成子弹  就是为了让子弹出现的不那么频繁
        if (random.nextInt(1000)>990) {
            GameFrame.getInstance().enemyBulletList.add(
                    new EnemyBullet(getX()+image.getWidth(null)/2-ImageMap.get("eb1").getWidth(null)/2,
                            getY()+image.getHeight(null),
                            ImageMap.get("eb1")));
        }
    }
    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        fire();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);


    }

    @Override
    public void move() {
        setY(getY()+speed);
    }
    /**
     * 边缘检测
     * 飞出边缘从集合remove
     */
    public void borderTesting(){
        if (getY()+image.getHeight(null)>FrameConstant.FRAME_HEIGHT){
            GameFrame.getInstance().enemyPlaneList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    /**
     * 判断 敌方飞机与我方飞机的碰撞
     */
    public void Crash(Plane plane){
            if (Plane.unmatched){
                for (EnemyPlane enemyPlane : GameFrame.getInstance().enemyPlaneList) {
                    if (enemyPlane.getRectangle().intersects(plane.getRectangle())) {
                        GameFrame.getInstance().enemyPlaneList.remove(this);
                    }
                }
            }else {
                for (EnemyPlane enemyPlane : GameFrame.getInstance().enemyPlaneList) {
                    if (enemyPlane.getRectangle().intersects(plane.getRectangle())) {
                        GameFrame.getInstance().enemyPlaneList.remove(this);
                        GameFrame.getInstance().hp--;
                        if (GameFrame.getInstance().hp<=0){
                            GameFrame.getInstance().gameOver=true;
                        }
                    }
                }
            }

        }

    }

