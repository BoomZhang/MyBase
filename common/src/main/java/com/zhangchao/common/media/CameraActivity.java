package com.zhangchao.common.media;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.zhangchao.common.Permission.Permission;
import com.zhangchao.common.R;
import com.zhangchao.common.util.ToastUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 创建时间: 2019/08/15
 * 作者: zhangchao042@ke.com
 * 描述: 自定义相机的Activity
 */
public class CameraActivity extends AppCompatActivity implements View.OnClickListener{

  private Camera camera;
  private SurfaceView surfaceView;
  private SurfaceHolder holder;
  private Button mBtTake;
  private ImageView mImShow;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.camera_view);
    surfaceView = findViewById(R.id.surface_view);
    holder = surfaceView.getHolder();
    mBtTake = findViewById(R.id.btn_take_photo);
    mImShow = findViewById(R.id.img_show);
    mBtTake.setOnClickListener(this);
    holder.addCallback(new SurfaceHolder.Callback() {
      @Override
      public void surfaceCreated(SurfaceHolder holder) {
        //缓存创建的时候
        //得到相机 初始化
        //得到相机对象
        //camera=getCamera();
        //如果相机不为空
        if (camera!=null){
          try {
            //preview预览 display显示 缓存持有者
            //设置缓存路径
            camera.setPreviewDisplay(holder);
            //缓存开始预览
            //开始缓存
            camera.startPreview();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }

      @Override
      public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

      }

      @Override
      public void surfaceDestroyed(SurfaceHolder holder) {
        //缓存销毁时 release释放
        //释放相机 以免再次使用相机出现被占用的现象
        releaseCamera();
      }
    });

    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    if(camera == null){
      Permission.apply(CameraActivity.this, Manifest.permission.CAMERA
          , new Permission.Status() {
            @Override
            public void allow() {
              camera = Camera.open();
              openCamera();
            }

            @Override
            public void deny() {
              ToastUtil.showLong(getBaseContext()," 权限不允许");
            }
          });
    }

  }


  private void openCamera(){
    if (camera != null){
      try {
        //相机 设置preview预览 display显示
        camera.setPreviewDisplay(holder);
        //相机开始预览  这是相机里的方法  调用
        //preview预览
        camera.startPreview();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.btn_take_photo){
      //照片拍摄图片 照片回调
      //括号中需要传入几个参数
      camera.takePicture( null,null,new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data,Camera camera) {
          //预览
          //bitmap位图  array数组 数据  数据的长度
          //得到数据bitmap  数组 数据为0  数据的长度
          Bitmap bitmap= BitmapFactory.decodeByteArray( data,0,data.length );
          //预览视图设置图片位图
          mImShow.setImageBitmap( bitmap );
          //这里把拍摄的图片设置到UI界面的图片上
          //并没有进行处理 你也可以把他储存到内存卡上 把bitmap进行封装
          //buffer缓冲 outPut输出 steam流 缓冲输入流初始化
          BufferedOutputStream bufferedOutputStream=null;
          //对缓存进行初始化
          //可能会出现bug  对可能出现的异常进行抛出
          try  {
            String fileName="hey"+System.currentTimeMillis()+".jpg";
            //设置文件名
            bufferedOutputStream = new BufferedOutputStream( new FileOutputStream( new File( Environment
                .getExternalStorageDirectory(),fileName ) )) ;
            //保存  100压缩率 按原图保存
            //compress压缩 format格式 100 缓冲输出流
            bitmap.compress( Bitmap.CompressFormat.JPEG,100,bufferedOutputStream );
            //对图片进行压缩  设置输出流为内容

          }catch (FileNotFoundException e){
            e.printStackTrace();
          }finally {
            //最后对所用到的东西进行关闭 以免内存泄漏
            //使用完毕假如缓冲输出流不是空值 关闭缓存输入流
            //防止使用后用户打开其他软件会出现bug
            if (bufferedOutputStream!=null){
              //判断输出流是否为空
              try {
                //对输出流进行关闭
                bufferedOutputStream.close();
              } catch (IOException e) {
                //抛出异常
                e.printStackTrace();
              }
            }

          }
        }
      } );
    }
  }

  @Override
  protected void onDestroy() {
    //release释放
    releaseCamera();
    super.onDestroy();
  }

  //得到相机
  private Camera getCamera() {
    //如果相机 为空
    if (camera == null) {
      try {

      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    //不管怎么样  返回相机
    return camera;
  }
  //release释放
  private void releaseCamera(){
    //如果相机不为空 释放相机   初始化
    if (camera!=null){
      camera.release();
      camera = null;
    }
  }



}
