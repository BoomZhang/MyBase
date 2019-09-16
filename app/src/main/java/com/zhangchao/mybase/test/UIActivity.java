package com.zhangchao.mybase.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.zhangchao.mybase.R;

/**
 * 创建时间: 2019/08/09
 * 作者: zhangchao042@ke.com
 * 描述: 测试各种基础控件的Activity
 */
public class UIActivity extends AppCompatActivity implements View.OnClickListener{

  private FloatingActionButton fab;
  private int NUM = 1;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.ui_test_1);
    initViews();
  }

  private void initViews() {
    fab = findViewById(R.id.fab);
    fab.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.fab){
      fabGo(v);
    }
  }

  /**
   * 描述：点击FloatingActionButton，底部弹出Snackbar
   */
  private void fabGo(View view) {
    /**
     * View: SnackBar会找到一个父View，以寄存所赋的值，一般为CoordinatorLayout
     * Text: 显示的文本内容
     * Duration: 显示时间长短，LONG或者SHORT
     */
    Snackbar.make(view, "Here's a " + NUM++, Snackbar.LENGTH_LONG)
        .setAction("删除", new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            /**
             * 在SnackBar上设置一个Button，添加的点击监听事件
             */
          }
        })
        .addCallback(new Snackbar.Callback(){
          @Override
          public void onShown(Snackbar sb) {
            super.onShown(sb);
            /**
             * 在SnackBar出现的时候被调用
             */
          }

          @Override
          public void onDismissed(Snackbar transientBottomBar, int event) {
            super.onDismissed(transientBottomBar, event);
            /**
             * 在SnackBar消失的时候被调用
             */
          }
        })
        .show();
  }
}
