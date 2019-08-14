package com.zhangchao.mybase.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.zhangchao.common.util.LogUtil;
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
public class MediaActivity extends AppCompatActivity implements View.OnClickListener{

  private static final int REQUEST_IMAGE_CAPTURE = 1;
  private String currentPhotoPath;

  private Button mBtTakePhoto;
  private ImageView mIvShow;


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
  }

  @Override
  public void onClick(View v) {
    if(v.getId() == R.id.btn_take_photo){
      takePhoto(v);
    }
  }

  private void takePhoto(View v) {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    //takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      File photoFile = null;
      try {
        photoFile = createImageFile();
      } catch (IOException e) {
        e.printStackTrace();
      }

      if(photoFile != null){

        Uri photoUri = FileProvider.getUriForFile(this,
            "com.zhangchao.mybase.fileprovider",
            photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
      }

      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      galleryAddPic();
    }
  }

  private File createImageFile() throws IOException {
    // Create an image file name
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_" + timeStamp;
    File storageDir = Environment.getExternalStorageDirectory();
    File image = new File(storageDir,"pictures/" + imageFileName + ".jpg");
    // Save a file: path for use with ACTION_VIEW intents
    //File image = File.createTempFile("pictures/" + imageFileName,".jpg",storageDir);
    currentPhotoPath = image.getAbsolutePath();
    LogUtil.i("A++++++++" + currentPhotoPath);
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
