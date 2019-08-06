package com.neuedu.runtime;
/**
 * 敌机二
 */

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.Map;
import java.util.Random;

public class EnemyPlane2 extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private int speed= FrameConstant.GAME_SPEED*5;
    private Random random=new Random();
    private double a=Math.PI/2;//起始的a弧度数
    public EnemyPlane2() {
        this(0,0, ImageMap.get("ep2"));
    }

    public EnemyPlane2(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    /**
     *   初始化敌军子弹
     */
//    发射子弹
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
        a=a+Math.PI/12;
        setY(getY()+speed);
        setX((getX()+(int)Math.cos(a)*16));
    }
    /**
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
     * 判断 敌机2与我机碰撞
     * @param plane
     */
    public void Crash(Plane plane){
        if (Plane.unmatched){
            for (EnemyPlane2 enemyPlane2 : GameFrame.getInstance().enemyPlane2List) {
                if (enemyPlane2.getRectangle().intersects(plane.getRectangle())) {
                    GameFrame.getInstance().enemyPlane2List.remove(this);
                }
            }
        }else {
            for (EnemyPlane2 enemyPlane2 : GameFrame.getInstance().enemyPlane2List) {
                if (enemyPlane2.getRectangle().intersects(plane.getRectangle())) {
                    GameFrame.getInstance().enemyPlane2List.remove(this);
                    GameFrame.getInstance().hp-=2;
                    if (GameFrame.getInstance().hp<=0){
                        GameFrame.getInstance().gameOver=true;
                    }
                }
            }
        }

    }
}
