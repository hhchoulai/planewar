package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.ImageMap;

import java.awt.*;

/**
 * 敌方子弹
 */
public class EnemyBullet extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 7;

    //  如果敌方的子弹速度与背景速度一样  那么看上去就产生了一个  实际上是在同一个位置产生了多个 是重叠了
    public EnemyBullet() {
    }

    public EnemyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);

    }

    @Override
    public void move() {
        setY(getY() + speed);
        borderTesting();
    }

    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame.getInstance().enemyBulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    /**
     * 矩形碰撞  敌方子弹与我方飞机的碰撞
     */
    public void collisionTesting(Plane plane) {
        if (Plane.unmatched){  //飞机无敌了 子弹移除  但血量不变
            if (plane.getRectangle().intersects(this.getRectangle())){
                GameFrame.getInstance().enemyBulletList.remove(this);
            }
        }else { //飞机正常情况下的碰撞
            if (plane.getRectangle().intersects(this.getRectangle())) {
                GameFrame.getInstance().enemyBulletList.remove(this);
                GameFrame.getInstance().hp--;
                if (GameFrame.getInstance().hp <= 0) {
                    GameFrame.getInstance().gameOver = true;
                }
            }
        }

    }
}
