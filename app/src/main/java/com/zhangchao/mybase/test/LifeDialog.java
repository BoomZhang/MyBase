package com.zhangchao.mybase.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import com.zhangchao.common.util.LogUtil;

/**
 * 创建时间: 2019/09/10
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class LifeDialog extends AlertDialog {

  private final String ClassName = this.getClass().getName();

  public LifeDialog(@NonNull Context context) {
    super(context);
  }

  public LifeDialog(@NonNull Context context, int themeResId) {
    super(context, themeResId);
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogUtil.i(ClassName + ".onCreate()");
  }

  @Override
  protected void onStop() {
    super.onStop();
    LogUtil.i(ClassName + ".onStop()");
  }

  @Override
  protected void onStart() {
    super.onStart();
    LogUtil.i(ClassName + ".onStart()");
  }

  @Override
  public void dismiss() {
    super.dismiss();
    LogUtil.i(ClassName + ".dismiss()");
  }


}
