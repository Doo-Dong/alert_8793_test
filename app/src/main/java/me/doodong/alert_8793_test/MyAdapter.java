package me.doodong.alert_8793_test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<ListData> itemList;
    private Context context;
    private View.OnClickListener onClickItem;

    public MyAdapter(Context context, ArrayList<ListData> itemList, View.OnClickListener onClickItem) {
        this.context = context;
        this.itemList = itemList;
        this.onClickItem = onClickItem;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListData item = itemList.get(position);

        holder.textview.setText(item.text);

        holder.textview.setTextColor(Color.rgb(182, 182, 182));
        holder.distance.setTextColor(Color.rgb(182, 182, 182));

        holder.imageview.setBackgroundResource(item.image);
        holder.direction.setBackground(item.direct);
        holder.distance.setText(item.dist);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            for (Object payload : payloads) {
                if (payload instanceof String) {
                    String type = (String) payload;
                    if (TextUtils.equals(type, "click")) {
                        ListData item = itemList.get(position);

                        if (item.image == 0) {
                            Toast.makeText(context, "일정을 고르지 않았습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, item.text, Toast.LENGTH_SHORT).show();
                        }

                        holder.textview.setTextColor(Color.rgb(71, 214, 176));
                    }
                    if (TextUtils.equals(type, "init")) {
/*                        ListData item = itemList.get(position);
                        Toast.makeText(context, position + ":" + item.text, Toast.LENGTH_SHORT).show();*/
                        holder.textview.setTextColor(Color.rgb(182, 182, 182));
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textview;
        public ImageView imageview;
        public ImageView direction;
        public TextView distance;

        public ViewHolder(View itemView) {
            super(itemView);

            textview = itemView.findViewById(R.id.item_textview);
            imageview = itemView.findViewById(R.id.item_imageview);
            direction = itemView.findViewById(R.id.item_direction);
            distance = itemView.findViewById(R.id.item_distance);
        }
    }
}