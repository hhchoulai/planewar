package com.neuedu.runtime;
/**
 * 第三种敌机
 */
import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.ImageMap;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends BaseSprite implements Drawable, Moveable {
    private List<Image> imageList=new ArrayList<>();
    private int index=0;//九张图轮换的起始量
    public int count=100;//boss的生命值是100
    private int speed= FrameConstant.GAME_SPEED*4;
    private boolean right=true;//控制boss朝右走的一个变量
    Random random=new Random();

    public Boss() {

    }
    public Boss(int x, int y) {
        super(x, y);
        for (int i = 1; i < 10; i++) {
            imageList.add(ImageMap.get("boss"+i));
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        fire();
        g.drawImage(imageList.get(index++),getX(),getY(),imageList.get(0).getWidth(null),
                imageList.get(1).getHeight(null),null);
        if (index>=9){
            index=0;
        }

    }
//  左右摇摆
    @Override
    public void move() {

        if (right){
            setX(getX()+speed);
        }else {
            setX(getX()-speed);
        }
        borderTesting();
    }
    /**
     * 边缘检测
     */
    public void borderTesting(){
        if (getX()+imageList.get(0).getWidth(null)>FrameConstant.FRAME_WIDTH){
            right=false;
        }else if (getX()<0){
            right=true;
        }
    }
    /**
     * 初始化敌军子弹
     */
    public void fire(){
        if (random.nextInt(1000)>980){ //  980/1000的概率
            GameFrame.getInstance().enemyBulletList.add(new EnemyBullet(
                getX()+imageList.get(0).getWidth(null)/2-ImageMap.get("eb2").getWidth(null)/2,
                    getY()+imageList.get(0).getHeight(null)/2-ImageMap.get("eb2").getHeight(null)/2,
                    ImageMap.get("eb2")
            ));
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),imageList.get(0).getWidth(null),imageList.get(0).getHeight(null));
    }

    /**
     * 碰撞  boss与我机碰撞
     * 如果Boss与我方飞机碰撞，那么hp=0,直接死亡
     */
    public void crash(Plane plane){
            if (plane.getRectangle().intersects(this.getRectangle())) {
                GameFrame.getInstance().hp=0;
                GameFrame.getInstance().gameOver=true;
            }
        }

}
