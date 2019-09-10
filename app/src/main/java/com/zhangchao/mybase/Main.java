package com.zhangchao.mybase;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2019/08/19
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class Main {

  public static void main(String[] strings){
    Map<String,String> map = new HashMap<>();
    map.put("1","11111");
    map.put("2","22222");
    map.put("3","33333");
    Map<String,String> map2 = new HashMap<>(map);
    map2.put("4","44444");
    map2.put("1","kkkkk");
    System.out.println(map2.toString());
  }

}
