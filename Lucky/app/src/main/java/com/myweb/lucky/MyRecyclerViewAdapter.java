package com.myweb.lucky;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
        private List<Course> feedItemList;
        private Context mContext;
        private OnItemClickListener onItemClickListener;

    public MyRecyclerViewAdapter(Context context, List<Course> feedItemList) {
            this.feedItemList = feedItemList;
            this.mContext = context;
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.course_list_item, null);
            CustomViewHolder viewHolder = new CustomViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
            final Course course = feedItemList.get(i);


            customViewHolder.name.setText(course.getName());
            customViewHolder.price.setText(course.getPrice());
            Glide.with(mContext)
                    .load(course.getPic())
                    .into(customViewHolder.pic);

            //customViewHolder.name.setText(course.getName());
            //customViewHolder.price.setText(course.getPrice());
            //customViewHolder.name.setText(Html.fromHtml(course.getName()));
            //customViewHolder.price.setText(Html.fromHtml(course.getPrice()));

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(course);
                }
            };
            customViewHolder.pic.setOnClickListener(listener);
            customViewHolder.name.setOnClickListener(listener);
            customViewHolder.price.setOnClickListener(listener);
        }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

        @Override
        public int getItemCount() {
            return (null != feedItemList ? feedItemList.size() : 0);
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {
            protected ImageView pic;
            protected TextView name,price;

            public CustomViewHolder(View view) {
                super(view);
                this.pic = (ImageView) view.findViewById(R.id.pic);
                this.name = (TextView) view.findViewById(R.id.name);
                this.price = (TextView) view.findViewById(R.id.price);
            }
        }
}
