package me.doodong.alert_8793_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecommendationResult extends AppCompatActivity {
    private RecyclerView listview1;
    private RecyclerView listview2;
    private RecyclerView listview3;
    private MyAdapter_reco adapter1;
    private MyAdapter_reco adapter2;
    private MyAdapter_reco adapter3;
    String main_list0, main_list1, main_list2, main_list3, name;
    TextView tv_themeName;

    int[] list;
    Intent intent_send;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.BaseTheme);
        setContentView(R.layout.recommendation_result);
        tv_themeName =findViewById(R.id.tv_themeName);

        Intent intent = getIntent();

        main_list0 = intent.getStringExtra("main_list0");
        main_list1 = intent.getStringExtra("main_list1");
        main_list2 = intent.getStringExtra("main_list2");
        main_list3 = intent.getStringExtra("main_list3");

        if (main_list0 != null){
           // Toast.makeText(RecommendationResult.this, main_list0, Toast.LENGTH_SHORT).show();
            tv_themeName.setText("2박 3일 카페로드");
            name = "2박 3일 카페로드";
            setMain_list0();
            setMain_list1();
            setMain_list2();
        }else  if (main_list1 != null){
            //Toast.makeText(RecommendationResult.this, main_list1, Toast.LENGTH_SHORT).show();
            tv_themeName.setText("2박 3일 풍경로드");
            name = "2박 3일 풍경로드";
            setMain_list0();
            setMain_list1();
            setMain_list2();

        } else if (main_list2 != null){
            //Toast.makeText(RecommendationResult.this, main_list2, Toast.LENGTH_SHORT).show();
            tv_themeName.setText("2박 3일 마켓로드");
            name = "2박 3일 마켓로드";
            setMain_list0();
            setMain_list1();
            setMain_list2();

        } else if (main_list3 != null){
            //Toast.makeText(RecommendationResult.this, main_list3, Toast.LENGTH_SHORT).show();
            tv_themeName.setText("2박 3일 먹방로드");
            name = "2박 3일 먹방로드";
            setMain_list0();
            setMain_list1();
            setMain_list2();

        }

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
                //startActivity(intent);
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setMain_list0(){
        listview1 = findViewById(R.id.list_view_1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview1.setLayoutManager(layoutManager);

        ArrayList<ListData> itemList = new ArrayList<>();
        ListData listData = new ListData("오전", tv_themeName.getText().equals("2박 3일 카페로드") ?
                R.drawable.pic_7 : tv_themeName.getText().equals("2박 3일 마켓로드") ? R.drawable.pic_21:  R.drawable.pic_17,
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "1m");
        itemList.add(listData);
        listData = new ListData("점심",
                tv_themeName.getText().equals("2박 3일 카페로드") ? R.drawable.pic_47 :
                        tv_themeName.getText().equals("2박 3일 마켓로드") ?  R.drawable.pic_58 : R.drawable.pic_53,
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "25m");
        itemList.add(listData);
        listData = new ListData("오후",
                R.drawable.pic_10,
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "10m");
        itemList.add(listData);
        listData = new ListData("저녁",
                R.drawable.pic_49,
                getResources().getDrawable(getResources().getIdentifier("@drawable/dehaze", "id", this.getPackageName())),
                "");
        itemList.add(listData);

        adapter1 = new MyAdapter_reco(this, itemList, onClickItem);
        listview1.setAdapter(adapter1);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setMain_list1(){
        listview2 = findViewById(R.id.list_view_2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview2.setLayoutManager(layoutManager);

        ArrayList<ListData> itemList = new ArrayList<>();
        ListData listData = new ListData("오전",
                tv_themeName.getText().equals("2박 3일 카페로드") ? R.drawable.pic_5 :
                        tv_themeName.getText().equals("2박 3일 마켓로드") ?  R.drawable.pic_15 : R.drawable.pic_11,
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "1m");
        itemList.add(listData);
        listData = new ListData("점심",
                tv_themeName.getText().equals("2박 3일 카페로드") ? R.drawable.pic_62 :
                        tv_themeName.getText().equals("2박 3일 마켓로드") ?  R.drawable.pic_25 : R.drawable.pic_50,
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "25m");
        itemList.add(listData);
        listData = new ListData("오후",
                R.drawable.pic_14,
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "10m");
        itemList.add(listData);
        listData = new ListData("저녁",
                R.drawable.pic_64,
                getResources().getDrawable(getResources().getIdentifier("@drawable/dehaze", "id", this.getPackageName())),
                "");
        itemList.add(listData);

        adapter2 = new MyAdapter_reco(this, itemList, onClickItem);
        listview2.setAdapter(adapter2);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setMain_list2(){
        listview3 = findViewById(R.id.list_view_3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview3.setLayoutManager(layoutManager);

        ArrayList<ListData> itemList = new ArrayList<>();
        ListData listData = new ListData("오전",
                tv_themeName.getText().equals("2박 3일 카페로드") ? R.drawable.pic_36 :
                        tv_themeName.getText().equals("2박 3일 마켓로드") ? R.drawable.pic_23 : R.drawable.pic_24,
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "1m");
        itemList.add(listData);
        listData = new ListData("점심",
                tv_themeName.getText().equals("2박 3일 카페로드") ? R.drawable.pic_54 :
                        tv_themeName.getText().equals("2박 3일 마켓로드") ?  R.drawable.pic_66 :  R.drawable.pic_48,
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "25m");
        itemList.add(listData);
        listData = new ListData("오후",
                R.drawable.pic_38,
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "10m");
        itemList.add(listData);
        listData = new ListData("저녁",
                R.drawable.pic_69,
                getResources().getDrawable(getResources().getIdentifier("@drawable/dehaze", "id", this.getPackageName())),
                "");
        itemList.add(listData);

        adapter3 = new MyAdapter_reco(this, itemList, onClickItem);
        listview3.setAdapter(adapter3);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainPage_3.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    };

    public void  onClick_my_theme(View view) {
        if (tv_themeName.getText().equals("2박 3일 카페로드")) {
            list = new int[]{
                    R.drawable.pic_7, R.drawable.pic_10, R.drawable.pic_47, R.drawable.pic_49,
                    R.drawable.pic_5, R.drawable.pic_14, R.drawable.pic_62, R.drawable.pic_64,
                    R.drawable.pic_36, R.drawable.pic_38, R.drawable.pic_54, R.drawable.pic_69
            };
        } else if (tv_themeName.getText().equals("2박 3일 마켓로드")) {
            list = new int[]{
                    R.drawable.pic_21, R.drawable.pic_10, R.drawable.pic_58, R.drawable.pic_49,
                    R.drawable.pic_15, R.drawable.pic_14, R.drawable.pic_25, R.drawable.pic_64,
                    R.drawable.pic_23, R.drawable.pic_38, R.drawable.pic_66, R.drawable.pic_69
            };
        } else {
            list = new int[]{
                    R.drawable.pic_17, R.drawable.pic_10, R.drawable.pic_53, R.drawable.pic_49,
                    R.drawable.pic_11, R.drawable.pic_14, R.drawable.pic_50, R.drawable.pic_64,
                    R.drawable.pic_24, R.drawable.pic_38, R.drawable.pic_48, R.drawable.pic_69
            };
        }

        intent_send = new Intent(getApplicationContext(), my_page.class);
        intent_send.putExtra("list_spot", list);
        intent_send.putExtra("schedule_name", name);
        startActivity(intent_send);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}