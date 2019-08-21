package com.zhangchao.mybase;

import java.util.Arrays;
import java.util.List;

/**
 * 创建时间: 2019/08/19
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class Main {

  public static void main(String[] strings){
    Param param101 = new Param();
    param101.setId("101");
    param101.setParamKey("年级");
    param101.setParamValue("小学,中学,高中,大学");

    Param param102 = new Param();
    param102.setId("102");
    param102.setParamKey("Four Emperors");
    param102.setParamValue("Edward Newgate,Shanks,Kaido,BIG MOM");

    List<String> values = Arrays.asList(param102.getParamValue().split(","));
    values.remove("Edward Newgate"); // 移除 "爱德华纽盖特", 报错

    // 后续操作：添加 "黑胡子"...
    String blackBeard = "Marshall·D·Teach";
    values.add(blackBeard); //　即将报错
    //String newValue = StringUtils.join(values.toArray(new String[values.size()]), ",");
    //param102.setParamValue(newValue);

    System.out.println(param102.getParamValue().toString());
  }


  public static class Param {
    private String id;
    private String paramKey;
    private String paramValue;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getParamKey() {
      return paramKey;
    }

    public void setParamKey(String paramKey) {
      this.paramKey = paramKey;
    }

    public String getParamValue() {
      return paramValue;
    }

    public void setParamValue(String paramValue) {
      this.paramValue = paramValue;
    }
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
