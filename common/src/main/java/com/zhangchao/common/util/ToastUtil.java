package com.zhangchao.common.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 创建时间: 2019/08/09
 * 作者: zhangchao042@ke.com
 * 描述: ToastUtil的工具类
 */
public final class ToastUtil {

  public static void showLong(Context context, String text){
    Toast.makeText(context,text,Toast.LENGTH_LONG).show();
  }

  public static void showShort(Context context, String text){
    Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
  }

}
