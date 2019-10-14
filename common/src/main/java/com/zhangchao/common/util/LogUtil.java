package com.zhangchao.common.util;

import android.util.Log;

/**
 * 创建时间: 2019/08/07
 * 作者: zhangchao042@ke.com
 * 描述:Log日志工具类
 */
public final class LogUtil {

  //默认使用的tag
  public static final String TAG = "ztc";

  private LogUtil(){

  }

  public static void i(String msg){
    Log.i(TAG,msg);
  }

  public static void d(String msg){
    Log.d(TAG,msg);
  }

  public static void w(String msg){
    Log.w(TAG,msg);
  }

  public static void e(String msg){
    Log.e(TAG,msg);
  }

}
