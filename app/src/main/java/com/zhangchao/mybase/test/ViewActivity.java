package com.zhangchao.mybase.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.zhangchao.common.base.BaseActivity;
import com.zhangchao.common.ui.SeatView;
import com.zhangchao.common.util.LogUtil;
import com.zhangchao.mybase.R;
import java.util.regex.Pattern;

/**
 * 创建时间: 2019/09/02
 * 作者: zhangchao042@ke.com
 * 描述: 一些View的测试Activity
 */
public class ViewActivity extends BaseActivity {

  private TextInputLayout mTextInputLayout;
  private EditText mEtInputPhoneNumber;
  private SeatView mSeatView;
  private Button mBtnShowDialog;
  private DialogFragment dialogFragment;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    //setPrintLifeCycle(true);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.view_test_layout);
    initViews();
    //查看Fragment工作流程源码
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    new Thread(new Runnable() {
      @Override
      public void run() {
        runOnUiThread(new Runnable() {
          @Override
          public void run() {

          }
        });
      }
    }).start();
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

    mSeatView = findViewById(R.id.seat_view);
    mSeatView.setData(10,20);
    mBtnShowDialog = findViewById(R.id.btn_show_dialog);
    mBtnShowDialog.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"TEST");
      }
    });
  }

  @Override
  public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    super.onSaveInstanceState(outState, outPersistentState);
    LogUtil.i("onSave");
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);
    return true;
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  public static boolean isInteger(String str) {
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
    return pattern.matcher(str).matches();
  }
}
