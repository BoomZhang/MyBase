package com.zhangchao.mybase;

import android.app.Application;
import android.content.Context;
import com.didichuxing.doraemonkit.DoraemonKit;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * 创建时间: 2019/10/09
 * 作者: zhangchao042@ke.com
 * 描述: 自定义Application
 */
public class MyApplication extends Application {

  public static RefWatcher getRefWatcher(Context context){
    return ((MyApplication)context.getApplicationContext()).mRefWatcher;
  }

  private RefWatcher mRefWatcher;

  @Override
  public void onCreate() {
    super.onCreate();
    //setupLeakCanary();
    DoraemonKit.install(this);
  }

  //设置监听
  protected void setupLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return;
    }
    mRefWatcher = LeakCanary.install(this);
  }
}
