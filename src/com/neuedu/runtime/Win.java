package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Win extends BaseSprite implements Drawable {
    private List<Image> imageList=new ArrayList<>();
    private int index=0;
    public Win() {
        this(FrameConstant.FRAME_WIDTH/2-ImageMap.get("win1").getWidth(null)/2,
                FrameConstant.FRAME_HEIGHT/2-ImageMap.get("win1").getHeight(null)/2);
    }

    public Win(int x, int y) {
        super(x, y);
        for (int i = 1; i <15 ; i++) {
            imageList.add(ImageMap.get("win"+i)) ;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imageList.get(index++),getX(),getY(),ImageMap.get("win1").getWidth(null)
                ,ImageMap.get("win1").getHeight(null),null);
        if (index>=14){
            index=0;
        }
    }
}
