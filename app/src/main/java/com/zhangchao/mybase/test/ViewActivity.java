package com.zhangchao.mybase.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.widget.EditText;
import com.zhangchao.mybase.R;
import java.util.regex.Pattern;

/**
 * 创建时间: 2019/09/02
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class ViewActivity extends LifeCycleActivity {

  private TextInputLayout mTextInputLayout;
  private EditText mEtInputPhoneNumber;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.view_test_layout);
    initViews();
  }

  private void initViews() {
    mTextInputLayout = findViewById(R.id.text_input_layout);
    mEtInputPhoneNumber = findViewById(R.id.input_phone_number);
    mEtInputPhoneNumber.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!isInteger(s.toString())){
          mTextInputLayout.setError("验证码错误");
          mTextInputLayout.setErrorEnabled(true);
        }else{
          mTextInputLayout.setErrorEnabled(false);
        }
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);
    return true;
  }

  public static boolean isInteger(String str) {
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
    return pattern.matcher(str).matches();
  }
}
