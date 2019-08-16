package com.zhangchao.common.media;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

/**
 * 创建时间: 2019/08/15
 * 作者: zhangchao042@ke.com
 * 描述: 自定义相机的类
 */
public class Preview extends ViewGroup implements SurfaceHolder.Callback {

  private SurfaceView surfaceView;
  private SurfaceHolder holder;

  public Preview(Context context) {
    super(context);

    surfaceView = new SurfaceView(context);
    addView(surfaceView);

    holder = surfaceView.getHolder();
    holder.addCallback(this);
    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {

  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {

  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {

  }

}
