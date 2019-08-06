package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Game Over的图片类  动图
 */
public class End extends BaseSprite implements Drawable {
    private List<Image> imageList=new ArrayList<>();
    private int index=0;

    public End() {
        this(FrameConstant.FRAME_WIDTH/2-ImageMap.get("end1").getWidth(null)/2,
                FrameConstant.FRAME_HEIGHT/2-ImageMap.get("end1").getHeight(null));
    }

    public End(int x, int y) {
        super(x, y);
        for (int i = 1; i <15 ; i++) {
            imageList.add(ImageMap.get("end"+i));
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imageList.get(index++),getX(),getY(),imageList.get(1).getWidth(null),imageList.get(1).getHeight(null),null);
        if (index>=14){
            index=0;
        }
    }
}
