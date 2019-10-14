package com.zhangchao.common.store;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

/**
 * 创建时间: 2019/10/10
 * 作者: zhangchao042@ke.com
 * 描述: SharePreference工具类封装
 */
public final class SPUtil {

  private static final String SP_FILE = "sp_file_defalut";

  public static String getDefalutSpFileName(){
    return SP_FILE;
  }

  public static void put(Context context, String key, Object value){
    put(context,key,value,null);
  }

  public static void put(Context context, String key, Object value, String fileName){
    SharedPreferences sharedPreferences;
    if(fileName == null || "".equals(fileName) || SP_FILE.equals(fileName)){
      sharedPreferences = context.getSharedPreferences(SP_FILE,Context.MODE_PRIVATE);
    }else{
      sharedPreferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
    }
    SharedPreferences.Editor editor = sharedPreferences.edit();
    if (value instanceof Integer){
      editor.putInt(key,(int)value);
    }else if(value instanceof String){
      editor.putString(key,(String)value);
    }else if(value instanceof Boolean){
      editor.putBoolean(key,(Boolean)value);
    }else if(value instanceof Float){
      editor.putFloat(key,(Float)value);
    }else if(value instanceof Long){
      editor.putLong(key,(Long)value);
    }
    editor.commit();
  }

  public static Object get(Context context, String key) {
    return get(context,key,null);
  }

  public static Object get(Context context, String key, String fileName){
    SharedPreferences sharedPreferences;
    if(fileName == null || "".equals(fileName) || SP_FILE.equals(fileName)){
      sharedPreferences = context.getSharedPreferences(SP_FILE,Context.MODE_PRIVATE);
    }else{
      sharedPreferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
    }
    Map<String,?> map = sharedPreferences.getAll();
    return map.get(key);
  }

}
