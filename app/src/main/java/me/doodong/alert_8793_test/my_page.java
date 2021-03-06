package me.doodong.alert_8793_test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class my_page extends AppCompatActivity {
    private RecyclerView listview1;
    private RecyclerView listview2;
    private RecyclerView listview3;
    private EditText schedule_name;
    private MyAdapter adapter1;
    private MyAdapter adapter2;
    private MyAdapter adapter3;
    String result, resID, day, time, name;

    Random rand = new Random();
    int[] list;
    boolean reset;

    SharedPreferences localData;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.BaseTheme);
        setContentView(R.layout.my_page);

        schedule_name = findViewById(R.id.schedule_name);

        //데이터 가져오기
        Intent intent = getIntent();
        result = intent.getStringExtra("result");
        list = intent.getIntArrayExtra("list_spot");
        name = intent.getStringExtra("schedule_name");
        reset = intent.getBooleanExtra("reset", true);
        localData = getSharedPreferences("localData", 0);

        //Main3 to my_page
        if (result != null) {
            if (result.equals("Close Popup")) {
                resID = intent.getStringExtra("resID");
                day = intent.getStringExtra("day");
                time = intent.getStringExtra("time");
                Log.d("click to my_page", resID + day + time );
            }
        }

        if (list != null) {
            SharedPreferences.Editor editor = localData.edit();
            //editor.clear();
/*            for (int i = 0; i < 12; i++) {
                editor.remove(""+i);
            }
            editor.commit();*/

            //Toast.makeText(this, "새 여행정보를 만듭니다", Toast.LENGTH_SHORT).show();
        } else {
            //세이브 체크
            if (localData.getBoolean("is_Save", false)) {
                schedule_name.setText(localData.getString("schedule_name", ""));

                list = new int[]{
                        localData.getInt("0", R.drawable.background_gray),
                        localData.getInt("1", R.drawable.background_gray),
                        localData.getInt("2", R.drawable.background_gray),
                        localData.getInt("3", R.drawable.background_gray),
                        localData.getInt("4", R.drawable.background_gray),
                        localData.getInt("5", R.drawable.background_gray),
                        localData.getInt("6", R.drawable.background_gray),
                        localData.getInt("7", R.drawable.background_gray),
                        localData.getInt("8", R.drawable.background_gray),
                        localData.getInt("9", R.drawable.background_gray),
                        localData.getInt("10", R.drawable.background_gray),
                        localData.getInt("11", R.drawable.background_gray)
                };

                Toast.makeText(this, "데이터를 불러왔습니다", Toast.LENGTH_SHORT).show();;
            } else {
                list = new int[]{
                        0,0,0,0,
                        0,0,0,0,
                        0,0,0,0,
                };
            }
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

        // 저장 버튼 객체 참조
        Button schedule_save = findViewById(R.id.save);

        if (name != null) {
            schedule_name.setText(name);
        }

        // 바텀 버튼 객체 참조
        final Button mypg_bottom_btn_1 = findViewById(R.id.mypg_bottom_btn_1);
        Button mypg_bottom_btn_2 = findViewById(R.id.mypg_bottom_btn_2);
        Button mypg_bottom_btn_3 = findViewById(R.id.mypg_bottom_btn_3);

        // 일정 저장 버튼
        schedule_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = localData.edit();

                editor.putString("schedule_name", schedule_name.getText().toString());

                int index = 0;
                for (int i : list) {
                    editor.putInt(Integer.toString(index), i);
                    index++;
                }
                editor.putBoolean("is_Save", true);
                editor.commit();

                Toast.makeText(my_page.this, "저장되었습니다", Toast.LENGTH_SHORT).show();
                Log.i("저장", "SAVE : onClick and Saved! => " + localData.getBoolean("is_Save", false));
            }
        });

        // 일정 시작 -> 일정들 애니메이션 & 하이라이트
        mypg_bottom_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypg_bottom_btn_1.setEnabled(false);

                final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
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
                                vibrator.vibrate(100); // 0.5초간 진동
                                listview1.smoothScrollToPosition(count);
                                listview2.smoothScrollToPosition(0);
                                listview3.smoothScrollToPosition(0);
                            }
                            else if(phase==2){
                                adapter = adapter2;
                                vibrator.vibrate(100); // 0.5초간 진동
                                listview1.smoothScrollToPosition(0);
                                listview2.smoothScrollToPosition(count);
                                listview3.smoothScrollToPosition(0);
                            }
                            else if(phase==3){
                                adapter = adapter3;
                                vibrator.vibrate(100); // 0.5초간 진동
                                listview1.smoothScrollToPosition(0);
                                listview2.smoothScrollToPosition(0);
                                listview3.smoothScrollToPosition(count);
                            }
                            else if(phase==4){
                                vibrator.vibrate(100); // 0.5초간 진동
                                listview1.smoothScrollToPosition(0);
                                listview2.smoothScrollToPosition(0);
                                listview3.smoothScrollToPosition(0);
                                adapter1.notifyItemRangeChanged(0,4, "init");
                                adapter2.notifyItemRangeChanged(0,4, "init");
                                adapter3.notifyItemRangeChanged(0,4, "init");
                                mypg_bottom_btn_1.setEnabled(true);
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
                intent.putExtra("country","태국");
                intent.putExtra("list_spot", list);
                intent.putExtra("schedule_name", schedule_name.getText().toString());
                intent.putExtra("is_shift", true);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                Toast.makeText(my_page.this, "친구테마로 일정이 공유되었습니다", Toast.LENGTH_SHORT).show();
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

    private String randDirect(int flag) {
        String direct = "@drawable/dehaze";

        if(flag != 0) {
            int directFlag = (rand.nextInt(2) + 1);

            switch (directFlag) {
                case 1 :
                    direct = "@drawable/directions_walk";
                    break;
                case 2 :
                    direct = "@drawable/directions_bus";
                    break;
                default:
                    break;
            }
        }

        return direct;
    }

    private String randDist(int flag) {
        String dist = "";

        if(flag != 0) {
            dist = (rand.nextInt(98) + 1) + "m";
        }

        return dist;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init1() {
        listview1 = findViewById(R.id.list_view_1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview1.setLayoutManager(layoutManager);

        ArrayList<ListData> itemList = new ArrayList<>();
        ListData listData = new ListData("오전",
                (day+time).equals("1일차오전") ? Integer.parseInt(resID) : list[0],
                getResources().getDrawable(getResources().getIdentifier(randDirect(1), "id", this.getPackageName())),
                randDist(1));
        itemList.add(listData);
        listData = new ListData("점심",
                (day+time).equals("1일차점심") ? Integer.parseInt(resID) : list[2],
                getResources().getDrawable(getResources().getIdentifier(randDirect(1), "id", this.getPackageName())),
                randDist(1));
        itemList.add(listData);
        listData = new ListData("오후",
                (day+time).equals("1일차오후") ? Integer.parseInt(resID) : list[1],
                getResources().getDrawable(getResources().getIdentifier(randDirect(1), "id", this.getPackageName())),
                randDist(1));
        itemList.add(listData);
        listData = new ListData("저녁",
                (day+time).equals("1일차저녁") ? Integer.parseInt(resID) : list[3],
                getResources().getDrawable(getResources().getIdentifier(randDirect(0), "id", this.getPackageName())),
                randDist(0));
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
                (day+time).equals("2일차오전") ? Integer.parseInt(resID) : list[4],
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "1m");
        itemList.add(listData);
        listData = new ListData("점심",
                (day+time).equals("2일차점심") ? Integer.parseInt(resID) :list[6],
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "3m");
        itemList.add(listData);
        listData = new ListData("오후",
                (day+time).equals("2일차오후") ? Integer.parseInt(resID) : list[5],
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "5m");
        itemList.add(listData);
        listData = new ListData("저녁",
                (day+time).equals("2일차저녁") ? Integer.parseInt(resID) : list[7],
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
                (day+time).equals("3일차오전") ? Integer.parseInt(resID) : list[8],
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_walk", "id", this.getPackageName())),
                "1m");
        itemList.add(listData);
        listData = new ListData("점심",
                (day+time).equals("3일차점심") ? Integer.parseInt(resID) : list[10],
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "25m");
        itemList.add(listData);
        listData = new ListData("오후",
                (day+time).equals("3일차오후") ? Integer.parseInt(resID) : list[9],
                getResources().getDrawable(getResources().getIdentifier("@drawable/directions_bus", "id", this.getPackageName())),
                "10m");
        itemList.add(listData);
        listData = new ListData("저녁",
                (day+time).equals("3일차저녁") ? Integer.parseInt(resID) : list[11],
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
    int image;
    Drawable direct;
    String dist;

    ListData(String text, int image, Drawable direct, String dist) {
        this.text = text;
        this.image = image;
        this.direct = direct;
        this.dist = dist;
    }
}