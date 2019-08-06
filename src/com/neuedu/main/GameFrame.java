package com.neuedu.main;
/**
 * 用于绘制窗口
 */
import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {
    /**
     * 单例模式  保证就这一个窗口，往这一个窗口上画东西
     */
    private static class Holder {// 静态内部类
        private static final GameFrame gameFrame = new GameFrame();
    }
    private GameFrame() {
    }
    public static GameFrame getInstance(){
        return Holder.gameFrame;
    }
    /**
     * 属性
     */
    public boolean gameOver=true;//  判断游戏是否结束 true是结束 false是没结束
    public int score=0;//记录分数
    public int hp=10;//初始血量
    public int lv=1;//等级
    /**
     * 创建对象或集合
     */
    private Background bg = new Background();//    创建背景对象
    public final Plane plane = new Plane(); //    创建飞机对象
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();//    创建子弹集合
    /*因为Plane类要访问  所以把private改为了public final，就相当于private修饰，但提供了get方法
     这样保证了只能往里添加元素  而不能创建一个新的集合*/
    public final List<EnemyPlane> enemyPlaneList=new CopyOnWriteArrayList<>();//创建敌军飞机集合
    public final List<EnemyPlane2> enemyPlane2List=new CopyOnWriteArrayList<>();//创建敌军飞机2集合
    public final List<EnemyBullet> enemyBulletList=new CopyOnWriteArrayList<>();//创建敌军子弹集合
    public final List<Tools> toolsList=new CopyOnWriteArrayList<>();//创建工具类集合
    public final Boss boss=new Boss(100,160);//创建boss  敌军飞机3
    private Warning warning=new Warning();// 创建警告
    public Start start=new Start();//创建开始图标对象
    private End end=new End();
    private Win win=new Win();
    @Override
    // paint方法不需要调用  只要重写就行  在类库中有一个component
    /*组件第一次显示在屏幕上时，系统会自动触发绘图代码。这里面采用的是回调机制，
    自动调用paint函数。不由自己调用。*/
    public void paint(Graphics g) {
        bg.draw(g);
        plane.draw(g);
        if (start != null) {
            start.draw(g);
        }
        if (!gameOver){

//            当出现boss的时候，才有下边的行为
            if (score > 30) {
                boss.draw(g);//当分数>30的时候，才开始画boss
                boss.crash(plane);
                for (Bullet bullet : bulletList) {
                    bullet.hitPlane(boss);
                }
                if (!gameOver){
                    warning.draw(g);//boss出来之后  再去画warning
                }
                /*矩形条表示生命值 */
                g.setFont(new Font("楷体", Font.BOLD, 20));
                g.setColor(Color.MAGENTA);
                g.drawRect(650, 110, 80 , 15);
                g.fillRect(650, 110, boss.count , 15);
                g.drawString("boss生命值:" + boss.count, 600, 150);
                /*矩形条表示生命值  end*/
            }
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
                bullet.collisionTesting(enemyPlaneList);
                bullet.crashTesting(enemyPlane2List);
            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
                enemyPlane.Crash(plane);

            }
            for (EnemyPlane2 enemyPlane2 : enemyPlane2List) {
                enemyPlane2.draw(g);
                enemyPlane2.Crash(plane);
            }
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
                enemyBullet.collisionTesting(plane);
            }
            for (Tools tools : toolsList) {
                tools.draw(g);
                tools.crash(plane);
            }

        }
//      显示在窗口上的字drawString方法来写
        g.setColor(Color.BLUE);
        g.setFont(new Font("微软雅黑",Font.BOLD,22));
        g.drawString("得分："+score,150,100);
        g.drawString("血量："+hp,550,100);
        if (score>=15){
           lv=2;
        }
        if (score>=30){
            lv=3;
        }
        g.drawString("等级：LV"+lv,350,100);


        //boss的生命值是0  通关成功
        if (boss.count==0){
//            g.setColor(Color.red);
//            g.setFont(new Font("微软雅黑",Font.BOLD,35));
//            g.drawString("通关成功！成绩："+score+"分",180,400);
            win.draw(g);
        }
        //血量为0  游戏结束
        if (hp==0){
//            g.setColor(Color.red);
//            g.setFont(new Font("微软雅黑",Font.BOLD,35));
//            g.drawString("游戏结束",350,400);
            end.draw(g);
        }
    }

    /**
     * 初始化窗口
     */
    public void init() {
//      setSize(200,200); 因为这些值不要写死 所以这时候出现了常量类constant  就出现了如下代码
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        setLocationRelativeTo(null);//设置居中
        enableInputMethods(false);//静止使用输入法
        setResizable(false);//不允许改变窗口
//       windowClosing是点×关闭
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
//        按WSAD代表上下左右键  触发事件
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (start != null) {
                    start.mouseClicked(e);
                }
            }
        });
        setVisible(true);//  显示窗口  可见性为true  这行代码必须放最后
//      匿名内部类  要用线程实时刷新
//      实际上就开了俩个线程   主线程在那组织自己的逻辑一步一步往下执行
//      新开辟的线程在后台重复刷新  不影响主线程的运行
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
        Random random = new Random();
//        初始化添加一些敌方飞机   目前写死
        /*enemyPlaneList.add(new EnemyPlane(300,0, ImageMap.get("ep1")));
        enemyPlaneList.add(new EnemyPlane(200,0, ImageMap.get("ep1")));
        enemyPlaneList.add(new EnemyPlane(100,0, ImageMap.get("ep1")));
        enemyPlaneList.add(new EnemyPlane(50,0, ImageMap.get("ep1")));*/

        /*enemyPlane2List.add(new EnemyPlane2(400,-700,ImageMap.get("ep2")));
        enemyPlane2List.add(new EnemyPlane2(200,-200,ImageMap.get("ep2")));
        enemyPlane2List.add(new EnemyPlane2(300,-500,ImageMap.get("ep2")));
        enemyPlane2List.add(new EnemyPlane2(700,-300,ImageMap.get("ep2")));*/

    }

    /**
     * 解决闪屏问题的代码
     */
    private Image offScreenImage = null;//创建缓冲区

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);

        }
        Graphics gOff = offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
