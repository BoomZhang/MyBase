package com.zhangchao.mybase.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 创建时间: 2019/10/31
 * 作者: zhangchao042@ke.com
 * 描述: 带获取验证码标签的自定义Edittext
 */
public class VerificationEditText extends AppCompatEditText {

  private String text = "获取验证码";
  private Paint paint;

  public VerificationEditText(Context context) {
    super(context);
  }

  public VerificationEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    paint = new Paint();
    paint.setColor(getTextColors().getDefaultColor());
    paint.setStyle(Paint.Style.FILL);
    paint.setTextSize(getTextSize());
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawText(text,getMeasuredWidth() - paint.measureText(text) ,getBaseline(), paint);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int x = (int) event.getX();
    int y = (int) event.getY();

    int left = getWidth() - (int)paint.measureText(text);
    int right = getWidth();

    if(x >= left && x < right){
      if(event.getAction() == MotionEvent.ACTION_UP){
        setVerificationText("正在获取验证码");
      }
      return true;
    }

    return super.onTouchEvent(event);
  }

  public void setVerificationText(String text){
    this.text = text;
    invalidate();
  }

}
