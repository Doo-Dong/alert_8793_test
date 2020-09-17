package me.doodong.alert_8793_test;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter_Theme extends RecyclerView.Adapter<RecyclerAdapter_Theme.ItemViewHolder> {
    private ArrayList<Theme_Item> mData;
    Context context;
    onClickInterface_Food onClickInterface_food;

    public RecyclerAdapter_Theme(Context con, ArrayList<Theme_Item> list, onClickInterface_Food onClickItem){
        context = con;
        mData = list;
        onClickInterface_food = onClickItem;

    }


    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tv1,tv2;

        public ItemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);

        }

        public void setItem(Theme_Item item) {
            imageView.setImageDrawable(item.getImage());
            tv1.setText(item.getTv1());
            tv2.setText(item.getTv2());
        }

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_theme_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {

        Theme_Item item = mData.get(position);
        holder.setItem(item);

//        holder.imageView.setImageDrawable(item.getImage());
//        holder.tv1.setText(item.getTv1());
//        holder.tv2.setText(item.getTv2());

        if (onClickInterface_food != null){

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickInterface_food.setClick(position);

                }
            });
            holder.tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickInterface_food.setClick(position);

                }
            });
            holder.tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickInterface_food.setClick(position);

                }
            });

        }

//        if(onClickInterface_spot == null){
//            Toast.makeText(activity, "점심과 저녁 항목은" + "\n" + "음식점에서 선택해주세요.", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return mData.size();
    }

    public void addItem(Theme_Item item){
        mData.add(item);
    }
    public void setItems(ArrayList<Theme_Item> mData){
        this.mData = mData;
    }
    public Theme_Item getItem(int position){
        return mData.get(position);
    }
    public void setItem(int position, Theme_Item item){
        mData.set(position,item);
    }

}
