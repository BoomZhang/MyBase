package com.zhangchao.mybase.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.zhangchao.common.base.BaseActivity;
import com.zhangchao.common.store.SPUtil;
import com.zhangchao.mybase.R;

/**
 * 创建时间: 2019/10/10
 * 作者: zhangchao042@ke.com
 * 描述: 测试存储的Activity
 */
public class StoreActivity extends BaseActivity {

  private EditText mEdtKey,mEdtValue;
  private Button mBtSave;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.store_layout);
    initViews();
  }

  private void initViews() {
    mEdtKey = findViewById(R.id.key_input);
    mEdtValue = findViewById(R.id.value_input);
    mBtSave = findViewById(R.id.save);
    mBtSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        SPUtil.put(getBaseContext(),mEdtKey.getText().toString(),mEdtValue.getText().toString());
      }
    });
  }
}
