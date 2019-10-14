package com.zhangchao.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 创建时间: 2019/08/07
 * 作者: zhangchao042@ke.com
 * 描述: 日期操作相关的工具类
 */
public final class DateUtil {

  private static final String[] DayOfWeek = new String[]{
      "星期日","星期一","星期二","星期三","星期四","星期五","星期六"
  };

  private DateUtil(){ }

  /**
   * 获取当前日期时间
   * @param format 格式
   * @return String类型日期时间
   */
  public static String getCurrentDate(String format){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    Date date = new Date(System.currentTimeMillis());
    return simpleDateFormat.format(date);
  }

  /**
   * 获取当前星期几
   */
  public static String getDayOfWeek(){
    return DayOfWeek[Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1];
  }

}
