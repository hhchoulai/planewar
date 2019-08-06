package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.ImageMap;

import java.awt.*;

/**
 * 警告  当离boss很近的时候出现警告  表示快要死了
 */
public class Warning extends BaseSprite implements  Drawable {
    private Image image;

    public Warning() {
        this(FrameConstant.FRAME_WIDTH/2-ImageMap.get("warning").getWidth(null)/2,FrameConstant.FRAME_HEIGHT/2-ImageMap.get("warning").getHeight(null)/2, ImageMap.get("warning"));
    }

    public Warning(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        if ((GameFrame.getInstance().plane.getY()-
        (GameFrame.getInstance().boss.getY()+ ImageMap.get("boss1").getWidth(null)))<=25){
            g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        }
    }


}
