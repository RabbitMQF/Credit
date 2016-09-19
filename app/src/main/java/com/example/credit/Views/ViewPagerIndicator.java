package com.example.credit.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by alucard on 2016-05-24.
 */
public class ViewPagerIndicator extends LinearLayout {
    private Paint mPaint;//画笔
    private Path mPath;//三角形类
    private int mTriangleWidth;//三角形宽
    private int mTriangleHeight;//三角型高
    private static final float RADIO_TRIANGLE_WIDTH = 1 / 6F;//三角形底部宽度
    private int mInitTranslationX;//初始位置
    private int mTranslationX;//移动时位置

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        mPaint=new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.parseColor("#EEEEEE"));
        mPaint.setStyle(Paint.Style.FILL);
        //mPaint.setPathEffect(new CornerPathEffect(3));//圆角
    }

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mInitTranslationX+mTranslationX,getHeight()+4);
        canvas.drawPath(mPath,mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w / 3 * RADIO_TRIANGLE_WIDTH);
        mInitTranslationX=w/2/2-mTriangleWidth/2;
        initTriangle();
    }

    /**
     *初始化三角形
     */
    private void initTriangle() {
        mTriangleHeight=mTriangleWidth/2;
        mPath=new Path();
        mPath.moveTo(0,0);
        mPath.lineTo(mTriangleWidth,0);
        mPath.lineTo(mTriangleWidth/2,-mTriangleHeight);
        mPath.close();
    }

    /**
     * 指示器跟随手指进行滚动
     * @param position
     * @param positionOffset
     */
    public void scroll(int position, float positionOffset) {
        int tabWidth =getWidth()/2;
        mTranslationX= (int) (tabWidth*(positionOffset+position));
        invalidate();//重绘
    }

    private ViewPager mViewPager;
    public interface PageOnchangeListener{

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);


        public void onPageSelected(int position);


        public void onPageScrollStateChanged(int state);
    }
    public PageOnchangeListener mListener;
    public void setOnPageChangeListener(PageOnchangeListener listener){
        this.mListener=listener;
    }

    public void setViewPager(ViewPager viewPager,int pos){
        setItemClickEvent();
        mViewPager=viewPager;
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //tbaWidth*positionOffset+position*tbaWidth
                scroll(position,positionOffset);
                if(mListener!=null){
                    mListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
               if(mListener!=null){
                   mListener.onPageSelected(position);
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(mListener!=null){
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });
        mViewPager.setCurrentItem(pos);
    }



    /**
     * tab点击事件
     */
    private void setItemClickEvent(){
        int cCount=getChildCount();
        for(int i=0;i<cCount;i++){
            final  int j=i;
         View view =getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    mViewPager.setCurrentItem(j);
                }
            });
        }

    }




}
