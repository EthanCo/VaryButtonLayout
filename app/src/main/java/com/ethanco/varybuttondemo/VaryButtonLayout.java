package com.ethanco.varybuttondemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by YOLANDA on 2015-11-09.
 */
public class VaryButtonLayout extends RelativeLayout implements View.OnClickListener {

    int currIndex = 0;
    private boolean isChild;

    public VaryButtonLayout(Context context) {
        super(context);
        init();
    }

    public VaryButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VaryButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    /**
     * 设置现在的状态
     *
     * @param currIndex
     */
    public void setCurrSatus(int currIndex) {
        if (currIndex < 0 || currIndex >= getChildCount()) {
            throw new IndexOutOfBoundsException("length=" + getChildCount() + "; index=" + currIndex);
        }
        this.currIndex = currIndex;
        getChildAt(currIndex).setVisibility(VISIBLE);
    }

    public int getCurrSatus() {
        return currIndex;
    }

    /**
     * 添加一种状态
     *
     * @param res
     */
    public void addStatusView(@LayoutRes int res) {
        View v = LayoutInflater.from(getContext()).inflate(res, this, false);
        addStatusView(v);
    }

    /**
     * 添加一种状态
     *
     * @param v
     */
    public void addStatusView(View v) {
        addView(v);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();
        if (childCount == 1 && getChildAt(0) instanceof ViewGroup) {
            isChild = true;
            ViewGroup viewGroup = (ViewGroup) getChildAt(0);
            initChildView(viewGroup, widthMeasureSpec, heightMeasureSpec);
        } else {
            initChildView(this, widthMeasureSpec, heightMeasureSpec);
        }
    }

    private void initChildView(ViewGroup rootViewGroup, int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = rootViewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = rootViewGroup.getChildAt(i);
            fillParent(childView, widthMeasureSpec, heightMeasureSpec);
            childView.setOnClickListener(this);
            childView.setDuplicateParentStateEnabled(true);
            if (i != currIndex) {
                childView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 将子布局大小填充满父布局
     *
     * @param childView
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    private void fillParent(View childView, int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();

        float weight = 0;
        int parentOrientation = -1;
        //如果父布局是LinearLayout，进行weight的处理
        if (getParent() != null && getParent() instanceof LinearLayout) {
            LinearLayout parentLayout = (LinearLayout) getParent();
            parentOrientation = parentLayout.getOrientation();
            LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) this.getLayoutParams();
            weight = ll.weight;
        }

        Log.i("VaryButton", "2015-11-09-fillParent: weight:" + weight);
        if (specWidthMode != MeasureSpec.AT_MOST) {
            if (weight == 0 && parentOrientation != LinearLayout.HORIZONTAL) {
                layoutParams.width = specWidth;
            }
        }
        if (specHeightMode != MeasureSpec.AT_MOST) {
            if (weight == 0 && parentOrientation != LinearLayout.VERTICAL) {
                layoutParams.height = specHeight;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void onClick(View v) {
        Log.i("VaryButton", "2015-11-09-onClick: onCLick");
        //getChildAt(currIndex).setVisibility(View.GONE);
        if (isChild) {
            ViewGroup childViewGroup = (ViewGroup) getChildAt(0);
            childViewGroup.getChildAt(circulateIndex(childViewGroup.getChildCount())).setVisibility(View.VISIBLE);
        } else {
            getChildAt(circulateIndex(getChildCount())).setVisibility(View.VISIBLE);
        }

        mVarayClickListener.onClick(this, currIndex);
    }

    /**
     * 获得下一个Index
     *
     * @return
     */
    private int circulateIndex(int maxIndex) {
        currIndex++;
        if (currIndex >= maxIndex)
            currIndex = 0;
        return currIndex;
    }

    public interface OnVaryClickListener {
        void onClick(View v, int currIndex);

    }

    public void setOnVarayClickListener(OnVaryClickListener varyClickListener) {
        this.mVarayClickListener = varyClickListener;
    }

    public OnVaryClickListener mVarayClickListener = new OnVaryClickListener() {
        @Override
        public void onClick(View v, int currIndex) {

        }
    };
}
