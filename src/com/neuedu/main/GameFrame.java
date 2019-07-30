package com.neuedu.main;
/**
 * 用于绘制窗口
 */

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.Background;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
    private Background bg=new Background();
    @Override
    public void paint(Graphics g) {
        bg.draw(g);
    }
    /**
     * 初始化窗口
     */
    public void init(){
//        setSize(200,200); 因为这些值不要写死 所以这时候出现了常量类constant  就出现了如下代码
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);
        setLocationRelativeTo(null);//设置居中
        enableInputMethods(false);//静止使用输入法
        addWindowListener(new WindowAdapter() {
            @Override
            //windowClosing是点×关闭
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);//  显示窗口  可见性为true  这行代码必须放最后
//      匿名内部类  要用线程实时刷新
        new Thread(){
            @Override
            public void run() {
                while (true){
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

    }
//    解决闪屏问题的代码
    private Image offScreenImage=null;//创建缓冲区
    public void update(Graphics g){
        if (offScreenImage==null){
            offScreenImage=this.createImage(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

        }
        Graphics gOff=offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }
}
