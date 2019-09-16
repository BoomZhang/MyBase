package com.zhangchao.common.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * 创建时间: 2019/09/10
 * 作者: zhangchao042@ke.com
 * 描述: 处理UI的工具类
 */
public class UIUtil {

  /**
   * 打印View的位置信息
   * 注：需要在View绘制完成之后才能有具体值，否则默认为0
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

  /**
   * 打印屏幕信息
   */
  public static void printDisplayMetrics(Activity activity){
    DisplayMetrics displayMetrics = new DisplayMetrics();
    //将信息保存到displayMetrics中.
    activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    //1.x轴和y轴的dpi.
    LogUtil.i("ydpi=" + displayMetrics.ydpi);
    LogUtil.i("xdpi=" + displayMetrics.xdpi);
    //2.x轴和y轴的像素个数.
    LogUtil.i("heightPixels=" + displayMetrics.heightPixels);
    LogUtil.i("widthPixels=" + displayMetrics.widthPixels);
    //3.dpi
    LogUtil.i("densityDpi=" + displayMetrics.densityDpi);
    //4.dpi/160.
    LogUtil.i("density=" + displayMetrics.density);
    //5.通常情况下和density相同.
    LogUtil.i("scaledDensity=" + displayMetrics.scaledDensity);
  }

}
