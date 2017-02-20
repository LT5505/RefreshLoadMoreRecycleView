package com.liuting.refreshloadmorelistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnList;//列表
    private Button btnGrid;//网格列表
    private static final String LIST_ACTION="com.liuting.refreshloadmorelistview.LIST_ACTION";
    private static final String GRID_ACTION="com.liuting.refreshloadmorelistview.GRID_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化控件
     */
    public void initView(){
        btnList = (Button) findViewById(R.id.main_btn_list);
        btnGrid = (Button) findViewById(R.id.main_btn_grid);
        btnList.setOnClickListener(this);
        btnGrid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_btn_list:
                startActivity(new Intent(LIST_ACTION));
                break;
            case R.id.main_btn_grid:
                startActivity(new Intent(GRID_ACTION));
                break;
        }
    }
}
