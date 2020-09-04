package me.doodong.alert_8793_test;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import jxl.Sheet;
import jxl.Workbook;

public class Theme extends AppCompatActivity {

    private RecyclerAdapter_Theme adapter;
    ExtendedFloatingActionButton btn_choice;
    LinearLayout btn_place, btn_list;
    ImageView image_place, image_list1, image_list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_test);
        btn_choice = findViewById(R.id.btn_choice);

        Toolbar toolbar = findViewById(R.id.toolbar);

        btn_place = findViewById(R.id.btn_place);
        btn_list = findViewById(R.id.btn_list);
        image_place = findViewById(R.id.image_place);
        image_list1 = findViewById(R.id.image_list1);
        image_list2 = findViewById(R.id.image_list2);

        Intent intent = getIntent();

        init();
        getData();

        // 전체화면인 DrawerLayout 객체 참조
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        // Drawer 화면(뷰) 객체 참조
        final View drawerView = findViewById(R.id.drawer);

        // 드로어 버튼 객체 참조
        ImageButton btnOpenDrawer = findViewById(R.id.drawerLayout_Btn);
        Button btnCloseDrawer = findViewById(R.id.drawerLayout_Btn_close);
        Button btnList_main = findViewById(R.id.drawerLayout_list_btn_1);
        Button btnList_airInfo = findViewById(R.id.drawerLayout_list_btn_2);
        Button btnList_myPage = findViewById(R.id.drawerLayout_list_btn_3);

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
                startActivity(intent);
            }
        });


    }


    private void init() {
        RecyclerView recyclerView = findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter_Theme();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        // 임의의 데이터입니다.

        List<Integer> listResId = Arrays.asList(
                R.drawable.theme_food1,
                R.drawable.theme_food2,
                R.drawable.theme_food3
        );
        List<String> listTitle = Arrays.asList(
                "입이 즐거운 먹거리","입이 즐거운 먹거리","입이 즐거운 먹거리"
        );
        List<String> listSubtitle = Arrays.asList(
                "TV에도 나온 그 맛집", "TV에도 나온 그 맛집", "TV에도 나온 그 맛집"
        );
        List<String> listSub = Arrays.asList(
                "2km to city", "2km to city", "2km to city"
        );
        for (int i = 0; i < listResId.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Theme_Item data = new Theme_Item();
            data.setImage(listResId.get(i));
            data.setTv1(listTitle.get(i));
            data.setTv2(listSubtitle.get(i));
            data.setTv3(listSub.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }

    public void  onClick_click(View view) {
        //Toast.makeText(Theme.this, "선택완료 클릭", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), my_page.class);
        startActivity(intent);
    }

    public void  onClick_place(View view) {
        Intent intent = new Intent(getApplicationContext(), Theme_Map.class);
        startActivity(intent);
        finish();
    }

}
