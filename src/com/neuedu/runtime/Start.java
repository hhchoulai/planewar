package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * 开始图标
 */
public class Start extends BaseSprite implements Drawable {
    private Image image;

    public Start() {
        this(FrameConstant.FRAME_WIDTH/2-ImageMap.get("start").getWidth(null)/2,FrameConstant.FRAME_HEIGHT/2-ImageMap.get("start").getHeight(null)/2, ImageMap.get("start"));
    }

    public Start(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
    }
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > getX() && e.getX() < getX() + image.getWidth(null) && e.getY() > getY() && e.getY() < getY() + image.getHeight(null)) {
            GameFrame.getInstance().gameOver = false;
            GameFrame.getInstance().start = null;
        }
    }
}
