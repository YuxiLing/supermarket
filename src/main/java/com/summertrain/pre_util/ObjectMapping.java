package com.summertrain.pre_util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ObjectMapping {
	/**将map转换为实体类,在jpa查询部分字段时会用到
     * 使用的时候注意,因为int类型会初始化的问题,无法被FASTJSON忽略掉,所以返回的json可能会带有额外的数字0*/
    public static <T>List<T> mapToEntity(List<Map<String,Object>> mapList,Class<T> targetClass) throws InstantiationException, IllegalAccessException {
        List<T> targetList = new LinkedList<>();
        for (Map<String, Object> map : mapList) {
            targetList.add(mapToEntity(map,targetClass));
        }
        return targetList;
    }
    /**将map转换为实体类,在jpa查询部分字段时会用到
     * 使用的时候注意,因为int类型会初始化的问题,无法被FASTJSON忽略掉,所以返回的json可能会带有额外的数字0*/
    public static <T>T mapToEntity(Map<String,Object> map,Class<T> targetClass) throws IllegalAccessException, InstantiationException {
        Class superClass;
        Field[] fields;
        T target = targetClass.newInstance();
        //接收targetClass的Field
        List<Field> targetfieldList = new LinkedList<>();

        superClass = targetClass;
        while(superClass!=null&&superClass!=Object.class){
            fields = superClass.getDeclaredFields();
            targetfieldList.addAll(Arrays.asList(fields));
            superClass = superClass.getSuperclass();
        }
        //匹配并赋值
        for (Field targetfield : targetfieldList) {
            for (Map.Entry<String, Object> mapEntry : map.entrySet()) {
                if (targetfield.getName().equals(mapEntry.getKey())){
                    //暂时提取出权限
                    boolean targetFlag = targetfield.isAccessible();
                    //赋予权限
                    targetfield.setAccessible(true);
                    //赋值
                    targetfield.set(target,mapEntry.getValue());
                    //恢复原权限
                    targetfield.setAccessible(targetFlag);
                    break;
                }
            }
        }
        return target;
    }

}
