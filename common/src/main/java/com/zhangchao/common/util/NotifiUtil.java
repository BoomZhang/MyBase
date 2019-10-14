package com.zhangchao.common.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * 创建时间: 2019/08/08
 * 作者: zhangchao042@ke.com
 * 描述: 发送通知的Util类
 */
public final class NotifiUtil {

  private static Notifi notifi;

  private NotifiUtil(){

  }

  /**
   * 获得通知实例，设置Notification的各个属性
   */
  public static NotificationCompat.Builder getInstance(Context context){
    if(notifi == null){
      notifi = new Notifi(context);
    }
    return notifi.builder;
  }

  public NotificationManager getNotificationManager(){
    return notifi.notificationManager;
  }

  public static void sendNotification(int ID){
    if(notifi == null){
      return;
    }
    notifi.sendNotification(ID);
  }

  private static class Notifi{

    private static String CHANNEL_NAME = "channelid";
    private static String CHANNEL_ID = "channelname";
    private static int IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT;

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private Context context;

    public Notifi(Context context){
      this.context = context;
      notificationManager = (NotificationManager) this.context.getSystemService(Context.NOTIFICATION_SERVICE);
      builder = new NotificationCompat.Builder(this.context,createChannel(this.context));
    }

    public void sendNotification(int ID){
      Notification notification = builder.build();
      NotificationManagerCompat.from(this.context).notify(ID,notification);
    }

    /**
     * 在SDK版本在26及以上的时候，发送通知需要注册一个channelID
     * 在之前的版本不需要，直接返回null
     */
    private String createChannel(Context context){
      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
        NotificationChannel channel =
            new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,IMPORTANCE);
        channel.setShowBadge(false);
        notificationManager.createNotificationChannel(channel);
        return CHANNEL_ID;
      }else {
        return null;
      }
    }
  }


}
