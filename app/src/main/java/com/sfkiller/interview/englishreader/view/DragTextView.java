package com.sfkiller.interview.englishreader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

/**
 * 拖动的TextView
 *
 * Created by SFKiller on 2017/8/26.
 */
public class DragTextView extends TextView {

    private int lastX = 0;
    private int lastY = 0;

    private static final int screenWidth = 720;
    private static final int screenHeight = 1280;


    public DragTextView(Context context) {
        super(context, null);
    }

    public DragTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx =(int)event.getRawX() - lastX;
                int dy =(int)event.getRawY() - lastY;

                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = getRight() + dx;
                int bottom = getBottom() + dy;
                if(left < 0){
                    left = 0;
                    right = left + getWidth();
                }
                if(right > screenWidth){
                    right = screenWidth;
                    left = right - getWidth();
                }
                if(top < 0){
                    top = 0;
                    bottom = top + getHeight();
                }
                if(bottom > screenHeight){
                    bottom = screenHeight;
                    top = bottom - getHeight();
                }
                layout(left, top, right, bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.8f,0.0f);
                alphaAnimation.setDuration(1000);
                setAnimation(alphaAnimation);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener(){

                    public void onAnimationStart(Animation animation){}

                    public void onAnimationRepeat(Animation animation){}

                    public void onAnimationEnd(Animation animation) {
                        setVisibility(View.GONE);
                    }
                });
                break;
            default:
                break;
        }
        return true;
    }
}
