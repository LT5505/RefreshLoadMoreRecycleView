package com.liuting.refreshloadmorelistview.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.liuting.library.RefreshLoadMoreRecycleView;
import com.liuting.refreshloadmorelistview.R;
import com.liuting.refreshloadmorelistview.adapter.RefreshLoadMoreRecycleAdapter;
import com.liuting.refreshloadmorelistview.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Package:com.liuting.refreshloadmorelistview
 * author:liuting
 * Date:2017/2/20
 * Desc:下拉刷新加载更多列表
 */

public class RefreshLoadMoreListActivity extends Activity implements RefreshLoadMoreRecycleView.IOnScrollListener{
    private RefreshLoadMoreRecycleView recycleView;//下拉刷新RecycleView
    private List<String> list;//列表
    private RefreshLoadMoreRecycleAdapter adapter;//Adapter
    private ProgressDialog dialog;//提示框
    private static final int REFRESH_LOAD=0;//下拉刷新
    private static final int MORE_LOAD=1;//加载更多
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REFRESH_LOAD:
                    recycleView.setLoadMoreEnable(true);
                    dismissDialog();
                    if(list!=null){
                        list.clear();
                    }
                    loadData();
                    adapter.notifyDataSetChanged();
                    break;
                case MORE_LOAD:
                    recycleView.setLoadMoreEnable(false);
                    dismissDialog();
                    loadData();
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);
        initView();
    }

    /**
     * 初始化
     */
    public void initView(){
        dialog = new ProgressDialog(RefreshLoadMoreListActivity.this);

        list=new ArrayList<>();
        loadData();
        recycleView = (RefreshLoadMoreRecycleView)findViewById(R.id.list_recycle_view_data);
        recycleView.addItemDecoration(new SpacesItemDecoration(8));
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RefreshLoadMoreListActivity.this);
        recycleView.setLayoutManager(linearLayoutManager);
        adapter = new RefreshLoadMoreRecycleAdapter(RefreshLoadMoreListActivity.this,list);
        recycleView.setAdapter(adapter);
        recycleView.setListener(this);
        recycleView.setRefreshEnable(true);
        recycleView.setLoadMoreEnable(true);
    }

    /**
     * 加载数据
     */
    public void loadData(){
        for(int i=0;i<10;i++ ){
            list.add("It is "+i);
        }
    }

    @Override
    public void onRefresh() {
        showDialog();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(5000);
                    handler.sendEmptyMessage(REFRESH_LOAD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Log.e("TAG","Running--->>Refresh");
    }

    @Override
    public void onLoadMore() {
        showDialog();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(5000);
                    handler.sendEmptyMessage(MORE_LOAD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Log.e("TAG","Running--->>LoadMore");
    }

    @Override
    public void onLoaded() {
        Toast.makeText(RefreshLoadMoreListActivity.this, R.string.loaded_all,Toast.LENGTH_SHORT).show();
    }

    /**
     * dismiss dialog
     */
    private void dismissDialog(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }

    /**
     * show dialog
     */
    private void showDialog(){
        if (dialog!=null&&!dialog.isShowing()){
            dialog.show();
        }
    }
}
