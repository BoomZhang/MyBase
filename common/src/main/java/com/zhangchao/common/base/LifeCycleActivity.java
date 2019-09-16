package com.zhangchao.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.zhangchao.common.util.LogUtil;

/**
 * 创建时间: 2019/09/10
 * 作者: zhangchao042@ke.com
 * 描述: 打印Activity的生命周期
 */
public class LifeCycleActivity extends AppCompatActivity {

  private String ClassName = "Error";
  protected boolean isPrintLifeCycle = false;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ClassName = getLocalClassName();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onCreate()");
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onStart()");
    }
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onRestart()");
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onResume()");
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onPause()");
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onStop()");
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if(isPrintLifeCycle){
      LogUtil.i(ClassName + " -> onDestroy()");
    }
  }

}
