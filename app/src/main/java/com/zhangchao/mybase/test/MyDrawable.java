package com.zhangchao.mybase.test;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * 创建时间: 2019/08/12
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class MyDrawable extends Drawable {

  private Paint redPaint;

  public MyDrawable(){
    redPaint = new Paint();
    redPaint.setARGB(255,255,0,0);
    redPaint.setAlpha(100);
  }

  @Override
  public void draw(Canvas canvas) {
    int width = getBounds().width();
    int height = getBounds().height();
    float radius = Math.min(width, height) / 2;
    // Draw a red circle in the center
    canvas.drawCircle(width/2, height/2, radius, redPaint);
  }

  @Override
  public void setAlpha(int alpha) {

  }

  @Override
  public void setColorFilter(ColorFilter colorFilter) {

  }

  @Override
  public int getOpacity() {
    return PixelFormat.OPAQUE;
  }
}
