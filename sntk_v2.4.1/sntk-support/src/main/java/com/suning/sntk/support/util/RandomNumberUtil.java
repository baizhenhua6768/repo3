package com.suning.sntk.support.util;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 
 * 随机数生成类
 * 
 * @author croyson
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RandomNumberUtil {

    private RandomNumberUtil() {

    }


    /**
     * 
     * 生成0-num之间的随机整数 <br>
     * 
     * @param num 序号
     * @return 随机下标
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static int getRandomNum(int num) {
        Random r = new Random();
        return r.nextInt(num);
    }

    /**
     * 从数组中随机获取一个元素
     * @param array 数组
     * @return 数组中随机元素
     */
    public static <E> E getRandomElement(E[] array){
        return array[getRandomNum(array.length)];
    }

    /**
     * 从list中随机取得一个元素
     * @param list 集合
     * @return 集合中的随机元素
     */
    public static <E> E getRandomElement(List<E> list){
        return list.get(getRandomNum(list.size()));
    }


    /**
     * 从set中随机取得一个元素
     * @param set 待取值的set集合
     * @return set结果集中随机一个元素
     */
    public static <E> E getRandomElement(Set<E> set){
        //1.获得随机序号
        int rn = getRandomNum(set.size());
        //2.遍历set 取得序号对应的值
        int i = 0;
        for (E e : set) {
            if(i== rn){
                return e;
            }
            i++;
        }
        return null;
    }


    /**
     * 从map中随机取得一个key
     * @param map map结果集
     * @return map中的随机key
     */
    public static <K, V> K getRandomKeyFromMap(Map<K, V> map) {
        int rn = getRandomNum(map.size());
        int i = 0;
        for (K key : map.keySet()) {
            if(i==rn){
                return key;
            }
            i++;
        }
        return null;
    }

    /**
     * 从map中随机取得一个value
     * @param map map结果集
     * @return map中的随机value
     */
    public static <K, V> V getRandomValueFromMap(Map<K, V> map) {
        int rn = getRandomNum(map.size());
        int i = 0;
        for (V value : map.values()) {
            if(i==rn){
                return value;
            }
            i++;
        }
        return null;
    }

}