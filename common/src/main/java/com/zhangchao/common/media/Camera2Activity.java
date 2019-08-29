package com.zhangchao.common.media;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import com.zhangchao.common.Permission.Permission;
import com.zhangchao.common.R;
import com.zhangchao.common.ui.CircleImageView;
import com.zhangchao.common.util.LogUtil;
import com.zhangchao.common.util.ToastUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 创建时间: 2019/08/16
 * 作者: zhangchao042@ke.com
 * 描述: 测试Camera2的使用
 */
public class Camera2Activity extends AppCompatActivity implements View.OnClickListener{


  private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
  ///为了使照片竖直显示
  static {
    ORIENTATIONS.append(Surface.ROTATION_0, 90);
    ORIENTATIONS.append(Surface.ROTATION_90, 0);
    ORIENTATIONS.append(Surface.ROTATION_180, 270);
    ORIENTATIONS.append(Surface.ROTATION_270, 180);
  }

  public final int BACK_CAMERA = 0; //后置摄像头的CameraId
  public final int FRONT_CAMERA = 1; //前置摄像头
  private int mCameraID = 0;

  private SurfaceView mSfvShow;
  private ImageButton mImbTakePhoto,mImChangeCamera;
  private CircleImageView mCimPhoto;

  private CameraDevice mCameraDevice;//摄像头设备
  private CameraManager mCameraManager;
  private CameraCaptureSession mCameraCaptureSession;
  private CaptureRequest.Builder mPreviewRequestBuilder;

  private SurfaceHolder mSfvHolder;
  private ImageReader mImageReader;
  private Handler childHandler;
  private Handler mainHandler;

  private SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        Permission.apply(Camera2Activity.this, Manifest.permission.CAMERA, new Permission.Status() {
          @Override
          public void allow() {
            initCamera();
          }

          @Override
          public void deny() {
            mImbTakePhoto.setClickable(false);
            mImChangeCamera.setClickable(false);
            ToastUtil.showLong(getBaseContext(),"没有权限，无法使用相机");
          }
        });
      }else {
        initCamera();
      }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
      if(null != mCameraDevice){
        mCameraDevice.close();
        mCameraDevice = null;
      }
    }
  };

  /**
   * 摄像头创建监听
   */
  private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onOpened(CameraDevice camera) {//打开摄像头
      mCameraDevice = camera;
      //开启预览
      takePreview();
    }

    @Override
    public void onDisconnected(CameraDevice camera) {//关闭摄像头
      if (null != mCameraDevice) {
        mCameraDevice.close();
        mCameraDevice = null;
      }
    }

    @Override
    public void onError(CameraDevice camera, int error) {//发生错误
      ToastUtil.showLong(getBaseContext(),"摄像头开启失败");
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
    setContentView(R.layout.camera2_view);
    initViews();
  }

  private void initViews() {
    mSfvShow = findViewById(R.id.sfv_show);
    mImbTakePhoto = findViewById(R.id.imb_take_photo);
    mImbTakePhoto.setOnClickListener(this);
    mImChangeCamera = findViewById(R.id.imb_change_camera);
    mImChangeCamera.setOnClickListener(this);
    mCimPhoto = findViewById(R.id.iv_show_photo);
    mCimPhoto.setOnClickListener(this);

    mSfvHolder = mSfvShow.getHolder();
    mSfvHolder.setKeepScreenOn(true);
    mSfvHolder.addCallback(mCallback);

  }

  @SuppressLint("MissingPermission")
  private void initCamera() {
    HandlerThread handlerThread = new HandlerThread("Camera2");
    handlerThread.start();
    childHandler = new Handler(handlerThread.getLooper());
    mainHandler = new Handler(getMainLooper());

    initImageReader();
    mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
    if(Permission.check(this,Manifest.permission.CAMERA,null)){
      try {
        mCameraManager.openCamera(mCameraID+"",stateCallback,mainHandler);
      } catch (CameraAccessException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * ImageReader在切换镜头时需要重新initImageReader
   * 否则会出现crash，原因未知
   */
  private void initImageReader(){
    mImageReader = ImageReader.newInstance(1080,1920,ImageFormat.JPEG,1);
    mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
      @Override
      public void onImageAvailable(ImageReader reader) {
        Image image = reader.acquireNextImage();
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        if (bitmap != null) {
          if(mCameraID == FRONT_CAMERA){
            //前置摄像头拍的要先旋转180度
            bitmap = adjustPhotoRotation(bitmap,180);
          }
          mCimPhoto.setImageBitmap(bitmap);
          saveBitmapToAlbum(bitmap);
        }
        image.close();
      }
    },mainHandler);
  }

  @RequiresApi(api = Build.VERSION_CODES.P)
  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.imb_take_photo){
      takePhoto();
    }else if(v.getId() == R.id.imb_change_camera){
      changeCamera();
    }else if(v.getId() == R.id.iv_show_photo){
      openAblum();
    }
  }

  private void takePreview() {
    try {
      mPreviewRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
      mPreviewRequestBuilder.addTarget(mSfvHolder.getSurface());

      mCameraDevice.createCaptureSession(
          Arrays.asList(mSfvHolder.getSurface(),mImageReader.getSurface()),
          new CameraCaptureSession.StateCallback() {
            @Override
            public void onConfigured(CameraCaptureSession session) {
              if(mCameraDevice == null) return;
              mCameraCaptureSession = session;
              // 自动对焦
              mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
              // 打开闪光灯
              mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.FLASH_MODE_OFF);
              CaptureRequest captureRequest = mPreviewRequestBuilder.build();
              try {
                mCameraCaptureSession.setRepeatingRequest(captureRequest,null,childHandler);
              } catch (CameraAccessException e) {
                e.printStackTrace();
              }
            }

            @Override
            public void onConfigureFailed(CameraCaptureSession session) {
              ToastUtil.showLong(getBaseContext(),"相机配置失败");
            }
          },childHandler);
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }

  }

  private void takePhoto(){
    if (mCameraDevice == null) return;
    // 创建拍照需要的CaptureRequest.Builder
    final CaptureRequest.Builder captureRequestBuilder;
    try {
      captureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
      // 将imageReader的surface作为CaptureRequest.Builder的目标
      captureRequestBuilder.addTarget(mImageReader.getSurface());
      // 自动对焦
      captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
      // 自动曝光
      captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
      // 获取手机方向
      int rotation = getWindowManager().getDefaultDisplay().getRotation();
      // 根据设备方向计算设置照片的方向
      captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));
      //拍照
      CaptureRequest mCaptureRequest = captureRequestBuilder.build();
      mCameraCaptureSession.capture(mCaptureRequest, null, childHandler);
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.P)
  @SuppressLint("MissingPermission")
  private void changeCamera(){
    if(mCameraDevice != null){

      mCameraDevice.close();
      mCameraDevice = null;
    }
    mCameraID ^= 1;
    try {
      /**
       * 在切换镜头的时候，ImageReader之前的surface会有问题
       * 需要重新init()，原因未知
       */
      initImageReader();
      mCameraManager.openCamera(mCameraID+"", stateCallback, mainHandler);
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }
  }

  Bitmap adjustPhotoRotation(Bitmap bm, final int orientationDegree) {
    Matrix m = new Matrix();
    m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);

    try {
      Bitmap bm1 = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);
      return bm1;
    } catch (OutOfMemoryError ex) {
    }
    return null;
  }

  /**
   * 保存Bitmap到手机相册中
   * @param bitmap
   */
  private void saveBitmapToAlbum(Bitmap bitmap){
    File qrCache = new File(getBaseContext().getExternalCacheDir(),"cache");
    if(!qrCache.exists()){
      qrCache.mkdir();
    }
    String fileName = System.currentTimeMillis() + "jpg";
    final File picFile = new File(qrCache, fileName);
    FileOutputStream fos  = null;
    try {
      fos = new FileOutputStream(picFile);
      bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
      fos.flush();
      fos.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    Permission.apply(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,
        new Permission.Status() {
          @Override
          public void allow() {
            try {
              MediaStore.Images.Media.insertImage(getBaseContext().getContentResolver(),
                  picFile.getAbsolutePath(), picFile.getName(), null);
            } catch (FileNotFoundException e) {
              e.printStackTrace();
            }
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            getBaseContext().sendBroadcast(mediaScanIntent);
          }

          @Override
          public void deny() {
            ToastUtil.showLong(getBaseContext(),"无权限存储相片！");
          }
        });
  }

  /**
   * 打开系统相册
   * 未解决，不知道该怎么跳转相册而不是选择图片
   */
  private void openAblum(){
    //Intent intent = new Intent(Intent.ACTION_VIEW);
    //intent.setType("image/*");
    //this.startActivity(intent);
  }

}
