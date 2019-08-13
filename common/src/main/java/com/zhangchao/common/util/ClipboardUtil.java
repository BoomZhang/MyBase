package com.zhangchao.common.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * 创建时间: 2019/08/12
 * 作者: zhangchao042@ke.com
 * 描述: 剪切板工具类的封装
 */
public class ClipboardUtil {

  public static ClipboardManager manager;

  public static void copyText(Context context, String lable, String text){
    if(manager == null){
      manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }
    ClipData data = ClipData.newPlainText(lable, text);
    manager.setPrimaryClip(data);
  }

  public static void copyData(Context context, ClipData data){
    if(manager == null){
      manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }
    manager.setPrimaryClip(data);
  }

}
