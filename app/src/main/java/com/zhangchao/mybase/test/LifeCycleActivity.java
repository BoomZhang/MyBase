package com.zhangchao.mybase.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.zhangchao.common.util.LogUtil;

/**
 * 创建时间: 2019/09/10
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class LifeCycleActivity extends AppCompatActivity {

  private String ClassName = "Error";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ClassName = getLocalClassName();
    LogUtil.i(ClassName + " -> onCreate()");
  }

  @Override
  protected void onStart() {
    super.onStart();
    LogUtil.i(ClassName + " -> onStart()");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    LogUtil.i(ClassName + " -> onRestart()");
  }

  @Override
  protected void onResume() {
    super.onResume();
    LogUtil.i(ClassName + " -> onResume()");
  }

  @Override
  protected void onPause() {
    super.onPause();
    LogUtil.i(ClassName + " -> onPause()");
  }

  @Override
  protected void onStop() {
    super.onStop();
    LogUtil.i(ClassName + " -> onStop()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    LogUtil.i(ClassName + " -> onDestroy()");
  }

}
