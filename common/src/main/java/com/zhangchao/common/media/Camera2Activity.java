package com.zhangchao.common.media;

import android.Manifest;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.zhangchao.common.Permission.Permission;
import com.zhangchao.common.R;
import com.zhangchao.common.util.ToastUtil;

/**
 * 创建时间: 2019/08/16
 * 作者: zhangchao042@ke.com
 * 描述: 测试Camera2的使用
 */
public class Camera2Activity extends AppCompatActivity implements View.OnClickListener{

  private TextureView mTuvShow;
  private Button mBtTakePhoto;
  private ImageView mImShow;
  private CameraDevice cameraDevice;

  protected final CameraDevice.StateCallback stateCallback
      = new CameraDevice.StateCallback() {
    @Override
    public void onOpened(CameraDevice camera) {
      cameraDevice = camera;
    }

    @Override
    public void onDisconnected(CameraDevice camera) {

    }

    @Override
    public void onError(CameraDevice camera, int error) {

    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.camera2_view);
    initViews();
  }

  private void initViews() {
    mTuvShow = findViewById(R.id.texture_view);
    mTuvShow.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
      @Override
      public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        openCamera(width,height);
      }

      @Override
      public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

      }

      @Override
      public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
      }

      @Override
      public void onSurfaceTextureUpdated(SurfaceTexture surface) {

      }
    });
    mBtTakePhoto = findViewById(R.id.btn_take_photo);
    mBtTakePhoto.setOnClickListener(this);
    mImShow = findViewById(R.id.img_show);
  }

  private void openCamera(final int width, final int height) {
    Permission.apply(this, Manifest.permission.CAMERA,
        new Permission.Status() {
          @Override
          public void allow() {
            setUpCameraOutputs(width,height);
            waitOpen();
          }

          @Override
          public void deny() {
            ToastUtil.showLong(getBaseContext(),"无权限");
          }
        });
  }

  private void setUpCameraOutputs(int width, int height) {
    CameraManager manager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
    try {
      for(String cameraId : manager.getCameraIdList()){
        CameraCharacteristics cameraCharacteristics = manager.getCameraCharacteristics(cameraId);
        Integer facing = cameraCharacteristics.get(cameraCharacteristics.LENS_FACING);
        if(facing == null || facing != CameraCharacteristics.LENS_FACING_BACK){
          continue;
        }
        //manager.openCamera(cameraId,statusCallback,w);
        return;
      }
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }
  }

  private void waitOpen(){

  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.btn_take_photo){

    }
  }
}
