package com.zhangchao.mybase.lifecycletest;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.util.Log;

/**
 * 创建时间: 2019/10/17
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class MainPresenter extends BasePresenter {

  private final String TAG = "ztc";

  private Context context;

  public MainPresenter(Context context) {
    this.context = context;
  }

  @Override
  public void onCreate(LifecycleOwner owner) {
    super.onCreate(owner);
    Log.d(TAG, "LifeCycle------onCreate------");
  }

  @Override
  public void onDestory(LifecycleOwner owner) {
    super.onDestory(owner);
    Log.d(TAG, "LifeCycle------onDestory------");
  }

}
