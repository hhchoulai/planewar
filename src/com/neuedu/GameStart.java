package com.neuedu;

import com.neuedu.main.GameFrame;

import javax.xml.ws.Holder;

/**
 * 启动类  写main方法的  这个类和所有的包并列
 */
public class GameStart {
    public static void main(String[] args) {
//        new GameFrame().init();
        GameFrame.getInstance().init();
    }
}
