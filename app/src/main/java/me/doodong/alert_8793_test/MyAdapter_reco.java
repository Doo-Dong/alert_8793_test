package me.doodong.alert_8793_test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

public class MyAdapter_reco extends RecyclerView.Adapter<MyAdapter_reco.ViewHolder> {

    private ArrayList<ListData> itemList;
    private Context context;
    private View.OnClickListener onClickItem;

    public MyAdapter_reco(Context context, ArrayList<ListData> itemList, View.OnClickListener onClickItem) {
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ListData item = itemList.get(position);

        holder.textview.setText(item.text);

        holder.textview.setTextColor(Color.rgb(182, 182, 182));
        holder.distance.setTextColor(Color.rgb(182, 182, 182));

        holder.imageview.setBackgroundResource(item.image);
        holder.direction.setBackground(item.direct);
        holder.distance.setText(item.dist);

        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                int test = item.image;  //resourceID
                String nametest = context.getResources().getResourceName(test);

                Log.d("test", String.valueOf(test));
                Log.d("testname", nametest);
                Intent intent = new Intent(context, MainPage_3.class);
                intent.putExtra("resourceName", nametest);
                context.startActivity(intent);

                //Toast.makeText(view.getContext(),"click", Toast.LENGTH_SHORT).show();
            }
        });
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
                        Toast.makeText(context, item.text, Toast.LENGTH_SHORT).show();
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