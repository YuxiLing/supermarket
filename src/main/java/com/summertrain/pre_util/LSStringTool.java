package com.summertrain.pre_util;

public class LSStringTool {
    /**判断是否为空*/
    public static boolean isEmpty(String str){
        return !((null!=str)&&(!"".equals(str)));
    }
    /**判断是否为纯正整数字符串*/
    public static boolean isPosZ(String str){
        if (isEmpty(str)) return false;

        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
            if(chr<48 || chr>57)
                return false;
        }
        return true;
    }
}
