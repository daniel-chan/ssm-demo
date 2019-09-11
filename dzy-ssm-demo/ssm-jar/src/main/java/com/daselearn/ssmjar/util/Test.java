package com.daselearn.ssmjar.util;

import com.daselearn.common.util.XmlUtils;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.joining;

/**
 * 类的详细说明
 *
 * @author daniel
 * @date 2019/8/26
 */
public class Test {

    public static void main(String[] args) {

        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            if("1".equals(temp)){
                a.remove(temp);
            }
        }

//        for (String temp : a) {
//            if("2".equals(temp)){
//                a.remove(temp);
//            }
//        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 100000; i++)
            map.put(i, i);
        /** 增强for循环，keySet迭代 */
        long start = System.currentTimeMillis();
        for (Integer key : map.keySet()) {
            map.get(key);
        }
        long end = System.currentTimeMillis();
        System.out.println("增强for循环，keySet迭代 -> " + (end - start) + " ms");

        /** 增强for循环，entrySet迭代 */
        start = System.currentTimeMillis();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();
        System.out.println("增强for循环，entrySet迭代 -> " + (end - start) + " ms");

        /** 迭代器，keySet迭代 */
        start = System.currentTimeMillis();
        Iterator<Integer> iterator = map.keySet().iterator();
        Integer key;
        while (iterator.hasNext()) {
            key = iterator.next();
            map.get(key);
        }
        end = System.currentTimeMillis();
        System.out.println("迭代器，keySet迭代 -> " + (end - start) + " ms");

        /** 迭代器，entrySet迭代 */
        start = System.currentTimeMillis();
        Iterator<Map.Entry<Integer, Integer>> iterator1 = map.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        while (iterator1.hasNext()) {
            entry = iterator1.next();
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();
        System.out.println("迭代器，entrySet迭代 -> " + (end - start) + " ms");
        System.out.println("迭代器的遍历速度要比增强for循环快很多，是增强for循环的2倍左右");
        System.out.println("使用entrySet遍历的速度要比keySet快很多，是keySet的1.5倍左右");


//        SortedMap<String, String> map2 = new TreeMap<>();
//        map2.put("name", "weixiaohuai");
//        map2.put("sex", "male");
//        map2.put("age", "20");
//        Set<String> keySet = map2.keySet();
//        for (String k : keySet) {
//            Object value = map2.get(k);
//            System.out.println(k + ", " + value);
//        }


    }

}
