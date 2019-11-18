package com.zhangchao.mybase.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建时间: 2019/10/23
 * 作者: zhangchao042@ke.com
 * 描述: 测试绘图
 */
public class MyView extends View {

  private Paint paint;
  private int mWidth,mHeight;

  public MyView(Context context) {
    super(context);
    paint = new Paint();
  }

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
    paint = new Paint();
    paint.setStyle(Paint.Style.FILL);
    paint.setStrokeWidth(10);
  }

  public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    paint = new Paint();
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mWidth = w;
    mHeight = h;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    //drawCircle(canvas);
    //drawLine(canvas);
    //drawMutlLines(canvas);
    //drawRect(canvas);
    //drawPath(canvas);
    //drawText(canvas);
    testCanvas(canvas);
  }

  private void testCanvas(Canvas canvas){
    canvas.translate(mWidth / 2, mHeight / 2);

    Path path1 = new Path();
    Path path2 = new Path();
    Path path3 = new Path();
    Path path4 = new Path();

    path1.addCircle(0, 0, 200, Path.Direction.CW);
    path2.addRect(0, -200, 200, 200, Path.Direction.CW);
    path3.addCircle(0, -100, 100, Path.Direction.CW);
    path4.addCircle(0, 100, 100, Path.Direction.CCW);


    path1.op(path2, Path.Op.DIFFERENCE);
    path1.op(path3, Path.Op.UNION);
    path1.op(path4, Path.Op.DIFFERENCE);

    canvas.drawPath(path1, paint);
  }


  //画点
  private void drawPoint(Canvas canvas){
    /**
     * 与绘制直线相似
     * drawPoint(float x, float y, @NonNull Paint paint)
     * drawPoints(@Size(multiple=2) @NonNull float[] pts, @NonNull Paint paint)
     * drawPoints(@Size(multiple=2) @NonNull float[] pts, int offset, int count, @NonNull Paint paint)
     */
  }

  //画直线
  private void drawLine(Canvas canvas){
    paint.setStyle(Paint.Style.FILL);
    paint.setStrokeWidth(30);
    paint.setColor(Color.parseColor("#FF0000"));
    canvas.drawLine(100,100,150,600,paint);
  }

  //画多条线
  private void drawMutlLines(Canvas canvas){

    /**
     * drawLines(@Size(min=4,multiple=2) @NonNull float[] pts, int offset, int count, Paint paint)
     * drawLines(@Size(min=4,multiple=2) @NonNull float[] pts, @NonNull Paint paint)
     *
     * 参数:
     * pts : 是点的集合且大小最小为4而且是2的倍数。表示每2个点连接形成一条直线，pts 的组织方式为{x1,y1,x2,y2….}
     * offset : 集合中跳过的数值个数，注意不是点的个数！一个点是两个数值
     * count : 参与绘制的数值的个数，指pts[]里数值个数，而不是点的个数，因为一个点是两个数值
     */

    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    paint.setStrokeWidth(5);

    float [] pts={50,100,100,200,200,300,300,400};
    paint.setColor(Color.RED);
    canvas.drawLines(pts,paint);

    paint.setColor(Color.BLUE);
    canvas.drawLines(pts,1,4,paint);//去掉第一个数50，取之后的4个数即100,100,200,200
  }

  //画矩形
  private void drawRect(Canvas canvas){
    /**
     * 矩形
     * drawRect(@NonNull RectF rect, @NonNull Paint paint)
     * drawRect(@NonNull Rect r, @NonNull Paint paint)
     * drawRect(float left, float top, float right, float bottom, @NonNull Paint paint)
     * 圆角矩形
     * drawRoundRect(@NonNull RectF rect, float rx, float ry, @NonNull Paint paint)
     * drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, @NonNull Paint paint)
     * rx ： 生成圆角的椭圆X轴半径
     * ry ： 生成圆角的椭圆Y轴的半径
     */
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.FILL);
    paint.setStrokeWidth(5);
    paint.setColor(Color.BLUE);
    //canvas.drawRect(100,100,300,400,paint);
    RectF rect = new RectF(100, 10, 500, 300);
    canvas.drawRoundRect(rect, 60, 20, paint);
  }

  //画圆形
  private void drawCircle(Canvas canvas){
    /**
     * 参数：
     * cx ： 圆心X坐标
     * cy ： 圆心Y坐标
     * radius ： 半径
     */
    // 设置画笔的颜色
    paint.setColor(Color.RED);
    // 设置画笔填充的样式
    //paint.setStyle(Paint.Style.STROKE);
    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    // 设置画笔的宽度
    paint.setStrokeWidth(20);
    paint.setAntiAlias(true);

    // 设置画布，把画笔画上去，画布是圆形
    canvas.drawCircle(300,300,160, paint);
  }

  private void drawPath(Canvas canvas){
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(10);

    paint.setColor(Color.parseColor("#FF0000"));
    Path path = new Path();
    path.moveTo(100,100);
    path.rLineTo(100,200);
    //path.lineTo(400,0);
    //path.lineTo(600,400);

    //path.lineTo(200, 200);
    //path.moveTo(300,300);//moveTo
    //path.lineTo(400, 0);

    //path.lineTo(200, 200);
    //path.setLastPoint(300,100);//setLastPoint
    //path.lineTo(400, 0);

    //path.moveTo(100,100);
    //path.lineTo(500,100);
    //path.lineTo(300,400);
    //path.close();

    //path.moveTo(100,400);
    //path.quadTo(300, 100, 400, 400);

    //path.moveTo(100, 400);
    //path.cubicTo(100, 400, 300, 100, 400, 400);

    //path.addRect(100, 200, 500, 400, Path.Direction.CCW);
    //path.setLastPoint(200,400);

    canvas.drawPath(path,paint);
  }

  private void drawText(Canvas canvas){
    paint.setStrokeWidth(5);
    paint.setTextSize(80);
    //设置绘图样式 为填充
    paint.setStyle(Paint.Style.FILL);
    //paint.setFakeBoldText(true);//是否粗体文字
    //paint.setUnderlineText(true);//设置下划线
    paint.setStrikeThruText(true);//设置删除线效果
    paint.setTextSkewX(-0.25f);//设置斜体
    //paint.setTextScaleX(2);//水平方向拉伸2倍
    canvas.drawText("我是一颗小小的石头",100,100, paint);
    //canvas.drawTextRun("我是一颗小小的石头".toCharArray(), 1, 4, 1, 4, 100, 300,false, paint);
  }

}
