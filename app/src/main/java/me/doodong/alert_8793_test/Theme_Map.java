package me.doodong.alert_8793_test;

import android.content.Context;
import android.content.Intent;
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
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Theme_Map extends FragmentActivity implements OnMapReadyCallback {
    //구글맵참조변수
    GoogleMap mMap;

    //툴바 버튼 변수
    CardView btn_list;

    //before var
    ExtendedFloatingActionButton btn_choice;
    CardView btn_place;
    ImageView image_place, image_list1, image_list2;
    ImageButton img_morning, img_afternoon, img_lunch, img_dinner;
    TextView tv_morning, tv_afternoon, tv_lunch, tv_dinner;
    ArrayList<Theme_Item_spot> itemList_spot = null;
    ArrayList<Theme_Item> itemList = null;

    Workbook wb;
    TextView title, dist;
    double Lat, Lng;

    AppCompatSpinner spinner;

    int pos;
    int[] list;
    int[] initList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_map);

        img_morning = findViewById(R.id.img_morning);
        img_afternoon = findViewById(R.id.img_afternoon);
        img_lunch = findViewById(R.id.img_lunch);
        img_dinner = findViewById(R.id.img_dinner);

        Intent intent = getIntent();
        list = intent.getIntArrayExtra("list_spot");

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

        // 버튼 객체 참조
        ImageButton btnOpenDrawer = findViewById(R.id.drawerLayout_Btn);

        btn_list = findViewById(R.id.btn_list);
        Button btnCloseDrawer = findViewById(R.id.drawerLayout_Btn_close);
        TextView btnList_main = findViewById(R.id.drawerLayout_list_btn_1);
        TextView btnList_thai = findViewById(R.id.drawerLayout_list_btn_thai);
        TextView btnList_jap = findViewById(R.id.drawerLayout_list_btn_jap);
        TextView btnList_airInfo = findViewById(R.id.drawerLayout_list_btn_2);
        TextView btnList_myPage = findViewById(R.id.drawerLayout_list_btn_3);

        btn_list.setOnClickListener(new View.OnClickListener() {
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
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        // SupportMapFragment을 통해 레이아웃에 만든 fragment의 ID를 참조하고 구글맵을 호출한다.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //getMapAsync must be called on the main thread.

        spinner();
//        init1();
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
                    init1();
                }else if (spinner.getItemAtPosition(i).toString().equals("2일차")){
                    //Toast.makeText(Theme.this, "2일차 클릭", Toast.LENGTH_SHORT).show();
                    init2();
                }else if (spinner.getItemAtPosition(i).toString().equals("3일차")) {
                    //Toast.makeText(Theme.this, "3일차 클릭", Toast.LENGTH_SHORT).show();
                    init3();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init1() {
        img_morning.setImageResource(list[0]);
        img_afternoon.setImageResource(list[1]);
        img_lunch.setImageResource(list[2]);
        img_dinner.setImageResource(list[3]);

        initList = new int[]{
                convertResID(list[0]),
                convertResID(list[1]),
                convertResID(list[2]),
                convertResID(list[3])
        };

        oneMarker();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init2() {
        img_morning.setImageResource(list[4]);
        img_afternoon.setImageResource(list[5]);
        img_lunch.setImageResource(list[6]);
        img_dinner.setImageResource(list[7]);

        initList = new int[]{
                convertResID(list[4]),
                convertResID(list[5]),
                convertResID(list[6]),
                convertResID(list[7])
        };

        oneMarker();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init3() {
        img_morning.setImageResource(list[8]);
        img_afternoon.setImageResource(list[9]);
        img_lunch.setImageResource(list[10]);
        img_dinner.setImageResource(list[11]);

        initList = new int[]{
                convertResID(list[8]),
                convertResID(list[9]),
                convertResID(list[10]),
                convertResID(list[11])
        };

        oneMarker();
    }

    private int convertResID(int resid) {
        return Integer.parseInt(getResources().getResourceEntryName(resid).substring(4));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override //구글맵을 띄울준비가 됬으면 자동호출된다.
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //지도타입 - 일반
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setPadding(0, 150,0, 0);
        init1();
        // manyMarker();
    }

    //마커하나찍는 기본 예제
    public void oneMarker() {
        ArrayList<LatLng> arrayPoints = new ArrayList<>();
        LatLng firstCamera = new LatLng(0, 0);
        MarkerOptions makerOptions = new MarkerOptions();
        PolylineOptions polylineOptions = new PolylineOptions();
        
        if(wb != null) {
            Sheet sheet = wb.getSheet(0);   // 시트 불러오기
            if(sheet != null) {
                int colTotal = sheet.getColumns();    // 전체 컬럼
                boolean flag = true;

                String kor_title = "";
                String kor_dist = "";

                for (int row : initList) {
                    for(int col=0;col<colTotal;col++) {
                        String contents = sheet.getCell(col, row).getContents();

                        if(col == 1)
                            kor_title = contents;
                        else if(col == 3)
                            kor_dist = contents;
                        else if(col == 4)
                            Lat = Double.parseDouble(contents);
                        else if(col == 5)
                            Lng = Double.parseDouble(contents);
                        else if(col == colTotal - 1) {
                            if (flag) {
                                firstCamera = new LatLng(Lat, Lng);
                                mMap.clear();
                                flag = false;
                            }
                            // 1. 마커 옵션 설정 (만드는 과정)
                            makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                                    .position(new LatLng(Lat, Lng))
                                    .title(kor_title) // 타이틀.
                                    .snippet(kor_dist)
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                                    .alpha(0.8f);
                            // 2. 마커 생성 (마커를 나타냄)
                            mMap.addMarker(makerOptions);

                            arrayPoints.add(new LatLng(Lat, Lng));
                            polylineOptions
                                    .color(Color.RED)
                                    .width(5)
                                    .addAll(arrayPoints);
                            // 3. 경로 생성
                            mMap.addPolyline(polylineOptions);
                        }
                    }
                }
            }
        }

        //처음 줌 레벨 설정 (해당좌표=>서울, 줌레벨을 매개변수로 넣으면 된다.)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstCamera, 18));
    }
}