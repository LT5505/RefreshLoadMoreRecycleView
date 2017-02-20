package com.liuting.refreshloadmorelistview.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Package:com.liuting.refreshloadmorelistview.widget
 * author:liuting
 * Date:2017/2/20
 * Desc:
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;

        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1)
//            outRect.top = space;
    }
}
