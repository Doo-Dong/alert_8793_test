package me.doodong.alert_8793_test;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Main extends AppCompatActivity {
    private RecyclerAdapter_Main adapter;
    ExtendedFloatingActionButton btn_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        btn_choice = findViewById(R.id.btn_choice);

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



//    private class HorizentalScrollViewMainView extends LinearLayout {
//        public HorizontalScrollView mHorizentalScrollView;
//        public LinearLayout mBtnLayout;
//
//        public HorizentalScrollViewMainView(Context context) {
//            super(context);
//            mBtnLayout = findViewById(R.id.horizontal);
//            mBtnLayout = new LinearLayout(context);
//
//            /** 가로로 자식들을 배치 함 */
//            mBtnLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//            Integer[] mImageIds = {
//                    R.drawable.main_img1,
//                    R.drawable.main_img1,
//                    R.drawable.main_img1,
//                    R.drawable.main_img1
//            };
//
//            /** 이미지 추가 */
//            for (int cnt = 0; cnt < mImageIds.length; cnt++) {
//
//                ImageView i = new ImageView(context);
//                i.setImageResource(mImageIds[cnt]);
//                i.setLayoutParams(new LayoutParams(530, 300));
//                i.setScaleType(ImageView.ScaleType.FIT_XY);
//
//                mBtnLayout.addView(i);
//            }
//
//            mHorizentalScrollView = new HorizontalScrollView(context);
//            mHorizentalScrollView.addView(mBtnLayout);
//
//            addView(mHorizentalScrollView);
//        }
//    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter_Main(onClickItem);
        recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MainPage_2.class);
            startActivity(intent);
        }
    };


    private void getData() {
        // 임의의 데이터입니다.

        List<Integer> listResId = Arrays.asList(
                R.drawable.recommendation1,
                R.drawable.recommendation2
        );
        List<String> listTitle = Arrays.asList(
                "반카왕마켓","입이 즐거운 먹거리"
        );
        List<String> listSubtitle = Arrays.asList(
                "예술가마을, 아기자기 수공예품 일요마켓", "센트럴 페스티벌 마켓, 입맛 없을 땐 요시노야"
        );

        for (int i = 0; i < listResId.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Main_Item data = new Main_Item();
            data.setImage(listResId.get(i));
            data.setTv1(listTitle.get(i));
            data.setTv2(listSubtitle.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }


    public void  onClick_my_theme(View view) {
        Intent intent = new Intent(getApplicationContext(), Theme.class);
        startActivity(intent);
    }

    public void  onClick_main2(View view) {
        Intent intent = new Intent(getApplicationContext(), MainPage_2.class);
        startActivity(intent);
    }
}
