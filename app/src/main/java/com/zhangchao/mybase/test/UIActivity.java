package com.zhangchao.mybase.test;

import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.zhangchao.common.util.ClipboardUtil;
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
  private Button mBtCopy;
  private static int NUM = 0;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.ui_test_1);
    initViews();
  }

  private void initViews() {
    fab = findViewById(R.id.fab);
    fab.setOnClickListener(this);
    mIv = findViewById(R.id.img_show);
    testImg(mIv);
    mEtInput = findViewById(R.id.etd_input);
    mBtCopy = findViewById(R.id.btn_copy);
    mBtCopy.setOnClickListener(this);
  }

  private void testImg(ImageView mIv) {
    //Resources res = this.getResources();
    //设置过渡图
    //TransitionDrawable transitionDrawable =
    //    (TransitionDrawable) ResourcesCompat.getDrawable(res,R.drawable.image_a_b,null);
    //mIv.setImageDrawable(transitionDrawable);
    //transitionDrawable.startTransition(3000);
    //MyDrawable myDrawable = new MyDrawable();
    //mIv.setImageDrawable(myDrawable);
    //mIv.setBackground(myDrawable);

    //绘制矢量图
    VectorDrawable  drawable =
        (VectorDrawable) ResourcesCompat.getDrawable(getResources(),R.drawable.battery_charging,null);
    mIv.setImageDrawable(drawable);
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.fab){
      fabGo(v);
    }else if(v.getId() == R.id.btn_copy){
      copyTest();
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
