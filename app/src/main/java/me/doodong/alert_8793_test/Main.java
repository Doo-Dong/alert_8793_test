package me.doodong.alert_8793_test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class Main extends AppCompatActivity {
    private RecyclerAdapter_Main adapter;
    ExtendedFloatingActionButton btn_choice;
    TextView tv_MainTheme;

    ImageView img_main1, img_main2, img_main3, img_main4, friend_theme1, friend_theme2, friend_theme3;

    String country;
    private onClickInterface_Main onclickInterfaceMain;

    int[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        btn_choice = findViewById(R.id.btn_choice);
        tv_MainTheme= findViewById(R.id.tv_MainTheme);

        img_main1 = findViewById(R.id.img_main1);
        img_main2 = findViewById(R.id.img_main2);
        img_main3 = findViewById(R.id.img_main3);
        img_main4 = findViewById(R.id.img_main4);

        friend_theme1 = findViewById(R.id.friend_theme1);
        friend_theme2 = findViewById(R.id.friend_theme2);
        friend_theme3 = findViewById(R.id.friend_theme3);

        Intent intent = getIntent();
        country = intent.getStringExtra("country");
        list = intent.getIntArrayExtra("list_spot");

        if(country.equals("일본")){
            img_main1.setBackgroundResource(R.drawable.main_img4);
            img_main4.setBackgroundResource(R.drawable.main_img1);
            img_main2.setBackgroundResource(R.drawable.main_img3);
            img_main3.setBackgroundResource(R.drawable.main_img2);
            tv_MainTheme.setText("일본");
        }else {
            img_main1.setBackgroundResource(R.drawable.main_img1);
            img_main2.setBackgroundResource(R.drawable.main_img2);
            img_main3.setBackgroundResource(R.drawable.main_img3);
            img_main4.setBackgroundResource(R.drawable.main_img4);
            tv_MainTheme.setText("태국");
        }

        if (list != null) {
            friend_theme1.setBackgroundResource(list[0]);
            friend_theme2.setBackgroundResource(list[1]);
            friend_theme3.setBackgroundResource(list[2]);
        } else {
            list = new int[]{
                    R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray
                    ,R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray
                    ,R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray,R.drawable.background_gray
            };
            friend_theme1.setBackgroundResource(list[0]);
            friend_theme2.setBackgroundResource(list[1]);
            friend_theme3.setBackgroundResource(list[2]);
        }

        init();
        getData();

        // 전체화면인 DrawerLayout 객체 참조
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        // Drawer 화면(뷰) 객체 참조
        final View drawerView = findViewById(R.id.drawer);

        // 드로어 버튼 객체 참조
        ImageButton btnOpenDrawer = findViewById(R.id.drawerLayout_Btn);
        Button btnCloseDrawer = findViewById(R.id.drawerLayout_Btn_close);
        TextView btnList_main = findViewById(R.id.drawerLayout_list_btn_1);
        TextView btnList_thai = findViewById(R.id.drawerLayout_list_btn_thai);
        TextView btnList_jap = findViewById(R.id.drawerLayout_list_btn_jap);
        TextView btnList_airInfo = findViewById(R.id.drawerLayout_list_btn_2);
        TextView btnList_myPage = findViewById(R.id.drawerLayout_list_btn_3);

        img_main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPage_2.class);
                intent.putExtra("shuffle",2);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        img_main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPage_2.class);
                intent.putExtra("shuffle",4);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        img_main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPage_2.class);
                intent.putExtra("shuffle",6);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        img_main4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPage_2.class);
                intent.putExtra("shuffle",8);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
            }
        });


    }
    public void main_Theme(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("테마 선택");

        builder.setItems(R.array.main_dialog, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int pos)
            {
                String[] items = getResources().getStringArray(R.array.main_dialog);

                tv_MainTheme.setText(items[pos]);
                if(items[pos].equals("일본")){
                    img_main1.setBackgroundResource(R.drawable.main_img4);
                    img_main4.setBackgroundResource(R.drawable.main_img1);
                    img_main2.setBackgroundResource(R.drawable.main_img3);
                    img_main3.setBackgroundResource(R.drawable.main_img2);
                    tv_MainTheme.setText("일본");
                }else {
                    img_main1.setBackgroundResource(R.drawable.main_img1);
                    img_main2.setBackgroundResource(R.drawable.main_img2);
                    img_main3.setBackgroundResource(R.drawable.main_img3);
                    img_main4.setBackgroundResource(R.drawable.main_img4);
                    tv_MainTheme.setText("태국");
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        onclickInterfaceMain = new onClickInterface_Main() {
            @Override
            public void setClick(int abc) {
                //Toast.makeText(Main.this,"Position : "+abc,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), RecommendationResult.class);

                intent.putExtra("main_list"+abc, String.valueOf(abc));
//                intent.putExtra("main_list2", "1");
//                intent.putExtra("main_list3", "2");
//                intent.putExtra("main_list4", "3");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        };

        adapter = new RecyclerAdapter_Main(getApplicationContext(), onclickInterfaceMain);
        recyclerView.setAdapter(adapter);
    }




    private void getData() {
        // 임의의 데이터입니다.

        List<Integer> listResId = Arrays.asList(
                R.drawable.recommendation1,
                R.drawable.recommendation2,
                R.drawable.recommendation3,
                R.drawable.recommendation4
        );
        List<String> listTitle = Arrays.asList(
                "반카왕마켓","입이 즐거운 먹거리", "반카왕마켓","입이 즐거운 먹거리"
        );
        List<String> listSubtitle = Arrays.asList(
                "예술가마을, 아기자기 수공예품 일요마켓", "센트럴 페스티벌 마켓, 입맛 없을 땐 요시노야",
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
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}