package me.doodong.alert_8793_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class my_page extends AppCompatActivity {
    private RecyclerView listview;
    private MyAdapter adapter;

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
        Button btnList_main = findViewById(R.id.drawerLayout_list_btn_1);
        Button btnList_airInfo = findViewById(R.id.drawerLayout_list_btn_2);
        Button btnList_myPage = findViewById(R.id.drawerLayout_list_btn_3);

        // 바텀 버튼 객체 참조
        Button mypg_bottom_btn_1 = findViewById(R.id.mypg_bottom_btn_1);
        Button mypg_bottom_btn_2 = findViewById(R.id.mypg_bottom_btn_2);
        Button mypg_bottom_btn_3 = findViewById(R.id.mypg_bottom_btn_3);

        // 일정 공유 -> 단순 토스트 메시지
        mypg_bottom_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(my_page.this, "일정이 공유되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 전체 일정 수정 -> 나만테로 이동해야함
        mypg_bottom_btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Theme.class);
                startActivity(intent);
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
                Intent intent = new Intent(getApplicationContext(), Main.class);
                drawerLayout.closeDrawer(drawerView);
                startActivity(intent);
            }
        });

        // 공항정보로 가는 버튼 리스너
        btnList_airInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), air_info.class);
                drawerLayout.closeDrawer(drawerView);
                startActivity(intent);
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

        Intent intent = getIntent();
    }

    private void init1() {
        listview = findViewById(R.id.list_view_1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview.setLayoutManager(layoutManager);

        ArrayList<ListData> itemList = new ArrayList<>();
        ListData listData = new ListData("오전",
                getResources().getDrawable(getResources().getIdentifier("@drawable/temp", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "10m");
        itemList.add(listData);
        listData = new ListData("점심",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_1", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "5m");
        itemList.add(listData);
        listData = new ListData("오후",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_2", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "2m");
        itemList.add(listData);
        listData = new ListData("저녁",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_3", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/dehaze", "id", this.getPackageName())),
                "");
        itemList.add(listData);

        adapter = new MyAdapter(this, itemList, onClickItem);
        listview.setAdapter(adapter);
    }

    private void init2() {
        listview = findViewById(R.id.list_view_2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview.setLayoutManager(layoutManager);

        ArrayList<ListData> itemList = new ArrayList<>();
        ListData listData = new ListData("오전",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_2", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "1m");
        itemList.add(listData);
        listData = new ListData("점심",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_3", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "3m");
        itemList.add(listData);
        listData = new ListData("오후",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_1", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "5m");
        itemList.add(listData);
        listData = new ListData("저녁",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_2", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/dehaze", "id", this.getPackageName())),
                "");
        itemList.add(listData);

        adapter = new MyAdapter(this, itemList, onClickItem);
        listview.setAdapter(adapter);
    }

    private void init3() {
        listview = findViewById(R.id.list_view_3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview.setLayoutManager(layoutManager);

        ArrayList<ListData> itemList = new ArrayList<>();
        ListData listData = new ListData("오전",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_3", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "1m");
        itemList.add(listData);
        listData = new ListData("점심",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_2", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "25m");
        itemList.add(listData);
        listData = new ListData("오후",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_1", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "10m");
        itemList.add(listData);
        listData = new ListData("저녁",
                getResources().getDrawable(getResources().getIdentifier("@drawable/temp", "id", this.getPackageName())),
                getResources().getDrawable(getResources().getIdentifier("@drawable/dehaze", "id", this.getPackageName())),
                "");
        itemList.add(listData);

        adapter = new MyAdapter(this, itemList, onClickItem);
        listview.setAdapter(adapter);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            //Toast.makeText(my_page.this, str, Toast.LENGTH_SHORT).show();
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