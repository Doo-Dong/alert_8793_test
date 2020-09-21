package me.doodong.alert_8793_test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class my_page extends AppCompatActivity {
    private RecyclerView listview1;
    private RecyclerView listview2;
    private RecyclerView listview3;
    private MyAdapter adapter1;
    private MyAdapter adapter2;
    private MyAdapter adapter3;

    Drawable day1_img1, day1_img2, day1_img3, day1_img4
            ,day2_img1, day2_img2, day2_img3, day2_img4
            ,day3_img1, day3_img2, day3_img3, day3_img4;

    int [] list;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.BaseTheme);
        setContentView(R.layout.my_page);

        // 전체화면인 DrawerLayout 객체 참조
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        // Drawer 화면(뷰) 객체 참조
        final View drawerView = findViewById(R.id.drawer);

        // 드로어 버튼 객체 참조
        Button btnOpenDrawer = findViewById(R.id.drawerLayout_Btn);
        Button btnCloseDrawer = findViewById(R.id.drawerLayout_Btn_close);
        TextView btnList_main = findViewById(R.id.drawerLayout_list_btn_1);
        TextView btnList_thai = findViewById(R.id.drawerLayout_list_btn_thai);
        TextView btnList_jap = findViewById(R.id.drawerLayout_list_btn_jap);
        TextView btnList_airInfo = findViewById(R.id.drawerLayout_list_btn_2);
        TextView btnList_myPage = findViewById(R.id.drawerLayout_list_btn_3);

        Intent intent = getIntent();
        list = intent.getIntArrayExtra("list_spot");

        if (intent != null){
            if (list != null){
                //Toast.makeText(my_page.this, "intent success"+ list[0]+ list[1]+ list[2]+ list[3], Toast.LENGTH_SHORT).show();

            }else {
                    list = new int[]{R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray,
                            R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray,
                            R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray};
            }
        }

        // 바텀 버튼 객체 참조
        Button mypg_bottom_btn_1 = findViewById(R.id.mypg_bottom_btn_1);
        Button mypg_bottom_btn_2 = findViewById(R.id.mypg_bottom_btn_2);
        Button mypg_bottom_btn_3 = findViewById(R.id.mypg_bottom_btn_3);

        // 일정 시작 -> 일정들 애니메이션 & 하이라이트
        mypg_bottom_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int speedScroll = 2000;
                final Handler handler = new Handler();
                final Runnable runnable = new Runnable() {
                    MyAdapter adapter = adapter1;
                    int count = 0;
                    int phase = 1;
                    boolean flag = true;
                    @Override
                    public void run() {
                        if(count <= adapter.getItemCount()){
                            if(count == adapter.getItemCount()){
                                flag = false;
                            }else if(count == 0){
                                flag = true;
                            }

                            if(flag) {
                                adapter.notifyItemChanged(count, "click");
                                count++;
                            }
                            else {
                                adapter.notifyItemChanged(count, "click");
                                count=0;
                                phase++;
                            }

                            if(phase==1){
                                listview1.smoothScrollToPosition(count);
                                listview2.smoothScrollToPosition(0);
                                listview3.smoothScrollToPosition(0);
                            }
                            else if(phase==2){
                                adapter = adapter2;
                                listview1.smoothScrollToPosition(0);
                                listview2.smoothScrollToPosition(count);
                                listview3.smoothScrollToPosition(0);
                            }
                            else if(phase==3){
                                adapter = adapter3;
                                listview1.smoothScrollToPosition(0);
                                listview2.smoothScrollToPosition(0);
                                listview3.smoothScrollToPosition(count);
                            }
                            else if(phase==4){
                                listview1.smoothScrollToPosition(0);
                                listview2.smoothScrollToPosition(0);
                                listview3.smoothScrollToPosition(0);
                                adapter1.notifyItemRangeChanged(0,4, "init");
                                adapter2.notifyItemRangeChanged(0,4, "init");
                                adapter3.notifyItemRangeChanged(0,4, "init");
                                count = adapter.getItemCount() + 1;
                            }
                            handler.postDelayed(this,speedScroll);
                        }
                    }
                };
                handler.postDelayed(runnable,speedScroll);
            }
        });

        // 일정 공유 -> 메인1 친구테마에 변경점 주기
        mypg_bottom_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        // 전체 일정 수정 -> 나만테로 이동해야함
        mypg_bottom_btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Theme.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        // 드로어 여는 버튼 리스너
        btnOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        // 드로어 닫는 버튼 리스너
        btnCloseDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
            }
        });

        // 메인으로 가는 버튼 리스너
        btnList_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AfterLoginActivity.class);
                drawerLayout.closeDrawer(drawerView);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        // 태국
        btnList_thai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                intent.putExtra("country","태국");
                drawerLayout.closeDrawer(drawerView);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        // 일본
        btnList_jap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                intent.putExtra("country","일본");
                drawerLayout.closeDrawer(drawerView);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        // 공항정보로 가는 버튼 리스너
        btnList_airInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), air_info.class);
                drawerLayout.closeDrawer(drawerView);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        // 마이페이지로 가는 버튼 리스너
        btnList_myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), my_page.class);
                drawerLayout.closeDrawer(drawerView);
                //startActivity(intent);
            }
        });

        init1();
        init2();
        init3();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init1() {
        listview1 = findViewById(R.id.list_view_1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview1.setLayoutManager(layoutManager);
        ArrayList<ListData> itemList = new ArrayList<>();
        ListData listData = new ListData("오전",
                getDrawable(list[0]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "10m");
        itemList.add(listData);
        listData = new ListData("점심",
                getDrawable(list[2]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "5m");
        itemList.add(listData);
        listData = new ListData("오후",
                getDrawable(list[1]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "2m");
        itemList.add(listData);
        listData = new ListData("저녁",
                getDrawable(list[3])
                ,
                getResources().getDrawable(getResources().getIdentifier("@drawable/dehaze", "id", this.getPackageName())),
                "");
        itemList.add(listData);

        adapter1 = new MyAdapter(this, itemList, onClickItem);
        listview1.setAdapter(adapter1);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init2() {
        listview2 = findViewById(R.id.list_view_2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview2.setLayoutManager(layoutManager);

        ArrayList<ListData> itemList = new ArrayList<>();
        ListData listData = new ListData("오전",
                getDrawable(list[4]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "1m");
        itemList.add(listData);
        listData = new ListData("점심",
                getDrawable(list[6]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "25m");
        itemList.add(listData);
        listData = new ListData("오후",
                getDrawable(list[5]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "5m");
        itemList.add(listData);
        listData = new ListData("저녁",
                getDrawable(list[7]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/dehaze", "id", this.getPackageName())),
                "");
        itemList.add(listData);

        adapter2 = new MyAdapter(this, itemList, onClickItem);
        listview2.setAdapter(adapter2);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init3() {
        listview3 = findViewById(R.id.list_view_3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview3.setLayoutManager(layoutManager);

        ArrayList<ListData> itemList = new ArrayList<>();
        ListData listData = new ListData("오전",
                getDrawable(list[8]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "1m");
        itemList.add(listData);
        listData = new ListData("점심",
                getDrawable(list[10]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "25m");
        itemList.add(listData);
        listData = new ListData("오후",
                getDrawable(list[9]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "10m");
        itemList.add(listData);
        listData = new ListData("저녁",
                getDrawable(list[11]),
                getResources().getDrawable(getResources().getIdentifier("@drawable/dehaze", "id", this.getPackageName())),
                "");
        itemList.add(listData);

        adapter3 = new MyAdapter(this, itemList, onClickItem);
        listview3.setAdapter(adapter3);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainPage_3.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
    };


}

class ListData {
    String text;
    Drawable image;
    Drawable direct;
    String dist;

    ListData(String text, Drawable image, Drawable direct, String dist) {
        this.text = text;
        this.image = image;
        this.direct = direct;
        this.dist = dist;
    }
}