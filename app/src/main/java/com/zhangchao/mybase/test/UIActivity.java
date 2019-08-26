package com.zhangchao.mybase.test;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.zhangchao.common.Permission.Permission;
import com.zhangchao.common.util.ClipboardUtil;
import com.zhangchao.common.util.LogUtil;
import com.zhangchao.common.util.ToastUtil;
import com.zhangchao.mybase.R;

/**
 * 创建时间: 2019/08/09
 * 作者: zhangchao042@ke.com
 * 描述: 测试各种基础控件的Activity
 */
public class UIActivity extends AppCompatActivity implements View.OnClickListener{

  private FloatingActionButton fab;
  private ImageView mIv;
  private EditText mEtInput;
  private Button mBtCopy,mBtLoadPicture;
  private static int NUM = 0;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.ui_test_1);
    initViews();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    //将信息保存到displayMetrics中.
    this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    //1.x轴和y轴的dpi.
    LogUtil.d("ydpi=" + displayMetrics.ydpi);
    LogUtil.d("xdpi=" + displayMetrics.xdpi);
    //2.x轴和y轴的像素个数.
    LogUtil.d("heightPixels=" + displayMetrics.heightPixels);
    LogUtil.d("widthPixels=" + displayMetrics.widthPixels);
    //3.dpi
    LogUtil.d("densityDpi=" + displayMetrics.densityDpi);
    //4.dpi/160.
    LogUtil.d("density=" + displayMetrics.density);
    //5.通常情况下和density相同.
    LogUtil.d("scaledDensity=" + displayMetrics.scaledDensity);
  }

  private void initViews() {
    fab = findViewById(R.id.fab);
    fab.setOnClickListener(this);
    mIv = findViewById(R.id.img_show);
    mBtLoadPicture = findViewById(R.id.btn_load_picture);
    mBtLoadPicture.setOnClickListener(this);
    mEtInput = findViewById(R.id.etd_input);
    mBtCopy = findViewById(R.id.btn_copy);
    mBtCopy.setOnClickListener(this);
  }

  private void testImg() {
    final String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
    Permission.apply(this, Manifest.permission.INTERNET, new Permission.Status() {
      @Override
      public void allow() {
        Glide.with(UIActivity.this)
            .load(url)
            .placeholder(R.drawable.test_1)
            .into(mIv);
      }

      @Override
      public void deny() {

      }
    });
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.fab){
      fabGo(v);
    }else if(v.getId() == R.id.btn_copy){
      copyTest();
    }else if(v.getId() == R.id.btn_load_picture){
      testImg();
    }

  }

  private void copyTest() {
    ClipboardUtil.copyText(this,"test",mEtInput.getText().toString());
    ToastUtil.showShort(this,"复制成功");
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
    Snackbar.make(view, "Here's a " + String.valueOf(NUM++), Snackbar.LENGTH_LONG)
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
