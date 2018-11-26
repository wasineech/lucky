package com.myweb.lucky;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AppRecyclerViewAdapter extends RecyclerView.Adapter<AppRecyclerViewAdapter.AppViewHolder> {
    private List<Application> feedItemList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public AppRecyclerViewAdapter(Context context, List<Application> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public AppRecyclerViewAdapter.AppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.application_list_item, null);
        AppRecyclerViewAdapter.AppViewHolder viewHolder = new AppRecyclerViewAdapter.AppViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AppRecyclerViewAdapter.AppViewHolder AppViewHolder, int i) {
        final Application application = feedItemList.get(i);

        AppViewHolder.app_id.setText(application.getId_application());
        AppViewHolder.status.setText(application.getState());


        //customViewHolder.name.setText(course.getName());
        //customViewHolder.price.setText(course.getPrice());
        //customViewHolder.name.setText(Html.fromHtml(course.getName()));
        //customViewHolder.price.setText(Html.fromHtml(course.getPrice()));

        /*View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener1.onItemClick(application);
            }
        };
        customViewHolder.pic.setOnClickListener(listener);
        customViewHolder.name.setOnClickListener(listener);
        customViewHolder.price.setOnClickListener(listener);*/
    }
/*
    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    */

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        protected TextView app_id,status;

        public AppViewHolder(View view) {
            super(view);
            this.app_id = (TextView) view.findViewById(R.id.appId);
            this.status = (TextView) view.findViewById(R.id.status);
        }
    }
}
