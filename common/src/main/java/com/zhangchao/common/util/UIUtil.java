package com.zhangchao.common.util;

import android.view.View;

/**
 * 创建时间: 2019/09/10
 * 作者: zhangchao042@ke.com
 * 描述: 处理UI的工具类
 */
public class UIUtil {

  /**
   * 打印View的位置信息
   */
  public static void printViewValue(View view){
    LogUtil.i("Top = " + view.getTop());
    LogUtil.i("Bottom = " + view.getBottom());
    LogUtil.i("Left = " + view.getLeft());
    LogUtil.i("Right = " + view.getRight());
    LogUtil.i("Width = " + view.getWidth());
    LogUtil.i("Height = " + view.getHeight());
    LogUtil.i("X = " + view.getX());
    LogUtil.i("Y = " + view.getY());
    LogUtil.i("TranslationX = " + view.getTranslationX());
    LogUtil.i("TranslationY = " + view.getTranslationY());
  }

}
