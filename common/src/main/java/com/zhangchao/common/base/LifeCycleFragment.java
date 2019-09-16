package com.zhangchao.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zhangchao.common.util.LogUtil;

/**
 * 创建时间: 2019/09/16
 * 作者: zhangchao042@ke.com
 * 描述: Fragment生命周期的基础类
 */
public class LifeCycleFragment extends Fragment {

  private String ClassName = "Error";
  protected boolean isPrintLifeCycle = false;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    ClassName = this.getClass().getName();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onAttach()");
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onCreate()");
    }
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onCreateView()");
    }
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onActivityCreated()");
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onStart()");
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onResume()");
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onPause()");
    }
  }

  @Override
  public void onStop() {
    super.onStop();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onStop()");
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onDestroy()");
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onDestroyView()");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onDetach()");
    }
  }
}
