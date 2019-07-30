package com.neuedu.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * 读取图片够工具类
 */
public class ImageUtil {
    public static Image getImage(String path){
        Image image=null;
        try {
//            规定就这么写的  只需要最终把路径复制对就可以
            image=ImageIO.read(ImageUtil.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
