package com.zhangchao.mybase.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import com.github.ahmadnemati.wind.WindView;
import com.github.ahmadnemati.wind.enums.TrendType;
import com.zhangchao.common.base.BaseActivity;
import com.zhangchao.common.util.LogUtil;
import com.zhangchao.mybase.R;

/**
 * 创建时间: 2019/10/15
 * 作者: zhangchao042@ke.com
 * 描述: 动画学习Activity
 */
public class AnimationActivity extends BaseActivity {

  private Button mBtStartAnimation;
  private ImageView mImgTest;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.animation_layout);
    initViews();
  }

  private void initViews() {
    mBtStartAnimation = findViewById(R.id.btn_start_animtion);
    mImgTest = findViewById(R.id.img_show);
    mImgTest.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        LogUtil.i("KKK");
      }
    });
    mBtStartAnimation.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.first_animation);
        animation.setDuration(2000);
        mImgTest.startAnimation(buildAnimation());
      }
    });

    WindView windView= (WindView) findViewById(R.id.windview);
    windView.setPressure(20);
    windView.setPressureUnit("in Hg");
    windView.setWindSpeed(1);
    windView.setWindSpeedUnit(" km/h");
    windView.setTrendType(TrendType.UP);
    windView.start();

  }

  private AnimationSet buildAnimation(){
    /*
     *  创建一个AnimationSet，它能够同时执行多个动画效果
     *  构造方法的入参如果是“true”，则代表使用默认的interpolator，如果是“false”则代表使用自定义interpolator
     */
    AnimationSet mAnimationSet = new AnimationSet(true);

    //透明度动画，从完全透明到不透明，我们的动画都是float型的，所以，在写数字的时候，要加f
    Animation alphAnima = new AlphaAnimation(0.0f, 1.0f);
    /*
     *  创建一个旋转动画对象
     *  入参列表含义如下：
     *  1.fromDegrees：从哪个角度开始旋转
     *  2.toDegrees：旋转到哪个角度结束
     *  3.pivotXType：旋转所围绕的圆心的x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
     *  4.pivotXValue：旋转所围绕的圆心的x轴坐标,0.5f表明是以自身这个控件的一半长度为x轴
     *  5.pivotYType：y轴坐标的类型
     *  6.pivotYValue：y轴坐标
     */
    Animation rotateAnim = new RotateAnimation(0f, 720f,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f);

    /*
     *  创建一个缩放效果的动画
     *  入参列表含义如下：
     *  fromX：x轴的初始值
     *  toX：x轴缩放后的值
     *  fromY：y轴的初始值
     *  toY：y轴缩放后的值
     *  pivotXType：x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
     *  pivotXValue：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
     *  pivotYType：y轴坐标的类型
     *  pivotYValue：轴的值，0.5f表明是以自身这个控件的一半长度为y轴
     */
    Animation scaleAnimation = new ScaleAnimation(
        0f,1f,0f,1f,
        Animation.RELATIVE_TO_SELF,0.5f,
        Animation.RELATIVE_TO_SELF,0.5f);

    /*
     *  创建一个移动动画效果
     *  入参的含义如下：
     *  fromXType：移动前的x轴坐标的类型
     *  fromXValue：移动前的x轴的坐标
     *  toXType：移动后的x轴的坐标的类型
     *  toXValue：移动后的x轴的坐标
     *  fromYType：移动前的y轴的坐标的类型
     *  fromYValue：移动前的y轴的坐标
     *  toYType：移动后的y轴的坐标的类型
     *  toYValue：移动后的y轴的坐标
     */
    Animation translateAnimation = new TranslateAnimation(
        Animation.RELATIVE_TO_SELF,0f,Animation.ABSOLUTE,360f,
        Animation.RELATIVE_TO_SELF,0f,Animation.ABSOLUTE,360f);

    mAnimationSet.addAnimation(alphAnima);
    mAnimationSet.addAnimation(rotateAnim);
    mAnimationSet.addAnimation(scaleAnimation);
    mAnimationSet.addAnimation(translateAnimation);
    mAnimationSet.setDuration(2000);//动画持续时间时间
    mAnimationSet.setInterpolator(new DecelerateInterpolator()); //添加插值器，下面会有说明
    mAnimationSet.setFillAfter(true);
    return mAnimationSet;

  }

}
