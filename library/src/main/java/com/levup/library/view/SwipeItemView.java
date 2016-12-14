package com.levup.library.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

/**
 * Created by java on 14.12.2016.
 */

public class SwipeItemView extends FrameLayout
        implements GestureDetector.OnGestureListener {

    private View mTopView;
    private View mBottomView;

    private GestureDetectorCompat mGestureDetectorCompat;

    public SwipeItemView(Context context) {
        super(context);
        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), this);
    }

    public SwipeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTopView = new View(getContext());
        mBottomView = new View(getContext());

        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        mTopView.setLayoutParams(params);
        mBottomView.setLayoutParams(params);

        ViewCompat.setBackground(mTopView, new ColorDrawable(Color.RED));
        ViewCompat.setBackground(mBottomView, new ColorDrawable(Color.GREEN));

        addView(mBottomView);
        addView(mTopView);


        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), this);
    }

    private boolean isLeftDirection = false;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetectorCompat.onTouchEvent(ev);
        if(ev.getAction() == MotionEvent.ACTION_UP) {
           if(isLeftDirection) {
               goToLeft();
           } else {
               goToRight();
           }
        }
        return super.dispatchTouchEvent(ev);
    }

    private void goToRight() {
        mTopView.animate().x(mTopView.getMeasuredWidth() / 2).setDuration(300).start();
    }

    private void goToLeft() {
        mTopView.animate().x(0).setDuration(300).start();
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(SwipeItemView.class.getSimpleName(), "onScroll");

        Log.d(SwipeItemView.class.getSimpleName(),
                "startX " + motionEvent.getX() + " currentX: " + motionEvent1.getX());


        if(motionEvent1.getX() < (mTopView.getMeasuredWidth() / 2)) {
            mTopView.setX(motionEvent1.getX());
        }

        isLeftDirection = (motionEvent1.getX() - motionEvent.getX()) < 0;

        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
