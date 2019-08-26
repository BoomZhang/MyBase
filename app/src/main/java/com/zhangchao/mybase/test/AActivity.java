package com.zhangchao.mybase.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.zhangchao.common.util.LogUtil;
import com.zhangchao.mybase.R;

/**
 * 创建时间: 2019/08/09
 * 作者: zhangchao042@ke.com
 * 描述: 测试Activity的生命周期
 */
public class AActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.aaaa);

    findViewById(R.id.btn_go_b).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(AActivity.this,BActivity.class);
        startActivity(intent);
      }
    });

    findViewById(R.id.myview).setVisibility(View.GONE);

    LogUtil.i("A.onCreate()");
  }

  @Override
  protected void onStart() {
    super.onStart();
    LogUtil.i("A.onStart()");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    LogUtil.i("A.onRestart()");
  }

  @Override
  protected void onResume() {
    super.onResume();
    LogUtil.i("A.onResume()");
    findViewById(R.id.myview).setVisibility(View.VISIBLE);
  }

  @Override
  protected void onPause() {
    super.onPause();
    LogUtil.i("A.onPause()");
  }

  @Override
  protected void onStop() {
    super.onStop();
    LogUtil.i("A.onStop()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    LogUtil.i("A.onDestroy()");
  }
}
