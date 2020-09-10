package me.doodong.alert_8793_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertPathValidatorException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class MainPage_2 extends AppCompatActivity {
    private RecyclerView listview1;
    private RecyclerView listview2;
    private MyAdapter_2 adapter;
    Workbook wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.BaseTheme);
        setContentView(R.layout.main_page_2);

        try {
            InputStream is = getBaseContext().getResources().getAssets().open("inform_chiangmai.xls");
            wb = Workbook.getWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
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
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        init1();
        init2();
    }

    private void init1() {
        listview1 = findViewById(R.id.list_view_tour);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listview1.setLayoutManager(layoutManager);

        ArrayList<ListData_2> itemList = new ArrayList<>();

        if(wb != null) {
            Sheet sheet = wb.getSheet(0);   // 시트 불러오기
            if(sheet != null) {
                int colTotal = sheet.getColumns();    // 전체 컬럼
                int rowIndexStart = 2;                  // row 인덱스 시작
                int rowTotal = sheet.getColumn(colTotal-1).length;

                String TYPE = "";
                String kor_title = "";
                String kor_dist = "";

                StringBuilder sb;
                for(int row=rowIndexStart;row<rowTotal;row++) {
                    sb = new StringBuilder();
                    for(int col=0;col<colTotal;col++) {
                        String contents = sheet.getCell(col, row).getContents();

                        if(col == 0)
                            TYPE = contents;
                        else if(col == 1)
                            kor_title = contents;
                        else if(col == 3)
                            kor_dist = contents;
                        else if(col == colTotal - 1 && TYPE.equals("SPOT")) {
                            ListData_2 listData = new ListData_2(kor_dist,
                                    getResources().getDrawable(getResources().getIdentifier("@drawable/pic_"+(row+1), "id", this.getPackageName())),
                                    kor_title);
                            itemList.add(listData);
                        }
                    }
                    Log.i("xls_log", sb.toString());
                }
            }
        }

        adapter = new MyAdapter_2(this, itemList, onClickItem);
        listview1.setAdapter(adapter);
    }

    private void init2() {
        listview2 = findViewById(R.id.list_view_store);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listview2.setLayoutManager(layoutManager);

        ArrayList<ListData_2> itemList = new ArrayList<>();

        if(wb != null) {
            Sheet sheet = wb.getSheet(0);   // 시트 불러오기
            if(sheet != null) {
                int colTotal = sheet.getColumns();    // 전체 컬럼
                int rowIndexStart = 2;                  // row 인덱스 시작
                int rowTotal = sheet.getColumn(colTotal-1).length;

                String TYPE = "";
                String kor_title = "";
                String kor_dist = "";

                StringBuilder sb;
                for(int row=rowIndexStart;row<rowTotal;row++) {
                    sb = new StringBuilder();
                    for(int col=0;col<colTotal;col++) {
                        String contents = sheet.getCell(col, row).getContents();

                        if(col == 0)
                            TYPE = contents;
                        else if(col == 1)
                            kor_title = contents;
                        else if(col == 3)
                            kor_dist = contents;
                        else if(col == colTotal - 1 && TYPE.equals("FOOD")) {
                            ListData_2 listData = new ListData_2(kor_dist,
                                    getResources().getDrawable(getResources().getIdentifier("@drawable/pic_"+(row+1), "id", this.getPackageName())),
                                    kor_title);
                            itemList.add(listData);
                        }
                    }
                    Log.i("xls_log", sb.toString());
                }
            }
        }

        adapter = new MyAdapter_2(this, itemList, onClickItem);
        listview2.setAdapter(adapter);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainPage_3.class);

            //intent.putExtra("country","일본");
/*
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();*/
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