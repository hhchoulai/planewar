package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.ImageMap;

import java.util.List;

import java.awt.*;

public class Bullet extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private Image image2;
    private Image image3;
    private int speed= FrameConstant.GAME_SPEED*6;
    private int grade=1;// grade代表子弹的等级  1是一颗子弹  2是俩颗子弹
    public Bullet() {
/*      这里可以不给子弹初始化  因为子弹的位置不确定 不像飞机，背景，它就在那  子弹需要动态获取
        实际上子弹的初始化是在Plane类里fire方法定义的*/
//        this(0,0, ImageMap.get("mb1"));
    }

    public Bullet(int x, int y, int grade) {
        super(x, y);
        this.grade = grade;
        this.image=ImageMap.get("mb1");
        this.image2=ImageMap.get("mb2");
        this.image3=ImageMap.get("mb3");
    }

    @Override
    public void draw(Graphics g) {
        move();
        if (grade==1){
            g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        }else if(grade==2){
            g.drawImage(image2,getX(),getY(),image2.getWidth(null),image2.getHeight(null),null);
        }else if(grade==3){
            g.drawImage(image3,getX(),getY(),image3.getWidth(null),image3.getHeight(null),null);
        }

    }

    @Override
    public void move() {
//        我方发出的子弹一直向上移动
        setY(getY()-speed);
        borderTesting();
    }
    /**
     * 边缘检测 判断子弹出了窗口的逻辑
     * 如果出了窗口  把它从集合中remove
     */
    public void borderTesting(){
        if (getY()<44){
            GameFrame.getInstance().bulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        Rectangle rectangle=null;
        if (grade==1){
           rectangle=new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
        }
        if (grade==2){
            rectangle=new Rectangle(getX(),getY(),image2.getWidth(null),image2.getHeight(null));
        }
        if (grade==3){
            rectangle=new Rectangle(getX(),getY(),image3.getWidth(null),image3.getHeight(null));
        }
        return rectangle;
    }

    /**
     * 碰撞  矩形检测  我方子弹与敌机1碰撞
     * @param enemyPlaneList
     */
//    先导入List包  再导入awt包
    public void collisionTesting(List<EnemyPlane> enemyPlaneList){
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())){
                enemyPlaneList.remove(enemyPlane);
                GameFrame.getInstance().bulletList.remove(this);
                GameFrame.getInstance().score++;
            }
        }
    }

    /**
     *  我方子弹与敌机2的判断
     * @param enemyPlane2List
     */
    public void crashTesting(List<EnemyPlane2> enemyPlane2List){
        for (EnemyPlane2 enemyPlane2 : enemyPlane2List) {
            if (enemyPlane2.getRectangle().intersects(this.getRectangle())){
                enemyPlane2List.remove(enemyPlane2);
                GameFrame.getInstance().bulletList.remove(this);
                GameFrame.getInstance().score+=2;
            }
        }

    }
    /**
     *  我方子弹与boss碰撞   子弹打飞机
     */
    public void hitPlane(Boss boss){
        for (Bullet bullet : GameFrame.getInstance().bulletList) {
            if (bullet.getRectangle().intersects(boss.getRectangle())){
                GameFrame.getInstance().bulletList.remove(bullet);
                boss.count-=5;
               if (boss.count<=0){
                   GameFrame.getInstance().gameOver=true;

               }
            }
        }
    }

}
