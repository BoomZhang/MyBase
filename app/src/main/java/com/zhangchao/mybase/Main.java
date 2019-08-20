package com.zhangchao.mybase;

/**
 * 创建时间: 2019/08/19
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class Main {

  public static void main(String[] strings){
    Son a = new Son();
    a.doing();
  }

  public static class Father{

    public int num = 1;

    public void doing(){
      System.out.println(num);
      print();
    }

    private void print(){
      System.out.println("Father");
    }

  }

  public static class Son extends Father{

    public int num = 2;

    @Override
    public void doing() {
      //super.doing();
      //super.num = 34;
      System.out.println(num);
    }

    private void print(){
      System.out.println("Son");
    }

  }

}
