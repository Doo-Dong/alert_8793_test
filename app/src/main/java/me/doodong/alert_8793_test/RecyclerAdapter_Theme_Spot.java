package me.doodong.alert_8793_test;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter_Theme_Spot extends RecyclerView.Adapter<RecyclerAdapter_Theme_Spot.ItemViewHolder>
{
    private ArrayList<Theme_Item_spot> mData;
    Context context;
    onClickInterface_Spot onClickInterface_Spot;
   ;



    public RecyclerAdapter_Theme_Spot(Context con, ArrayList<Theme_Item_spot> list, onClickInterface_Spot onClickItem){
        context = con;
        mData = list;
        onClickInterface_Spot = onClickItem;

    }


    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        private TextView tv1;

        public ItemViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            tv1 = itemView.findViewById(R.id.tv1);

        }

        public void setItem(Theme_Item_spot item) {
            imageView.setImageDrawable(item.getImage());
            tv1.setText(item.getTv1());
        }

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_theme_item_spot, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_Theme_Spot.ItemViewHolder holder, final int position) {
        Theme_Item_spot item = mData.get(position);
//
//        holder.imageView.setImageDrawable(item.getImage());
        //holder.setItem(item);
        holder.setItem(item);

        if (onClickInterface_Spot != null){
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickInterface_Spot.setClick(position);

                }
            });
            holder.tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickInterface_Spot.setClick(position);

                }
            });
        }

//        if(onClickInterface_Food == null){
//            Toast.makeText(activity, "오전과 오후 항목은" + "\n" + "관광지에서 선택해주세요.", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return mData.size();
    }

    public void addItem(Theme_Item_spot item){
        mData.add(item);
    }
    public void setItems(ArrayList<Theme_Item_spot> mData){
        this.mData = mData;
    }
    public Theme_Item_spot getItem(int position){
        return mData.get(position);
    }
    public void setItem(int position, Theme_Item_spot item){
        mData.set(position,item);
    }

    public void removeAllItem(){
        mData.clear();
        notifyDataSetChanged();
    }
}
