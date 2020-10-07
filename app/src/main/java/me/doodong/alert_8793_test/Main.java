package me.doodong.alert_8793_test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
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

    String country, schedule_name;
    private onClickInterface_Main onclickInterfaceMain;

    int[] list;
    int[] local_list;
    String[] name_list;
    int[] ftl0;
    int[] ftl1;
    int[] ftl2;
    int[][] ftl_data_list;
    ImageView[] friend_theme_list;
    SharedPreferences localData;

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

        friend_theme_list = new ImageView[]{
                friend_theme1, friend_theme2, friend_theme3
        };

        ftl0 = new int[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };

        ftl1 = new int[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };

        ftl2 = new int[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };

        ftl_data_list = new int[][] {
                ftl0, ftl1, ftl2
        };

        local_list = new int[]{
                0, 0, 0
        };

        name_list = new String[]{
                "", "", ""
        };

        // 드로어 버튼 객체 참조
        ImageButton btnOpenDrawer = findViewById(R.id.drawerLayout_Btn);
        Button btnCloseDrawer = findViewById(R.id.drawerLayout_Btn_close);
        TextView btnList_main = findViewById(R.id.drawerLayout_list_btn_1);
        TextView btnList_thai = findViewById(R.id.drawerLayout_list_btn_thai);
        TextView btnList_jap = findViewById(R.id.drawerLayout_list_btn_jap);
        TextView btnList_airInfo = findViewById(R.id.drawerLayout_list_btn_2);
        TextView btnList_myPage = findViewById(R.id.drawerLayout_list_btn_3);
        ImageButton btnList_thai_img = findViewById(R.id.drawerLayout_list_btn_thai_img);
        ImageButton btnList_jap_img = findViewById(R.id.drawerLayout_list_btn_jap_img);

        Intent intent = getIntent();
        country = intent.getStringExtra("country");
        list = intent.getIntArrayExtra("list_spot");
        schedule_name = intent.getStringExtra("schedule_name");
        localData = getSharedPreferences("localData", 0);


        // 세이브 데이터 체크
        if (localData.getBoolean("is_Save_ftl", false)) {
            // 있으면 로드
            Log.i("동작 세션", "있으면 로드");
            local_list = new int[]{
                    localData.getInt("ftl_localList0", R.drawable.background_gray),
                    localData.getInt("ftl_localList1", R.drawable.background_gray),
                    localData.getInt("ftl_localList2", R.drawable.background_gray),
            };

            name_list = new String[] {
                    localData.getString("ftl0_name", ""),
                    localData.getString("ftl1_name", ""),
                    localData.getString("ftl2_name", ""),
            };

            for (int i = 0; i < local_list.length; i++) {
                //Log.i("저장 내용 1 ", "" + local_list[i]);
                for (int j = 0; j < 12; j++) {
                    ftl_data_list[i][j] = localData.getInt("ftl" + i + j, R.drawable.background_gray);
                    //Log.i("저장 내용 2 ", "" + ftl_data_list[i][j]);
                }
            }

        } else {
            Log.i("동작 세션", "저장이 안되어있네?");
        }

        if(country.equals("일본")){
            img_main1.setBackgroundResource(R.drawable.main_img4);
            img_main4.setBackgroundResource(R.drawable.main_img1);
            img_main2.setBackgroundResource(R.drawable.main_img3);
            img_main3.setBackgroundResource(R.drawable.main_img2);
            tv_MainTheme.setText("일본");
            btnList_jap.setBackgroundColor(Color.parseColor("#74e4c4"));
            btnList_jap.setTextColor(Color.parseColor("#ffffff"));
            btnList_jap.setPadding((int) dpToPx(getApplicationContext(),15),0,0,0);
            LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) btnList_jap.getLayoutParams();
            lp2.leftMargin = (int) dpToPx(getApplicationContext(),0);
            btnList_jap.setLayoutParams(lp2);

            ViewGroup.LayoutParams params = btnList_jap_img.getLayoutParams();
            params.width = (int) dpToPx(getApplicationContext(),40);
            btnList_jap_img.setLayoutParams(params);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) btnList_jap_img.getLayoutParams();
            lp.leftMargin = (int) dpToPx(getApplicationContext(),10);
            lp.rightMargin = (int) dpToPx(getApplicationContext(),10);
            btnList_jap_img.setLayoutParams(lp);
            btnList_jap_img.setBackgroundResource(R.drawable.drawer);
        } else {
            img_main1.setBackgroundResource(R.drawable.main_img1);
            img_main2.setBackgroundResource(R.drawable.main_img2);
            img_main3.setBackgroundResource(R.drawable.main_img3);
            img_main4.setBackgroundResource(R.drawable.main_img4);
            tv_MainTheme.setText("태국");
            btnList_thai.setBackgroundColor(Color.parseColor("#74e4c4"));
            btnList_thai.setTextColor(Color.parseColor("#ffffff"));
            btnList_thai.setPadding((int) dpToPx(getApplicationContext(),15),0,0,0);
            LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) btnList_thai.getLayoutParams();
            lp2.leftMargin = (int) dpToPx(getApplicationContext(),0);
            btnList_thai.setLayoutParams(lp2);

            ViewGroup.LayoutParams params = btnList_thai_img.getLayoutParams();
            params.width = (int) dpToPx(getApplicationContext(),40);
            btnList_thai_img.setLayoutParams(params);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) btnList_thai_img.getLayoutParams();
            lp.leftMargin = (int) dpToPx(getApplicationContext(),10);
            lp.rightMargin = (int) dpToPx(getApplicationContext(),10);
            btnList_thai_img.setLayoutParams(lp);
            btnList_thai_img.setBackgroundResource(R.drawable.drawer);
        }

        if (list != null) {
            boolean flag = true;
            int temp = 0;
            int[] temp_arr;
            String temp_string = "";
            int ftl_save_index = 0;

            SharedPreferences.Editor editor = localData.edit();

            //로컬 리스트에 푸쉬
            for (ImageView ftl : friend_theme_list) {
                if (flag) {
                    // 오른쪽으로 한칸씩 쉬프트
                    temp = local_list[1];
                    local_list[1] = local_list[0];
                    local_list[2] = temp;

                    temp_string = name_list[1];
                    name_list[1] = name_list[0];
                    name_list[2] = temp_string;

                    temp_arr = ftl_data_list[1];
                    ftl_data_list[1] = ftl_data_list[0];
                    ftl_data_list[2] = temp_arr;

                    //Log.i("동작 세션", "로컬 리스트 쉬프트");

                    local_list[0] = list[0];
                    name_list[0] = schedule_name;
                    for (int j = 0; j < 12; j++) {
                        ftl_data_list[0][j] = list[j];
/*                        editor.putInt("ftl" + ftl_save_index + j, list[j]);
                        Log.i("저장 내용 2 ", "" + list[j]);*/
                    }

                    Log.i("동작 세션", "로컬 리스트 신규값 인서트");

                    ftl_save_index = 0;

                    flag = false;
                }

                //디스플레이
                ftl.setBackgroundResource(local_list[Arrays.asList(friend_theme_list).indexOf(ftl)]);

                //Log.i("동작 세션", "로컬 리스트 디스플레이 " + Arrays.asList(friend_theme_list).indexOf(ftl) + "번째");

                // 로컬 리스트 저장
                editor.putInt("ftl_localList" + Arrays.asList(friend_theme_list).indexOf(ftl), local_list[Arrays.asList(friend_theme_list).indexOf(ftl)]);
                editor.putString("ftl" + Arrays.asList(friend_theme_list).indexOf(ftl) + "_name", name_list[Arrays.asList(friend_theme_list).indexOf(ftl)]);
                for (int j = 0; j < 12; j++) {
                    editor.putInt("ftl" + Arrays.asList(friend_theme_list).indexOf(ftl) + j, ftl_data_list[Arrays.asList(friend_theme_list).indexOf(ftl)][j]);
                }
                editor.commit();

                //Log.i("동작 세션", "로컬 리스트 저장 " + Arrays.asList(friend_theme_list).indexOf(ftl) + "번째");
            }

            editor.putBoolean("is_Save_ftl", true);
            editor.commit();

            Log.i("저장", "SAVE : FTL Saved! => " + localData.getBoolean("is_Save_ftl", false));
        } else {
            for (ImageView ftl : friend_theme_list) {
                ftl.setBackgroundResource(local_list[Arrays.asList(friend_theme_list).indexOf(ftl)]);
            }
        }

        init();
        getData();

        // 전체화면인 DrawerLayout 객체 참조
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        // Drawer 화면(뷰) 객체 참조
        final View drawerView = findViewById(R.id.drawer);



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

        friend_theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_send = new Intent(getApplicationContext(), my_page.class);
                intent_send.putExtra("list_spot", ftl_data_list[0]);
                intent_send.putExtra("schedule_name", name_list[0]);
                intent_send.putExtra("reset", false);
                startActivity(intent_send);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        friend_theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_send = new Intent(getApplicationContext(), my_page.class);
                intent_send.putExtra("list_spot", ftl_data_list[1]);
                intent_send.putExtra("schedule_name", name_list[1]);
                intent_send.putExtra("reset", false);
                startActivity(intent_send);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        friend_theme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_send = new Intent(getApplicationContext(), my_page.class);
                intent_send.putExtra("list_spot", ftl_data_list[2]);
                intent_send.putExtra("schedule_name", name_list[2]);
                intent_send.putExtra("reset", false);
                startActivity(intent_send);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    private float dpToPx(Context context, float dp){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,dm);
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
                if(items[pos].equals("일본")){/*
                    img_main1.setBackgroundResource(R.drawable.main_img4);
                    img_main4.setBackgroundResource(R.drawable.main_img1);
                    img_main2.setBackgroundResource(R.drawable.main_img3);
                    img_main3.setBackgroundResource(R.drawable.main_img2);
                    tv_MainTheme.setText("일본");
                    country = "일본";*/
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    intent.putExtra("country","일본");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } else {/*
                    img_main1.setBackgroundResource(R.drawable.main_img1);
                    img_main2.setBackgroundResource(R.drawable.main_img2);
                    img_main3.setBackgroundResource(R.drawable.main_img3);
                    img_main4.setBackgroundResource(R.drawable.main_img4);
                    tv_MainTheme.setText("태국");
                    country = "태국";*/
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    intent.putExtra("country","태국");
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
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
                Intent intent = new Intent(getApplicationContext(), RecommendationResult.class);
                intent.putExtra("main_list"+abc, String.valueOf(abc));
                if(country.equals("일본")) {
                    intent.putExtra("reverse", true);
                }
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        };

        adapter = new RecyclerAdapter_Main(getApplicationContext(), onclickInterfaceMain);
        recyclerView.setAdapter(adapter);
    }




    private void getData() {
        List<Integer> listResId;
        List<String> listTitle, listSubtitle;
        if(country.equals("일본")){
            // 임의의 데이터입니다.
            listResId = Arrays.asList(
                    R.drawable.recommendation4,
                    R.drawable.recommendation3,
                    R.drawable.recommendation2,
                    R.drawable.recommendation1
            );
            listTitle = Arrays.asList(
                    "입이 즐거운 먹거리", "반카왕마켓",
                    "입이 즐거운 먹거리", "반카왕마켓"
            );
            listSubtitle = Arrays.asList(
                    "센트럴 페스티벌 마켓, 입맛 없을 땐 요시노야", "예술가마을, 아기자기 수공예품 일요마켓",
                    "센트럴 페스티벌 마켓, 입맛 없을 땐 요시노야", "예술가마을, 아기자기 수공예품 일요마켓"

            );
        } else {
            // 임의의 데이터입니다.
            listResId = Arrays.asList(
                    R.drawable.recommendation1,
                    R.drawable.recommendation2,
                    R.drawable.recommendation3,
                    R.drawable.recommendation4
            );
            listTitle = Arrays.asList(
                    "반카왕마켓","입이 즐거운 먹거리",
                    "반카왕마켓","입이 즐거운 먹거리"
            );
            listSubtitle = Arrays.asList(
                    "예술가마을, 아기자기 수공예품 일요마켓", "센트럴 페스티벌 마켓, 입맛 없을 땐 요시노야",
                    "예술가마을, 아기자기 수공예품 일요마켓", "센트럴 페스티벌 마켓, 입맛 없을 땐 요시노야"
            );
        }

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