package com.zhangchao.mybase;

/**
 * 创建时间: 2019/08/19
 * 作者: zhangchao042@ke.com
 * 描述: 测试java代码的工具类
 */
public class Main {

  public static void main(String[] strings){
    int a = 3;
    boolean b = true;
    System.out.println(isOb(b));
  }

  public static boolean isOb(Object ob){
    return ob instanceof Integer;
  }

  private void print(Object a){

  }
}
