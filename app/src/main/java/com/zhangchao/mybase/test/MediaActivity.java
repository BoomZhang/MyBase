package com.zhangchao.mybase.test;

import android.Manifest;
import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import com.zhangchao.common.Permission.Permission;
import com.zhangchao.common.base.BaseActivity;
import com.zhangchao.common.util.LogUtil;
import com.zhangchao.common.util.ToastUtil;
import com.zhangchao.mybase.R;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建时间
 * 作者: zhangchao042
 * 描述: 测试多媒体的Activity
 */
public class MediaActivity extends BaseActivity implements View.OnClickListener{

  private static final int REQUEST_IMAGE_CAPTURE = 1;
  private static final int REQUEST_VIDEO_CAPTURE = 2;
  private String currentPhotoPath;

  private Button mBtTakePhoto;
  private ImageView mIvShow;
  private Button mBtTakeVideo;

  private VideoView mVodShow;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.media_test_1);
    initView();
  }

  private void initView() {
    mBtTakePhoto = findViewById(R.id.btn_take_photo);
    mBtTakePhoto.setOnClickListener(this);
    mIvShow = findViewById(R.id.img_show);
    mVodShow = findViewById(R.id.vdo_show);
    mBtTakeVideo = findViewById(R.id.btn_take_video);
    mBtTakeVideo.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.btn_take_photo){
      takePhoto(v);
    }else if(v.getId() == R.id.btn_take_video){
      takeVideo(v);
    }
  }

  /**
   * 拍视频的方法
   */
  private void takeVideo(View v) {
    final Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
    if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
    }
  }

  /**
   * 拍照的方法
   */
  private void takePhoto(View v) {
    //表示将打开系统相机的Intent
    final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      File photoFile = null;
      try {
        //获取一个图片文件
        photoFile = createImageFile();
      } catch (IOException e) {
        e.printStackTrace();
      }

      if(photoFile != null){
        //其中第二个参数就是在AndroidManifest.xml中注册的FileProvider的authority属性
        Uri photoUri = FileProvider.getUriForFile(this,
            "com.zhangchao.mybase.fileprovider",
            photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
      }

      Permission.apply(this, Manifest.permission.CAMERA, new Permission.Status() {
        @Override
        public void allow() {
          startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

        @Override
        public void deny() {
          ToastUtil.showShort(getBaseContext(),"无权限");
        }
      });

    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      /**
       * 需要注意在takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);执行后
       * 即已经添加了存储图片的Uri之后，data中将不会保存信息，所以data == null
       * 如果不在Intent中添加Uri则data保存着拍照结果的缩略图
       */
      galleryAddPic();
    }

    if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
      Uri videoUri = data.getData();
      LogUtil.i(videoUri.toString());
      mVodShow.setVideoURI(videoUri);
      mVodShow.start();
    }
  }

  /**
   * 创建一个jpg文件，并且需要保存这个文件的路径currentPhotoPath
   * 创建方式是File.creatTempFile(),注意new File()的方式未成功，具体原因未知
   * 其中getExternalFilesDir()方法对应<external-path/>标签
   */
  private File createImageFile() throws IOException {
    // Create an image file name
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_" + timeStamp;
    File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(
        imageFileName,  /* prefix */
        ".jpg",         /* suffix */
        storageDir      /* directory */
    );
    currentPhotoPath = image.getAbsolutePath();
    return image;
  }

  private void galleryAddPic() {
    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    File f = new File(currentPhotoPath);
    Uri contentUri = Uri.fromFile(f);
    mIvShow.setImageURI(contentUri);
    mediaScanIntent.setData(contentUri);
    this.sendBroadcast(mediaScanIntent);
  }

}
