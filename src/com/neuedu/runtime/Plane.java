package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.ImageMap;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Plane extends BaseSprite implements Moveable, Drawable {
    /**
     * 属性
     */
    private Image image;
    private int index;//控制敌机刷新的
    private int index1;//控制道具刷新的
    private int speed = FrameConstant.GAME_SPEED * 8;
    private boolean up, down, left, right;//上下左右移动飞机
    Random random = new Random();
    public static boolean unmatched=false;//飞机是否无敌  无敌的话子弹打它不起效  想10s消失的话 用多线程
    /**
     * 构造方法
     */
    public Plane() {
        this((FrameConstant.FRAME_WIDTH - ImageMap.get("mp1").getWidth(null)) / 2,
                FrameConstant.FRAME_HEIGHT - ImageMap.get("mp1").getHeight(null),
                ImageMap.get("mp1"));
    }

    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    /**
     * 重写draw  move方法
     *
     * @param g
     */

    @Override
    public void draw(Graphics g) {
        index++;
        index1++;
        move();
        borderTesting();
        createEnemyPlane();
        createTools();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }

    /**
     * 随机产生敌机
     * 只要我方飞机活着  重绘的时候就调用此方法来生成敌机
     */

    public void createEnemyPlane() {
        if (GameFrame.getInstance().score <15) {//当分数小于15的时候  产生第一种飞机
            if (index == 50) {//避免生成的太频繁  1s刷新50次
                int x=random.nextInt(800) -ImageMap.get("ep1").getWidth(null);
                if (x<0){
                    x=0;
                }
                GameFrame.getInstance().enemyPlaneList.add(
                        new EnemyPlane(
                                x,
                                0,
                                ImageMap.get("ep1")));
                index = 0;
            }
        } else if (GameFrame.getInstance().score >=15 && GameFrame.getInstance().score<=30) {
            if (index == 50) {//避免生成的太频繁  1s刷新50次
                int x=random.nextInt(800) - ImageMap.get("ep2").getWidth(null);
                if (x<0){
                    x=0;
                }
                GameFrame.getInstance().enemyPlane2List.add(
                        new EnemyPlane2(
                                x,
                                0,
                                ImageMap.get("ep2")));
                index = 0;
            }
        }

    }

    /**
     * 随机产生道具  初始化道具
     */
    public void createTools(){
        if (index1 ==250 ){    //5秒产生一个
            int x=random.nextInt(800) - ImageMap.get("addHp").getWidth(null);
            if (x<0){
                x=0;
            }
                GameFrame.getInstance().toolsList.add(new Tools(x,0,1));
                GameFrame.getInstance().toolsList.add(new Tools(x,0,2));
            index1=0;
        }
    }
    @Override
    public void move() {
        if (up) {
            setY(getY() - speed);
        }
        if (down) {
            setY(getY() + speed);
        }
        if (left) {
            setX(getX() - speed);
        }
        if (right) {
            setX(getX() + speed);
        }
    }

    /**
     * 监听事件  按键会怎样  写具体逻辑
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
//        一定是多个if  因为可能组合按键
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire();//每当抬起的时候调用fire方法  这样不会连发  如果放在draw里会出现连发
        }
    }

    /**
     * 开火方法  初始化我方子弹
     * 判断开关是否打开，如果打开了创建一个对象放入集合中  在按下J的时候调用
     */
    public void fire() {
        if (GameFrame.getInstance().score < 15) {
            GameFrame.getInstance().bulletList.add(new Bullet(
                    getX() + image.getWidth(null) / 2 - ImageMap.get("mb1").getWidth(null) / 2,
                    getY() - ImageMap.get("mb1").getHeight(null),
                    1
            ));
        } else if (GameFrame.getInstance().score >= 15 && GameFrame.getInstance().score < 30) {
            GameFrame.getInstance().bulletList.add(new Bullet(
                    getX() + image.getWidth(null) / 2 - ImageMap.get("mb2").getWidth(null) / 2,
                    getY() - ImageMap.get("mb2").getHeight(null),
                    2
            ));
        }
        else {
            GameFrame.getInstance().bulletList.add(new Bullet(
                    getX() + image.getWidth(null) / 2 - ImageMap.get("mb3").getWidth(null) / 2,
                    getY() - ImageMap.get("mb3").getHeight(null),
                    3
            ));
        }
    }


    /**
     * 边缘检测 判断飞机出了窗口的逻辑
     */
    public void borderTesting() {
        if (getX() < 0) {
            setX(0);
        }
        if (getX() + image.getWidth(null) > FrameConstant.FRAME_WIDTH) {
            setX(FrameConstant.FRAME_WIDTH - image.getWidth(null));
        }
        if (getY() < 44) {//因为上边的白条大约占44
            setY(44);
        }
        if (getY() + image.getHeight(null) > FrameConstant.FRAME_HEIGHT) {
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null));
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
}
