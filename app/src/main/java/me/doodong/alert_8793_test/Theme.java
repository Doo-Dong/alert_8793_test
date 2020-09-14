package me.doodong.alert_8793_test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Theme extends AppCompatActivity {

    private RecyclerAdapter_Theme adapter;
    private RecyclerAdapter_Theme_Spot adapter_spot;
    RecyclerView recyclerView;
    ExtendedFloatingActionButton btn_choice;
    CardView btn_place;
    ImageView image_place, image_list1, image_list2, img_morning, img_afternoon;
    ArrayList<Theme_Item_spot> mList = new ArrayList<Theme_Item_spot>();

    Context context;

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

        Intent intent = getIntent();
        spinner();
        try {
            InputStream is = getBaseContext().getResources().getAssets().open("inform_chiangmai.xls");
            Workbook wb = Workbook.getWorkbook(is);

            if(wb != null) {
                Sheet sheet = wb.getSheet(0);   // 시트 불러오기
                if(sheet != null) {
                    int colTotal = sheet.getColumns();    // 전체 컬럼
                    int rowIndexStart = 1;                  // row 인덱스 시작
                    int rowTotal = sheet.getColumn(colTotal-1).length;

                    StringBuilder sb;
                    for(int row=rowIndexStart;row<rowTotal;row++) {
                        sb = new StringBuilder();
                        for(int col=0;col<colTotal;col++) {
                            String contents = sheet.getCell(col, row).getContents();
                            sb.append("col"+col+" : "+contents+" , ");
                        }
                        Log.i("xls_log", sb.toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

        getData_spot_morning();


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

    public void spinner(){
        AppCompatSpinner spinner = findViewById(R.id.spinner);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
//
//    private void theme_spot() {
//        recyclerView = findViewById(R.id.rv_theme_spot);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        //adapter_spot = new RecyclerAdapter_Theme_Spot();
//        recyclerView.setAdapter(adapter_spot);
//    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void getData_spot_morning() {
        // 임의의 데이터입니다.


        recyclerView = findViewById(R.id.rv_theme_spot);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter_spot = new RecyclerAdapter_Theme_Spot(mList);
        recyclerView.setAdapter(adapter_spot);

        for(int i = 3; i<36; i++){
            addItem(getDrawable(getResources().getIdentifier("@drawable/pic_"+(i), "id", this.getPackageName())));
        }

//        adapter_spot.setOnItemClickListener(new RecyclerAdapter_Theme_Spot.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//
//            }
//        });

//        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener_Spot(context, new RecyclerViewItemClickListener_Spot.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                long item = adapter_spot.getItemId(position);
//                // view is the clicked view (the one you wanted
//                // position is its position in the adapter
//                //img_morning.setImageResource(adapter_spot.getItemViewType(position));
//                //Toast.makeText(Theme.this, " 클릭", Toast.LENGTH_SHORT).show();
//            }
//        }));

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildAdapterPosition(child);
                Theme_Item_spot item = mList.get(position);

                //Toast.makeText(Theme.this, position+ " 클릭", Toast.LENGTH_SHORT).show();
                img_morning.setImageDrawable(item.getImage());

                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        adapter_spot.notifyDataSetChanged();
    }


    public void addItem(Drawable imageView) {
        Theme_Item_spot item = new Theme_Item_spot();

        item.setImage(imageView);

        mList.add(item);
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
