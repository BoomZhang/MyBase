package com.zhangchao.mybase.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.zhangchao.common.base.BaseActivity;
import com.zhangchao.mybase.R;

/**
 * 创建时间: 2019/10/29
 * 作者: zhangchao042@ke.com
 * 描述: 自定义View测试Activity
 */
public class MyViewActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.my_view_layout);
  }
}
