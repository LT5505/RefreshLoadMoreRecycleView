package com.liuting.library;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Package:com.liuting.library
 * author:liuting
 * Date:2017/2/14
 * Desc:自定义RecycleView，下拉刷新以及上拉加载更多
 */

public class RefreshLoadMoreRecycleView extends RecyclerView implements RecyclerView.OnTouchListener{
    private Boolean isLoadMore;//加载更多标志
    private Boolean isLoadEnd;//加载到最后的标志
    private Boolean isLoadStart;//顶部的标志
    private Boolean isRefresh;//下拉刷新标志
//    private int lastVisibleItem;//最后一项
    private IOnScrollListener listener;//事件监听
    private float mLastY;

    public RefreshLoadMoreRecycleView(Context context) {
        super(context);
        init(context);
    }

    public RefreshLoadMoreRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RefreshLoadMoreRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        isLoadEnd=false;
        isLoadStart =true;

        this.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //SCROLL_STATE_DRAGGING  和   SCROLL_STATE_IDLE 两种效果自己看着来
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //上滑
                if(dy>0){
                    //是否滑到底部
                    if(!recyclerView.canScrollVertically(1)){
                        isLoadEnd = true;
                    }else{
                        isLoadEnd = false;
                    }
                }else if(dy<0){
                    //是否滑到顶部
                    if(!recyclerView.canScrollVertically(-1)){
                        isLoadStart=true;
                    }else{
                        isLoadStart=false;
                    }

                }
            }
        });
        this.setOnTouchListener(this);
    }

    /**
     * 加载数据
     */
    private void loadData() {
        if (isLoadEnd) {
            // 判断是否已加载所有数据
            if (isLoadMore) {//未加载完所有数据，加载数据，并且还原isLoadEnd值为false，重新定位列表底部
                if (getListener() != null) {
                    getListener().onLoadMore();
                }
            } else {//加载完了所有的数据
                if(getListener()!=null){
                    getListener().onLoaded();
                }
            }
            isLoadEnd = false;
        } else if (isLoadStart) {
            if(isRefresh){
                if (getListener() != null) {
                    getListener().onRefresh();
                }
                isLoadStart=false;
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (mLastY == -1) {
            mLastY = motionEvent.getRawY();
        }
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_MOVE:
                final float deltaY = motionEvent.getRawY() - mLastY;
                mLastY = motionEvent.getRawY();
                if(deltaY<0){
                    //是否滑到底部
                    if(!this.canScrollVertically(1)){
                        isLoadEnd = true;
                    }else{
                        isLoadEnd = false;
                    }
                }else if(deltaY>0) {
                    //是否滑到顶部
                    if (!this.canScrollVertically(-1)) {
                        isLoadStart = true;
                    } else {
                        isLoadStart = false;
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                mLastY = motionEvent.getRawY();
                break;
            default://reset
                mLastY = -1;
                break;
        }

        //return true会屏蔽滑动事件
        return false;
    }

    /**
     * 滑动监听事件
     */
    public interface IOnScrollListener {
        void onRefresh();//刷新

        void onLoadMore();//加载更多

        void onLoaded();//加载完成
    }

    public IOnScrollListener getListener() {
        return listener;
    }

    public void setListener(IOnScrollListener listener) {
        this.listener = listener;
    }

    public Boolean getLoadMore() {
        return isLoadMore;
    }

    public void setLoadMoreEnable(Boolean loadMore) {
        isLoadMore = loadMore;
    }

    public Boolean getRefresh() {
        return isRefresh;
    }

    public void setRefreshEnable(Boolean refresh) {
        isRefresh = refresh;
    }
}
