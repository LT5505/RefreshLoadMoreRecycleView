package com.liuting.refreshloadmorelistview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Package:com.liuting.refreshloadmorelistview.adapter
 * author:liuting
 * Date:2017/2/16
 * Desc:列表Adapter
 */

public class RefreshLoadMoreRecycleAdapter extends RecyclerView.Adapter<RefreshLoadMoreRecycleAdapter.ViewHolder> {
    private List<String> list;
    private Context context;

    public RefreshLoadMoreRecycleAdapter(Context context,List<String> list) {
        this.context =context;
        this.list = list;
    }

    @Override
    public RefreshLoadMoreRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        RefreshLoadMoreRecycleAdapter.ViewHolder viewHolder = new RefreshLoadMoreRecycleAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(list.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text=(TextView)itemView.findViewById(android.R.id.text1);
        }
    }
}
