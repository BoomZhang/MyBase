package com.zhangchao.mybase.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.zhangchao.common.util.LogUtil;
import com.zhangchao.mybase.R;

/**
 * 创建时间: 2019/08/10
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class BActivity extends AppCompatActivity {

  private Handler mHandler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what){
        case 1:
          doin();
          break;
      }
    }
  };

  private void doin() {
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.bbbb);
    LogUtil.i("B.onCreate()");
  }

  @Override
  protected void onStart() {
    super.onStart();
    LogUtil.i("B.onStart()");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    LogUtil.i("B.onRestart()");
  }

  @Override
  protected void onResume() {
    super.onResume();
    LogUtil.i("B.onResume()");
  }

  @Override
  protected void onPause() {
    super.onPause();
    LogUtil.i("B.onPause()");
  }

  @Override
  protected void onStop() {
    super.onStop();
    LogUtil.i("B.onStop()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    LogUtil.i("B.onDestroy()");
  }

}
