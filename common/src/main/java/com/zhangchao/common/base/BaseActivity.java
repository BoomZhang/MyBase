package com.zhangchao.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 创建时间: 2019/09/16
 * 作者: zhangchao042@ke.com
 * 描述: 所有Activity的基类
 */
public class BaseActivity extends LifeCycleActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  /**
   * 设置是否打印出Activity的生命周期，默认不打印
   * @param isPrintLifeCycle true则打印，false为不打印
   */
  protected void setPrintLifeCycle(boolean isPrintLifeCycle){
    super.isPrintLifeCycle = isPrintLifeCycle;
  }

}
