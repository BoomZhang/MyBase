package com.zhangchao.mybase.lifecycletest;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

/**
 * 创建时间: 2019/10/17
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class BasePresenter implements IPresenter {
  @Override
  public void onCreate(LifecycleOwner owner) {

  }

  @Override
  public void onDestory(LifecycleOwner owner) {

  }

  @Override
  public void onLifeCycleChanged(LifecycleOwner owner, Lifecycle.Event event) {

  }
}
