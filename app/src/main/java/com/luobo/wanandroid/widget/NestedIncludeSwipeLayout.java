package com.luobo.wanandroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.luobo.wanandroid.R;

public class NestedIncludeSwipeLayout extends FrameLayout implements NestedScrollingParent3 {

    private View searchView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private float searchHeight;//topBar高度

    public NestedIncludeSwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        searchView = findViewById(R.id.include);
        recyclerView = findViewById(R.id.aqRecyclerView);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        searchHeight = searchView.getMeasuredHeight();


    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {

    }

    /**
     * 一定要按照自己的需求返回true,则说明这个 Parent 想接收 nested scroll
     * 该方法决定了当前控件是否能接收到其内部View(非并非是直接子View)滑动时的参数；
     * 假设你只涉及到纵向滑动，这里可以根据nestedScrollAxes这个参数，进行纵向判断。
     *
     * @param child  布局中包含下面target的直接父View
     * @param target 发起嵌套滑动的NestedScrollingChild的View
     * @param axes   滑动方向
     * @param type
     * @return 返回NestedScrollingParent是否配合处理嵌套滑动
     */
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {

        return child.getId() == swipeRefreshLayout.getId() && axes == ViewCompat.SCROLL_AXIS_VERTICAL;

    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    /**
     * 如果 Parent 想在 Child 之前消费就在 onNestedPreScroll 方法里处理，否则就在 onNestedScroll 方法里，
     *
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     * @param type
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {

    }
}
