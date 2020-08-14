package com.czm.managed_system.supper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
public class TransformUtil {
    /**
     *     创建SimpleDateFormat对象，指定样式    2019-05-13 22:39:30
     */
    private static SimpleDateFormat format;
    private static Map<Integer, String> stageMap;
    private static Map<String, Integer> typeMap;
    static{
        format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stageMap = new HashMap<>();
        stageMap.put(-1,"维修中");
        stageMap.put(0,"空闲");
        stageMap.put(1,"租用中");
        typeMap = new HashMap<>();
        typeMap.put("全部",0);
        typeMap.put("单人间",1);
        typeMap.put("双人间",2);
        typeMap.put("三人间",3);
        typeMap.put("四人间",4);

    }

    public static String formart(Date date){
        return format.format(date);
    }


    public static String stageSting(int value) {
        return stageMap.get(value);
    }

    public static int stageInt(String stage) {
        if ("维修中".equals(stage)){
            return -1;
        }else if("租用中".equals(stage)) {
            return 1;
        }else {
            return 0;
        }
    }

    public static byte genderByString(String gender){
        if ("男".equals(gender)) {
            return 1;
        }else {
            return 0;
        }
    }

    public static int getTypeByString(String type) {
        return typeMap.get(type);
    }

}
