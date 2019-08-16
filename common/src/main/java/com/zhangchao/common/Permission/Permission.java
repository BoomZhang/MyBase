package com.zhangchao.common.Permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * 创建时间: 2019/08/15
 * 作者: zhangchao042@ke.com
 * 描述: 和系统权限有关的类,Activity目前只支持v7包下的AppCompatActivity
 */
public class Permission{

  private final static String FRAGMENT_TAG = "fragment";
  private static PermissionFragment fragment;
  private static String permissionStr;
  private static Status status_inner;

  private static final int PERMISSION_REQUEST_CODE = 808;

  /**
   * 检测当前是否有某种权限并做相应的处理
   * @param context
   * @param permission
   * @param status 是否有权限的处理
   * @return 有权限返回true,否则返回false
   */
  public static boolean check(Context context, String permission, Status status){
    int permissionCheck = ContextCompat.checkSelfPermission(context,permission);
    if(permissionCheck == PackageManager.PERMISSION_GRANTED){
      if(status != null){
        status.allow();
      }
      return true;
    }else{
      if(status != null){
        status.deny();
      }
      return false;
    }
  }

  /**
   * 在代码中动态申请权限
   * @param activity
   * @param permission
   * @param status
   */
  public static void apply(Activity activity, String permission, final Status status){
    if(status == null){
      return;
    }
    status_inner = status;
    permissionStr = permission;
    if(check(activity.getBaseContext(),permission,null)){
      status.allow();
    }else{
      FragmentManager fragmentManager = ((AppCompatActivity)activity).getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragment = (PermissionFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
      if (fragment != null) {
        fragmentTransaction.remove(fragment);
        fragment = null;
      }
      fragment = new PermissionFragment();
      fragmentTransaction.add(fragment, FRAGMENT_TAG).commit();
    }
  }

  public static class PermissionFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      requestPermissions(new String[]{permissionStr}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
        int[] grantResults) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      if(requestCode == PERMISSION_REQUEST_CODE){
        //如果请求被取消，grantResults数组为空
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          /**
           * 权限申请通过
           */
          status_inner.allow();
        } else {
          /**
           * 权限申请被拒绝
           */
          status_inner.deny();
        }
        return;
      }
    }
  }

  public interface Status{
    void allow();
    void deny();
  }

}
