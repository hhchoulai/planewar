package com.neuedu.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map=new HashMap<>();
    public static Image get(String key){
        return map.get(key);
    }

    /**
     * 在类加载的时候就把所有图片全取出来了   用到了直接从这里拿  而不用每次用到new  多次读取图片
     */
    static {
//        bg1背景  mp1我方飞机  mb1我方子弹  ep1代表敌方飞机  eb1敌方子弹
        map.put("bg1",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg1.png"));
        map.put("mp1",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\mp_1.png"));
        map.put("mb1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\mb_1.png"));
        map.put("ep1",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_1.png"));
        map.put("eb1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\eb_1.png"));
        map.put("eb2",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\eb_2.png"));
        map.put("ep2",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_2.png"));
        map.put("mp2",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\mb_2.png"));
        map.put("mb2",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\mb_2.png"));
        map.put("mb3",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\mb_3.png"));

        map.put("boss1",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_01.png"));
        map.put("boss2",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_02.png"));
        map.put("boss3",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_03.png"));
        map.put("boss4",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_04.png"));
        map.put("boss5",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_05.png"));
        map.put("boss6",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_06.png"));
        map.put("boss7",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_07.png"));
        map.put("boss8",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_08.png"));
        map.put("boss9",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_09.png"));

        map.put("defend",ImageUtil.getImage("com\\neuedu\\imgs\\tools\\adddefense1.png"));
        map.put("addHp",ImageUtil.getImage("com\\neuedu\\imgs\\tools\\addHP1.png"));
        map.put("warning",ImageUtil.getImage("com\\neuedu\\imgs\\tools\\warning.png"));
        map.put("start",ImageUtil.getImage("com\\neuedu\\imgs\\tools\\start.png"));

        map.put("end1",ImageUtil.getImage("com\\neuedu\\imgs\\end\\0.png"));
        map.put("end2",ImageUtil.getImage("com\\neuedu\\imgs\\end\\120.png"));
        map.put("end3",ImageUtil.getImage("com\\neuedu\\imgs\\end\\210.png"));
        map.put("end4",ImageUtil.getImage("com\\neuedu\\imgs\\end\\300.png"));
        map.put("end5",ImageUtil.getImage("com\\neuedu\\imgs\\end\\420.png"));
        map.put("end6",ImageUtil.getImage("com\\neuedu\\imgs\\end\\510.png"));
        map.put("end7",ImageUtil.getImage("com\\neuedu\\imgs\\end\\600.png"));
        map.put("end8",ImageUtil.getImage("com\\neuedu\\imgs\\end\\720.png"));
        map.put("end9",ImageUtil.getImage("com\\neuedu\\imgs\\end\\810.png"));
        map.put("end10",ImageUtil.getImage("com\\neuedu\\imgs\\end\\900.png"));
        map.put("end11",ImageUtil.getImage("com\\neuedu\\imgs\\end\\1020.png"));
        map.put("end12",ImageUtil.getImage("com\\neuedu\\imgs\\end\\1110.png"));
        map.put("end13",ImageUtil.getImage("com\\neuedu\\imgs\\end\\1200.png"));
        map.put("end14",ImageUtil.getImage("com\\neuedu\\imgs\\end\\1320.png"));

        map.put("win1",ImageUtil.getImage("com\\neuedu\\imgs\\win\\0.png"));
        map.put("win2",ImageUtil.getImage("com\\neuedu\\imgs\\win\\120.png"));
        map.put("win3",ImageUtil.getImage("com\\neuedu\\imgs\\win\\210.png"));
        map.put("win4",ImageUtil.getImage("com\\neuedu\\imgs\\win\\300.png"));
        map.put("win5",ImageUtil.getImage("com\\neuedu\\imgs\\win\\420.png"));
        map.put("win6",ImageUtil.getImage("com\\neuedu\\imgs\\win\\510.png"));
        map.put("win7",ImageUtil.getImage("com\\neuedu\\imgs\\win\\600.png"));
        map.put("win8",ImageUtil.getImage("com\\neuedu\\imgs\\win\\720.png"));
        map.put("win9",ImageUtil.getImage("com\\neuedu\\imgs\\win\\810.png"));
        map.put("win10",ImageUtil.getImage("com\\neuedu\\imgs\\win\\900.png"));
        map.put("win11",ImageUtil.getImage("com\\neuedu\\imgs\\win\\1020.png"));
        map.put("win12",ImageUtil.getImage("com\\neuedu\\imgs\\win\\1110.png"));
        map.put("win13",ImageUtil.getImage("com\\neuedu\\imgs\\win\\1200.png"));
        map.put("win14",ImageUtil.getImage("com\\neuedu\\imgs\\win\\1320.png"));


    }

}
