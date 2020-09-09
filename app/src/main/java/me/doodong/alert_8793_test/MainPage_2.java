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
import android.widget.TextView;

import java.security.cert.CertPathValidatorException;
import java.util.ArrayList;

public class MainPage_2 extends AppCompatActivity {
    private RecyclerView listview;
    private MyAdapter_2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.BaseTheme);
        setContentView(R.layout.main_page_2);

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
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        init1();
        init2();
    }

    private void init1() {
        listview = findViewById(R.id.list_view_tour);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listview.setLayoutManager(layoutManager);

        ArrayList<ListData_2> itemList = new ArrayList<>();
        ListData_2 listData = new ListData_2("치앙마이의 상징이된 황금사원",
                getResources().getDrawable(getResources().getIdentifier("@drawable/temp", "id", this.getPackageName())),
                "왓 프라탓 도이캄");
        itemList.add(listData);
        listData = new ListData_2("SNS속 인증샷의 메카, 바로 그곳!",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_1", "id", this.getPackageName())),
                "NO.39 CAFE");
        itemList.add(listData);

        adapter = new MyAdapter_2(this, itemList, onClickItem);
        listview.setAdapter(adapter);
    }

    private void init2() {
        listview = findViewById(R.id.list_view_store);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listview.setLayoutManager(layoutManager);

        ArrayList<ListData_2> itemList = new ArrayList<>();
        ListData_2 listData = new ListData_2("입이 즐거운 먹거리",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_2", "id", this.getPackageName())),
                "TV에도 나온 그 맛집");
        itemList.add(listData);
        listData = new ListData_2("드넓게 펼쳐진 뷰 맛집!",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_3", "id", this.getPackageName())),
                "푸핀 테라스");
        itemList.add(listData);
        listData = new ListData_2("황홀한 분위기, 멋진 조명의 레스토랑",
                getResources().getDrawable(getResources().getIdentifier("@drawable/view_page_4", "id", this.getPackageName())),
                "생생정보통 단골 출연 맛집");
        itemList.add(listData);

        adapter = new MyAdapter_2(this, itemList, onClickItem);
        listview.setAdapter(adapter);
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

class ListData_2 {
    String main;
    Drawable image;
    String sub;

    ListData_2(String text, Drawable image, String dist) {
        this.main = text;
        this.image = image;
        this.sub = dist;
    }
}