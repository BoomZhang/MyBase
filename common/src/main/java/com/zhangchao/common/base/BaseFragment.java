package com.zhangchao.common.base;

/**
 * 创建时间: 2019/09/16
 * 作者: zhangchao042@ke.com
 * 描述: 所有Fragment的基础类
 */
public class BaseFragment extends LifeCycleFragment {

  /**
   * 设置是否打印出Fragment的生命周期，默认不打印
   * @param isPrintLifeCycle true则打印，false为不打印
   */
  protected void setPrintLifeCycle(boolean isPrintLifeCycle){
    super.isPrintLifeCycle = isPrintLifeCycle;
  }

}
