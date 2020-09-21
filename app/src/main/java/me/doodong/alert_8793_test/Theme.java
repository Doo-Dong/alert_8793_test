package me.doodong.alert_8793_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.apache.log4j.lf5.util.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Theme extends AppCompatActivity {

    private RecyclerAdapter_Theme adapter;
    private RecyclerAdapter_Theme_Spot adapter_spot;
    RecyclerView recyclerView_spot, recyclerView_food;
    ExtendedFloatingActionButton btn_choice;
    CardView btn_place;
    ImageView image_place, image_list1, image_list2;
    ImageButton img_morning, img_afternoon, img_lunch, img_dinner;
    TextView tv_morning, tv_afternoon, tv_lunch, tv_dinner;
    ArrayList<Theme_Item_spot> itemList_spot = null;
    ArrayList<Theme_Item> itemList = null;

    Context context;
    Workbook wb;

    private onClickInterface_Spot onclickInterfaceSpot;
    private onClickInterface_Food onclickInterfaceFood;

    Drawable.ConstantState constantState_Spot, constantState_Food;

    AppCompatSpinner spinner;

    int day1_img1, day1_img2, day1_img3, day1_img4
            ,day2_img1, day2_img2, day2_img3, day2_img4
            ,day3_img1, day3_img2, day3_img3, day3_img4;

    int pos;
    Intent intent_send;


    int[] imgs, imgs2;
    int[] list;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_test);

        btn_choice = findViewById(R.id.btn_choice);

        Toolbar toolbar = findViewById(R.id.toolbar);

        btn_place = findViewById(R.id.btn_place);
        image_place = findViewById(R.id.image_place);
        image_list1 = findViewById(R.id.image_list1);
        image_list2 = findViewById(R.id.image_list2);

        img_morning = findViewById(R.id.img_morning);
        img_afternoon = findViewById(R.id.img_afternoon);
        img_lunch = findViewById(R.id.img_lunch);
        img_dinner = findViewById(R.id.img_dinner);

        tv_morning = findViewById(R.id.tv_morning);
        tv_afternoon = findViewById(R.id.tv_afternoon);
        tv_lunch = findViewById(R.id.tv_lunch);
        tv_dinner = findViewById(R.id.tv_dinner);

        Intent intent = getIntent();
        spinner();

        try {
            InputStream is = getBaseContext().getResources().getAssets().open("inform_chiangmai.xls");
            wb = Workbook.getWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

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

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void spinner(){
        spinner = findViewById(R.id.spinner);
        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this, R.array.theme_spinner, android.R.layout.simple_spinner_dropdown_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setSelected(true);
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#74e4c4"));
                ((TextView) adapterView.getChildAt(0)).setTextSize(12);
                ((TextView) adapterView.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                pos=i;
                if (spinner.getItemAtPosition(i).toString().equals("1일차")) {
                    //Toast.makeText(Theme.this, "1일차 클릭", Toast.LENGTH_SHORT).show();
                    reset();
                }else if (spinner.getItemAtPosition(i).toString().equals("2일차")){
                    //Toast.makeText(Theme.this, "2일차 클릭", Toast.LENGTH_SHORT).show();
                    reset();
                }else if (spinner.getItemAtPosition(i).toString().equals("3일차")) {
                    //Toast.makeText(Theme.this, "3일차 클릭", Toast.LENGTH_SHORT).show();
                    reset();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void reset(){
        itemList = new ArrayList<Theme_Item>();
        itemList_spot = new ArrayList<Theme_Item_spot>();
        img_morning.setImageResource(R.drawable.background_gray);
        img_afternoon.setImageResource(R.drawable.background_gray);
        img_lunch.setImageResource(R.drawable.background_gray);
        img_dinner.setImageResource(R.drawable.background_gray);

        getData();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getData() {
        init_spot();
        init_food();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getData_spot_morning(View view) {
        //Toast.makeText(Theme.this, "오전 클릭", Toast.LENGTH_SHORT).show();
        textColor();
        tv_morning.setTextColor(Color.parseColor("#74e4c4"));
        //interface 상단에 배치해야 에러없음
        onclickInterfaceSpot = new onClickInterface_Spot() {
            @Override
            public void setClick(int abc) {
                //Toast.makeText(Theme.this,"Position : "+abc,Toast.LENGTH_LONG).show();
                Theme_Item_spot item = itemList_spot.get(abc);
                img_morning.setImageDrawable(item.getImage());
                if (spinner.getItemAtPosition(pos).toString().equals("1일차")) {
                    day1_img1 = imgs[abc+2];
                    Log.d(" day1_img1", String.valueOf(day1_img1));

                    int res =getResources().getIdentifier("pic_3", "drawable", getPackageName());
                    int res2 =getResources().getIdentifier("pic_4", "drawable", getPackageName());
                    Log.d(" day1_img1_resId", String.valueOf(res));
                    Log.d(" day1_img1_resId2", String.valueOf(res2));

                }else if (spinner.getItemAtPosition(pos).toString().equals("2일차")){
                    day2_img1 =  imgs[abc+2];

                }else if (spinner.getItemAtPosition(pos).toString().equals("3일차")){
                    day3_img1 =   imgs[abc+2];
                }


            }
        };

        init_spot();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getData_spot_afternoon(View view) {
        //Toast.makeText(Theme.this, "오후 클릭", Toast.LENGTH_SHORT).show();

        textColor();
        tv_afternoon.setTextColor(Color.parseColor("#74e4c4"));
        try {
            constantState_Spot = img_morning.getDrawable().getConstantState();

            //interface 상단에 배치해야 에러없음
            onclickInterfaceSpot = new onClickInterface_Spot() {
                @Override
                public void setClick(int abc) {
                    //Toast.makeText(Theme.this,"Position : "+abc,Toast.LENGTH_LONG).show();
                    Theme_Item_spot item = itemList_spot.get(abc);
                    if (constantState_Spot.equals(item.getImage().getConstantState())){
                        Toast.makeText(Theme.this, "중복된 선택입니다."+ "\n" + "다른 관광지를 선택해주세요.", Toast.LENGTH_SHORT).show();
                    }else {
                        img_afternoon.setImageDrawable(item.getImage());
                        if (spinner.getItemAtPosition(pos).toString().equals("1일차")) {
                            day1_img2 =    imgs[abc+2];
                            Log.d("day1_img2", String.valueOf(day1_img2));

                        }else if (spinner.getItemAtPosition(pos).toString().equals("2일차")){
                            day2_img2 =    imgs[abc+2];

                        }else if (spinner.getItemAtPosition(pos).toString().equals("3일차")){
                            day3_img2 =    imgs[abc+2];

                        }

                    }
                }
            };

            init_spot();
        }catch (Exception e){
            Toast.makeText(Theme.this,"오전 항목이 선택되지 않았습니다." + "\n" + "오전 항목을 선택해주세요.",Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getData_food_lunch(View view) {
        //Toast.makeText(Theme.this, "점심 클릭", Toast.LENGTH_SHORT).show();
        textColor();
        tv_lunch.setTextColor(Color.parseColor("#74e4c4"));
        onclickInterfaceFood = new onClickInterface_Food() {
            @Override
            public void setClick(int abc) {
                //Toast.makeText(Theme.this,"Position : "+abc,Toast.LENGTH_LONG).show();
                Theme_Item item = itemList.get(abc);
                img_lunch.setImageDrawable(item.getImage());
                if (spinner.getItemAtPosition(pos).toString().equals("1일차")) {
                    day1_img3 =   imgs2[abc+44];
                    Log.d(" day1_img3", String.valueOf(day1_img3));

                    int res =getResources().getIdentifier("pic_45", "drawable", getPackageName());
                    int res2 =getResources().getIdentifier("pic_46", "drawable", getPackageName());
                    Log.d(" day1_img1_resId", String.valueOf(res));
                    Log.d(" day1_img1_resId2", String.valueOf(res2));
                }else if (spinner.getItemAtPosition(pos).toString().equals("2일차")){
                    day2_img3 =     imgs2[abc+44];

                }else if (spinner.getItemAtPosition(pos).toString().equals("3일차")){
                    day3_img3 =     imgs2[abc+44];

                }

            }
        };

        init_food();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getData_food_dinner(View view) {
        //Toast.makeText(Theme.this, "저녁 클릭", Toast.LENGTH_SHORT).show();
        textColor();
        tv_dinner.setTextColor(Color.parseColor("#74e4c4"));

        try {
            constantState_Food = img_lunch.getDrawable().getConstantState();
            onclickInterfaceFood = new onClickInterface_Food() {
                @Override
                public void setClick(int abc) {
                    //Toast.makeText(Theme.this,"Position : "+abc,Toast.LENGTH_LONG).show();
                    Theme_Item item = itemList.get(abc);
                    if (constantState_Food.equals(item.getImage().getConstantState())){
                        Toast.makeText(Theme.this, "중복된 선택입니다."+ "\n" + "다른 음식점을 선택해주세요.", Toast.LENGTH_SHORT).show();
                    }else {
                        img_dinner.setImageDrawable(item.getImage());
                        if (spinner.getItemAtPosition(pos).toString().equals("1일차")) {
                            day1_img4 = imgs2[abc+44];
                            Log.d("day1_img4", String.valueOf(day1_img4));

                        }else if (spinner.getItemAtPosition(pos).toString().equals("2일차")){
                            day2_img4 = imgs2[abc+44];

                        }else if (spinner.getItemAtPosition(pos).toString().equals("3일차")){
                            day3_img4 =  imgs2[abc+44];

                        }

                    }
                }
            };

            init_food();
        }catch (Exception e){
            Toast.makeText(Theme.this,"점심 항목이 선택되지 않았습니다." + "\n" + "점심 항목을 선택해주세요.",Toast.LENGTH_LONG).show();
        }
    }

    public void textColor(){
        tv_morning.setTextColor(Color.parseColor("#7a7a7a"));
        tv_afternoon.setTextColor(Color.parseColor("#7a7a7a"));
        tv_lunch.setTextColor(Color.parseColor("#7a7a7a"));
        tv_dinner.setTextColor(Color.parseColor("#7a7a7a"));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init_spot(){
        recyclerView_spot = findViewById(R.id.rv_theme_spot);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_spot.setLayoutManager(linearLayoutManager);
        itemList_spot = new ArrayList<>();

        if(wb != null) {
            Sheet sheet = wb.getSheet(0);   // 시트 불러오기
            if(sheet != null) {
                int colTotal = sheet.getColumns();    // 전체 컬럼
                int rowIndexStart = 2;                  // row 인덱스 시작
                int rowTotal = sheet.getColumn(colTotal-1).length;

                String TYPE = "";
                String title = "";

                StringBuilder sb;
                imgs = new int[rowTotal];
                for(int row=rowIndexStart;row<rowTotal;row++) {
                    sb = new StringBuilder();

                    for(int col=0;col<colTotal;col++) {
                        String contents = sheet.getCell(col, row).getContents();

                        if(col == 0)
                            TYPE = contents;
                        else if(col == 1)
                            title = contents;
                        else if(col == colTotal - 1 && TYPE.equals("SPOT")) {
                            Theme_Item_spot listData = new Theme_Item_spot(
                                    getDrawable(getResources().getIdentifier("pic_"+(row+1), "drawable", this.getPackageName())), title);
                            itemList_spot.add(listData);

                            imgs[row] = getApplicationContext().getResources().getIdentifier("pic_"+(row+1), "drawable", this.getPackageName());
                        }
                    }
                    Log.i("xls_log", sb.toString());


                }
                Log.d("imgs2", String.valueOf(imgs.length));
                Log.d("img[0]", String.valueOf(imgs[0]));
                Log.d("img[1]", String.valueOf(imgs[1]));
                Log.d("img[2]", String.valueOf(imgs[2]));
                Log.d("img[3]", String.valueOf(imgs[3]));
                Log.d("img[44]", String.valueOf(imgs[44]));
                Log.d("img[45]", String.valueOf(imgs[45]));
                Log.d("img[47]", String.valueOf(imgs[47]));
            }
        }

        adapter_spot = new RecyclerAdapter_Theme_Spot(context, itemList_spot, onclickInterfaceSpot);
        recyclerView_spot.setAdapter(adapter_spot);


        adapter_spot.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init_food(){
        recyclerView_food = findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_food.setLayoutManager(linearLayoutManager);
        itemList = new ArrayList<>();
        if(wb != null) {
            Sheet sheet = wb.getSheet(0);   // 시트 불러오기
            if(sheet != null) {
                int colTotal = sheet.getColumns();    // 전체 컬럼
                int rowIndexStart = 2;                  // row 인덱스 시작
                int rowTotal = sheet.getColumn(colTotal-1).length;

                String TYPE = "";
                String title = "";
                String sub = "";

                StringBuilder sb;
                imgs2 = new int[rowTotal];

                for(int row=rowIndexStart;row<rowTotal;row++) {
                    sb = new StringBuilder();
                    for(int col=0;col<colTotal;col++) {
                        String contents = sheet.getCell(col, row).getContents();

                        if(col == 0)
                            TYPE = contents;
                        else if(col == 1)
                            title = contents;
                        else if(col == 3)
                            sub = contents;
                        else if(col == colTotal - 1 && TYPE.equals("FOOD")) {
                            Theme_Item listData = new Theme_Item(
                                    getDrawable(getResources().getIdentifier("pic_"+(row+1), "drawable", this.getPackageName())),
                                    title,sub);
                            itemList.add(listData);

                            imgs2[row] = getApplicationContext().getResources().getIdentifier("pic_"+(row+1), "drawable", this.getPackageName());

                        }
                    }
                    Log.i("xls_log", sb.toString());
                }
                Log.d("imgs2", String.valueOf(imgs2.length));
                Log.d("img2[0]", String.valueOf(imgs2[0]));
                Log.d("img2[1]", String.valueOf(imgs2[1]));
                Log.d("img2[2]", String.valueOf(imgs2[2]));
                Log.d("img2[3]", String.valueOf(imgs2[3]));
                Log.d("img2[44]", String.valueOf(imgs2[44]));
                Log.d("img2[45]", String.valueOf(imgs2[45]));
                Log.d("img2[47]", String.valueOf(imgs2[47]));
            }
        }

        adapter = new RecyclerAdapter_Theme(context, itemList, onclickInterfaceFood);
        recyclerView_food.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void  onClick_click(View view) {
        //Toast.makeText(Theme.this, "선택완료 클릭", Toast.LENGTH_SHORT).show();
        //항목 아무 선택도 하지 않으면 mypage error
            list = new int[]{ day1_img1, day1_img2, day1_img3, day1_img4
                    ,day2_img1, day2_img2, day2_img3, day2_img4
                    ,day3_img1, day3_img2, day3_img3, day3_img4};
            intent_send = new Intent(getApplicationContext(), my_page.class);
            intent_send.putExtra("list_spot", list);
            startActivity(intent_send);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
    }

    public void  onClick_place(View view) {
        Intent intent = new Intent(getApplicationContext(), Theme_Map.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

}
