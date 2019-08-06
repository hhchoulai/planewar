package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;
import com.neuedu.util.ImageUtil;

import java.awt.*;

public class Background extends BaseSprite implements Drawable, Moveable {
    private Image image;
//无参的构造  初始化数据
    public Background() {
//        ImageMap.get("bg1")保证这个图片只读取一次  因为它是static final的
        this(0,
                FrameConstant.FRAME_HEIGHT-ImageMap.get("bg1").getHeight(null),
                ImageMap.get("bg1"));
    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
        setY(getY()+ FrameConstant.GAME_SPEED);

    }

    @Override
    public void draw(Graphics g) {
        move();
//        g是画笔
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

    }
}
