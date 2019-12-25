package com.example.kesy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class ShapeImageView extends ImageView {
    private Context mContext;
    private int border_size = 0;// 边框厚度
    private int in_border_color = 0;// 内圆边框颜色
    private int out_border_color = 0;// 外圆边框颜色
    private int defColor = 0xFFFFFFFF;// 默认颜色
    private int width = 0;// 控件的宽度
    private int height = 0;// 控件的高度
    private String shape_type;// 形状的类型

    public ShapeImageView(Context context) {
        super(context);
        this.mContext = context;

    }

    public ShapeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        setAttributes(attrs);

    }

    private void setAttributes(AttributeSet attrs) {
        TypedArray mArray = mContext.obtainStyledAttributes(attrs, R.styleable.shapeimageview);
        // 得到边框厚度，否则返回0
        border_size = mArray.getDimensionPixelSize(R.styleable.shapeimageview_border_size, 0);
        // 得到内边框颜色，否则返回默认颜色
        in_border_color = mArray.getColor(
                R.styleable.shapeimageview_in_border_color, defColor);
        // 得到外边框颜色，否则返回默认颜色
        out_border_color = mArray.getColor(
                R.styleable.shapeimageview_out_border_color, defColor);
        // 得到形状的类型
        shape_type = mArray.getString(R.styleable.shapeimageview_shape_type);
        mArray.recycle();// 回收mArray
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        // super.onDraw(canvas); 必须去掉该行或注释掉，否则会出现两张图片
        // 得到传入的图片
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        this.measure(0, 0);
        if (drawable.getClass() == NinePatchDrawable.class) {// 如果该传入图片是.9格式的图片
            return;
        }

        Bitmap mBitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap cpBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
        width = getWidth();
        height = getHeight();
        int radius = 0;//
        if ("round".equals(shape_type)) {
            if (in_border_color != defColor && out_border_color != defColor) {
                radius = (width < height ? width : height) / 2 - 2 * border_size;
                drawCircleBorder(canvas, radius + border_size / 2, in_border_color);
                drawCircleBorder(canvas, radius + border_size + border_size / 2, out_border_color);
            } else if (in_border_color != defColor && out_border_color == defColor) {
                radius = (width < height ? width : height) / 2 - border_size;
                drawCircleBorder(canvas, radius + border_size / 2, in_border_color);
            } else if (in_border_color == defColor && out_border_color != defColor) {
                radius = (width < height ? width : height) / 2 - border_size;
                drawCircleBorder(canvas, radius + border_size / 2, out_border_color);
            } else {
                radius = (width < height ? width : height) / 2;
            }
        } else {
            radius = (width < height ? width : height) / 2;

        }
        Bitmap shapeBitmap = drawShapeBitmap(cpBitmap, radius);
        canvas.drawBitmap(shapeBitmap, width / 2 - radius, height / 2 - radius, null);
    }

    private Bitmap drawShapeBitmap(Bitmap bmp, int radius) {
        Bitmap squareBitmap;
        Bitmap scaledBitmap;
        int diameter = radius * 2;
        // 传入位图的宽高
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        int squarewidth = 0, squareheight = 0;
        int x = 0, y = 0;
        if (h > w) {
            squarewidth = squareheight = w;
            x = 0;
            y = (h - w) / 2;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squarewidth, squareheight);
        } else if (h < w) {
            squarewidth = squareheight = h;
            x = (w - h) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squarewidth, squareheight);
        } else {
            squareBitmap = bmp;
        }
        if (squareBitmap.getWidth() != diameter || squareBitmap.getHeight() != diameter) {
            scaledBitmap = Bitmap.createScaledBitmap(squareBitmap, diameter, diameter, true);
        } else {
            scaledBitmap = squareBitmap;
        }
        Bitmap outputbmp = Bitmap.createBitmap(scaledBitmap.getWidth(), scaledBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outputbmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        if ("star".equals(shape_type)) {
            Path path = new Path();
            float radian = degree2Radian(36);
            float radius_in = (float) (radius * Math.sin(radian / 2) / Math.cos(radian));
            path.moveTo((float) (radius * Math.cos(radian / 2)), 0);
            path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in * Math.sin(radian)), (float) (radius - radius * Math.sin(radian / 2)));
            path.lineTo((float) (radius * Math.cos(radian / 2) * 2), (float) (radius - radius * Math.sin(radian / 2)));
            path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in * Math.cos(radian / 2)), (float) (radius + radius_in * Math.sin(radian / 2)));
            path.lineTo((float) (radius * Math.cos(radian / 2) + radius * Math.sin(radian)), (float) (radius + radius * Math.cos(radian)));
            path.lineTo((float) (radius * Math.cos(radian / 2)), (float) (radius + radius_in));
            path.lineTo((float) (radius * Math.cos(radian / 2) - radius * Math.sin(radian)), (float) (radius + radius * Math.cos(radian)));
            path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in * Math.cos(radian / 2)), (float) (radius + radius_in * Math.sin(radian / 2)));
            path.lineTo(0, (float) (radius - radius * Math.sin(radian / 2)));
            path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in * Math.sin(radian)), (float) (radius - radius * Math.sin(radian / 2)));
            path.close();
            canvas.drawPath(path, paint);
        } else if ("triangle".equals(shape_type)) {
            Path path = new Path();
            path.moveTo(0, 0);
            path.lineTo(diameter / 2, diameter);
            path.lineTo(diameter, 0);
            path.close();
            canvas.drawPath(path, paint);
        } else if ("heart".equals(shape_type)) {
            Path path = new Path();
            path.moveTo(diameter / 2, diameter / 5);
            path.quadTo(diameter, 0, diameter / 2, diameter / 1.0f);
            path.quadTo(0, 0, diameter / 2, diameter / 5);
            path.close();
            canvas.drawPath(path, paint);
        } else {
            canvas.drawCircle(scaledBitmap.getWidth() / 2, scaledBitmap.getHeight() / 2, scaledBitmap.getWidth() / 2, paint);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledBitmap, 0, 0, paint);
        bmp = null;
        squareBitmap = null;
        scaledBitmap = null;
        return outputbmp;
    }

    private float degree2Radian(int degree) {
        // TODO Auto-generated method stub
        return (float) (Math.PI * degree / 180);
    }

    private void drawCircleBorder(Canvas canvas, int radius, int color) {
        // TODO Auto-generated method stub
        Paint paint = new Paint();
        paint.setAntiAlias(true);// 抗锯齿
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(color);// 设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);// 设置画笔的style为STROKE：空心
        paint.setStrokeWidth(border_size);// 设置画笔的宽度
        // 画出空心圆，也就是边框
        canvas.drawCircle(width / 2, height / 2, radius, paint);
    }

}
