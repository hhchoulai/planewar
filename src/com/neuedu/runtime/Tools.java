package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.ImageMap;

import java.awt.*;

/**
 * 道具类
 */
public class Tools extends BaseSprite implements Moveable, Drawable {
    private int type;
    private Image image;
    private Image image2;
    private int speed= FrameConstant.GAME_SPEED*3;

    public Tools() {
    }

    public Tools(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image= ImageMap.get("addHp");
        this.image2=ImageMap.get("defend");
    }

    @Override
    public void draw(Graphics g) {
        if (type==1){
            g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        }if (type==2){
            g.drawImage(image2,getX(),getY(),image2.getWidth(null)*3,image2.getHeight(null)*3,null);
        }
        move();

    }

    @Override
    public void move() {
        if (type==1){
            setY(getY()+speed);
        }
        if (type==2){
            setX(getX()+speed);
            setY(getY()+speed);
        }
        borderTesting();
    }

    @Override
    public Rectangle getRectangle() {
        Rectangle rectangle=null;
        if (type == 1) {
            rectangle=new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
        }if (type==2){
           rectangle=new Rectangle(getX(),getY(),image2.getWidth(null),image2.getHeight(null));
        }
        return rectangle;
    }
    /**
     * 碰撞判断
     */
    public void crash(Plane plane){
        for (Tools tools : GameFrame.getInstance().toolsList) {
            if (tools.getRectangle().intersects(plane.getRectangle())&& tools.type==1){
                GameFrame.getInstance().toolsList.remove(this);
                GameFrame.getInstance().hp+=5;
            }
            if (tools.getRectangle().intersects(plane.getRectangle())&& tools.type==2){
                //把保护罩画在飞机上  随飞机移动  而且只停留10s  在这期间被任何子弹敌机打中都不减血
                GameFrame.getInstance().toolsList.remove(this);
                Plane.unmatched=true;//飞机无敌了
//                setX(plane.getX());
//                setY(plane.getY());
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10000);
                            Plane.unmatched=false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
    }
    /**
     * 边缘检测
     */
    public void borderTesting(){
        if (type==1){
            if (getY()+image.getHeight(null)>FrameConstant.FRAME_HEIGHT ||
                    getX()<0 || getX()+image.getWidth(null)>FrameConstant.FRAME_WIDTH){
                GameFrame.getInstance().toolsList.remove(this);
            }
        }if (type==2){
            if (getY()+image2.getHeight(null)>FrameConstant.FRAME_HEIGHT ||
                    getX()<0 || getX()+image2.getWidth(null)>FrameConstant.FRAME_WIDTH){
                GameFrame.getInstance().toolsList.remove(this);
            }
        }

    }
}
