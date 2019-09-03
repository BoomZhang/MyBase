package com.zhangchao.mybase.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zhangchao.common.util.LogUtil;
import com.zhangchao.mybase.R;

/**
 * 创建时间: 2019/09/02
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class ViewActivity extends AppCompatActivity {

  private TextView textView;
  private Button button;
  private LinearLayout mll;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.view_test_layout);
    initViews();
  }

  private void initViews() {
    textView = findViewById(R.id.textView);
    textView.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        LogUtil.i("textview onTouch");
        return false;
      }
    });
    button = findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ((ConstraintLayout)findViewById(R.id.pppp)).scrollTo(-100,-200);
      }
    });
  }

  private void printViewValue(View view){
    LogUtil.i("Top = " + view.getTop());
    LogUtil.i("Bottom = " + view.getBottom());
    LogUtil.i("Left = " + view.getLeft());
    LogUtil.i("Right = " + view.getRight());
    LogUtil.i("Width = " + view.getWidth());
    LogUtil.i("Height = " + view.getHeight());
    LogUtil.i("X = " + view.getX());
    LogUtil.i("Y = " + view.getY());
    LogUtil.i("TranslationX = " + view.getTranslationX());
    LogUtil.i("TranslationY = " + view.getTranslationY());
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);
    LogUtil.i("activity onTouch");
    return true;
  }
}
