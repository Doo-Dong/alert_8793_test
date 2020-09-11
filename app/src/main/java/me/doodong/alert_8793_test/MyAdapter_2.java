package me.doodong.alert_8793_test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_2 extends RecyclerView.Adapter<MyAdapter_2.ViewHolder> {

    private ArrayList<ListData_2> itemList;
    private Context context;
    private View.OnClickListener onClickItem;

    public MyAdapter_2(Context context, ArrayList<ListData_2> itemList, View.OnClickListener onClickItem) {
        this.context = context;
        this.itemList = itemList;
        this.onClickItem = onClickItem;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_view_item_main2, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListData_2 item = itemList.get(position);

        holder.textview.setTag(item.position);

        holder.textview.setText(item.main);
        holder.imageview.setBackground(item.image);
        holder.distance.setText(item.sub);
        holder.textview.setOnClickListener(onClickItem);
        holder.imageview.setOnClickListener(onClickItem);
        holder.distance.setOnClickListener(onClickItem);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textview;
        public ImageView imageview;
        public TextView distance;

        public ViewHolder(View itemView) {
            super(itemView);

            textview = itemView.findViewById(R.id.item_main_text);
            imageview = itemView.findViewById(R.id.item_imageview);
            distance = itemView.findViewById(R.id.item_sub_text);
        }
    }
}