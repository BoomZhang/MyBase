package com.zhangchao.mybase.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;
import com.zhangchao.common.util.LogUtil;

/**
 * 创建时间: 2019/08/22
 * 作者: zhangchao042@ke.com
 * 描述:
 */
@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {

  public MyImageView(Context context) {
    super(context);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    LogUtil.i("onMeasure");
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    LogUtil.i("onLayout");
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    LogUtil.i("onDraw");


  }

}
