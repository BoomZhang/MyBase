package com.zhangchao.mybase.test;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.zhangchao.common.util.LogUtil;
import com.zhangchao.mybase.R;

/**
 * 创建时间: 2019/10/08
 * 作者: zhangchao042@ke.com
 * 描述:
 */
public class MyDialogFragment extends DialogFragment {

  private Context mContext;
  private Button mBtnDismiss;
  private EditText mEdtInput;
  private Handler mHandler = new Handler();

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    View view = inflater.inflate(R.layout.common_layout,null);
    initViews(view);
    return view;
  }

  private void initViews(View view) {
    mBtnDismiss = view.findViewById(R.id.dialog_fragment);
    mEdtInput = view.findViewById(R.id.et_ss);
    mBtnDismiss.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        LogUtil.i(mEdtInput.getText().toString());
        dismissAllowingStateLoss();
      }
    });

    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(3000);
          mHandler.post(new Runnable() {
            @Override
            public void run() {
              dismissAllowingStateLoss();
            }
          });
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();

  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mContext = getContext();
    mContext.getString(R.string.app_name);
  }


}
