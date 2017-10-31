package com.erickogi14gmail.kale.Search;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Eric on 9/19/2017.
 */

public class CustomViewPager extends ViewPager {
    public boolean isPagingEnabled=true;
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled&& super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return  this.isPagingEnabled&&super.onInterceptTouchEvent(ev);
    }
    public  void setPagingEnabled(boolean b){
        this.isPagingEnabled=b;
    }
}
