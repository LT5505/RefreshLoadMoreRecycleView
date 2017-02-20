# RefreshLoadMoreRecycleView
Welcome to RefreshLoadMoreRecyclerView
===================================


----------
今天研究了下RecyclerView的滑动事件，特别是下拉刷新和加载更多事件，在现在几乎所有的APP显示数据列表时都用到了。自定义RecyclerView下拉刷新和加载更多听上去很复杂，实际上并不难，只要是对滑动事件的监听和处理。

1、如何判断RecyclerView是在上滑还是下滑

在RecyclerView的OnScrollListener滑动事件监听中有个好用的方法，就是onScrolled(RecyclerView recyclerView, int dx, int dy)，其中根据dx的值的正负就可以判断是在左滑还是右滑，而根据dy的值就可以判断是在上滑还是下滑。

    //上滑
    if(dy>0){
    //相应操作代码
    } 
    //下滑
    else if(dy<0){
    //相应操作代码
    }

2、如何判断是否滑到了顶部或者底部

同样在RecyclerView的OnScrollListener滑动事件监听中onScrolled(RecyclerView recyclerView, int dx, int dy)方法中处理，根据canScrollVertically(int direction)来进行判断。

    //是否滑到底部
    if(!recyclerView.canScrollVertically(1)){
    //相应处理操作
    }
    //是否滑到顶部
    if(!recyclerView.canScrollVertically(-1)){
    //相应处理操作
    } 
    
3、自定义RecyclerView

知道了滑动事件的判断和处理，就可以很轻松得实现下拉刷新和加载更多了。

4、使用自定义RecyclerView
只要在Activity或者Fragement中实现滑动监听事件即可。

    public interface IOnScrollListener {
    void onRefresh();//刷新
    void onLoadMore();//加载更多
    void onLoaded();//加载完成
    }

5、效果图

![效果图](https://github.com/LT5505/RefreshLoadMoreRecycleView/blob/master/Screenshots/loading.gif)
