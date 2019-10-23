package com.zhangchao.mybase.lifecycletest;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * 创建时间: 2019/10/17
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public interface IPresenter extends LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  void onCreate(LifecycleOwner owner);

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  void onDestory(LifecycleOwner owner);

  @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
  void onLifeCycleChanged(LifecycleOwner owner, Lifecycle.Event event);

}
