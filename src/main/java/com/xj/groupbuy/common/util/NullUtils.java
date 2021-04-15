package com.xj.groupbuy.common.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
/**
 * Author : zhangxiaojian
 * Date : 2021/3/31
 * 判断是否为空的工具栏，如果不使用StringUtils的jdk的工具类，可以自行封装
 */

public class NullUtils {

    /**
     * 判断字符串不为空
     */
    public static boolean notEmpty(String str){
        //StringUtils.isNotEmpty(str);
        return str != null && !"".equals(str);
    }

    /**
     * 判断字符串不为空
     * jdk StringUtils工具类实现如下所示
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * 判断字符串为空
     */
    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    /**
     * 集合判断是否为空
     * @param collection 使用泛型
     */
    public static <T> boolean notEmpty(Collection<T> collection){
        if(collection != null || collection.size() != 0){
            Iterator<T> iterator = collection.iterator();
            if(iterator != null){
                while(iterator.hasNext()){
                    Object next = iterator.next();
                    if(next != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * map集合不为空的判断
     * @param map 使用泛型，可以传递不同的类型参数
     */
    public static <T> boolean notEmpty(Map<T, T> map){
        return map != null && !map.isEmpty();
    }

    /**
     * byte类型数组判断不为空
     */
    public static boolean notEmpty(byte[] t){
        return t != null && t.length > 0;
    }

    /**
     * short类型数组不为空判断
     */
    public static boolean notEmpty(short[] t){
        return t != null && t.length > 0;
    }

    /**
     * 数组判断不为空,没有泛型数组,所以还是分开写吧
     * @param t 可以是int,short,byte,String,Object,long
     */
    public static boolean notEmpty(int[] t){
        return t != null && t.length > 0;
    }

    /**
     * long类型数组不为空
     */
    public static boolean notEmpty(long[] t){
        return t != null && t.length > 0;
    }

    /**
     * String类型的数组不为空
     */
    public static boolean notEmpty(String[] t){
        return t != null && t.length > 0;
    }

    /**
     * Object类型数组不为空
     */
    public static boolean notEmpty(Object[] t){
        return t != null && t.length > 0;
    }

    /**
     *
     */
    public static boolean notEmpty(Object o){
        return o != null && !"".equals(o) && !"null".equals(o);
    }

    public static boolean isEmpty(Object o){
        return o == null || "".equals(o) || "null".equals(o);
    }
    
    public static void main(String[] args) {
        //String str = "";
        //1、判断字符串是否为空notEmpty()方法
        /*if(ObjectUtils.notEmpty(str)){
            System.out.println("字符串 " + str + " 不为空......");
        }else{
            System.out.println("字符串 " + str + "为空......");
        }*/

        //2、判断字符串是否为空isNotEmpty()方法
        /*if(ObjectUtils.isNotEmpty(str)){
            System.out.println("字符串 " + str + " 不为空......");
        }else{
            System.out.println("字符串 " + str + "为空......");
        }*/

        //3、集合判断是否为空,list和set实现Collection
        /*List<String> list = new ArrayList<String>();
        //list.add("hello");
        if(ObjectUtils.notEmpty(list)){
            System.out.println("List集合不为空");
        }else{
            System.out.println("List集合为空");
        }*/

        /*Set<String> set = new HashSet<String>();
        set.add("hello");
        if(ObjectUtils.notEmpty(set)){
            System.out.println("set集合不为空");
        }else{
            System.out.println("set集合为空");
        }*/

        //4、map集合接口,需要写单独的判读是否为空的方法
        /*Map<String, String> map = new HashMap<String, String>();
        //map.put("hello", "hello world");
        if(ObjectUtils.notEmpty(map)){
            System.out.println("map集合不为空");
        }else{
            System.out.println("map集合为空");
        }*/

        //5、数组判断不为空
        /*int[] a = new int[]{1,2,3,4,5};
        if(ObjectUtils.notEmpty(a)){
            System.out.println("int类型数组不为空");
        }else{
            System.out.println("int类型数组为空");
        }*/

        /*byte[] b = new byte[]{1,2,3,4,5};
        if(ObjectUtils.notEmpty(b)){
            System.out.println("byte类型数组不为空");
        }else{
            System.out.println("byte类型数组为空");
        }

        short[] c = new short[]{1,2,3,4,5};
        if(ObjectUtils.notEmpty(c)){
            System.out.println("short类型数组不为空");
        }else{
            System.out.println("short类型数组为空");
        }


        long[] d = new long[]{1,2,3,4,5};
        if(ObjectUtils.notEmpty(d)){
            System.out.println("long类型数组不为空");
        }else{
            System.out.println("long类型数组为空");
        }


        String[] e = new String[]{"hello","world","lisi","zhangsan"};
        if(ObjectUtils.notEmpty(e)){
            System.out.println("String类型数组不为空");
        }else{
            System.out.println("String类型数组为空");
        }

        Object[] a = new Object[]{1,2,3,4,5};
        if(ObjectUtils.notEmpty(a)){
            System.out.println("Object类型数组不为空");
        }else{
            System.out.println("Object类型数组为空");
        }*/


    }

}