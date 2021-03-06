package me.doodong.alert_8793_test;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class MainPage_3 extends FragmentActivity implements OnMapReadyCallback {

    //구글맵참조변수
    GoogleMap mMap;

    Workbook wb;
    String position;

    LinearLayout thumbnail;
    TextView title, dist;
    double Lat, Lng;

    String resourceName, realName, type;

    int resID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.BaseTheme);
        setContentView(R.layout.main_page_3);

        thumbnail = findViewById(R.id.main3_thumbnail);
        title = findViewById(R.id.main3_title);
        dist = findViewById(R.id.main3_dist);

        try {
            InputStream is = getBaseContext().getResources().getAssets().open("inform_chiangmai.xls");
            wb = Workbook.getWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

        Intent intent = getIntent();
        position = intent.getStringExtra("position");
        resourceName = intent.getStringExtra("resourceName");
        if (resourceName != null){
            realName = resourceName.substring(40);
            //Toast.makeText(MainPage_3.this, "resourceName : " + resourceName, Toast.LENGTH_SHORT).show();
            Log.d("resourceName : ",resourceName);
            Log.d("realName : ",realName);

        }

        // 전체화면인 DrawerLayout 객체 참조
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        // Drawer 화면(뷰) 객체 참조
        final View drawerView = findViewById(R.id.drawer);

        // 버튼 객체 참조
        Button btnOpenDrawer = findViewById(R.id.drawerLayout_Btn);
        Button btnCloseDrawer = findViewById(R.id.drawerLayout_Btn_close);
        TextView btnList_main = findViewById(R.id.drawerLayout_list_btn_1);
        TextView btnList_thai = findViewById(R.id.drawerLayout_list_btn_thai);
        TextView btnList_jap = findViewById(R.id.drawerLayout_list_btn_jap);
        TextView btnList_airInfo = findViewById(R.id.drawerLayout_list_btn_2);
        TextView btnList_myPage = findViewById(R.id.drawerLayout_list_btn_3);
        Button btnList_include_schedule = findViewById(R.id.include_schedule);

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


        init_main3();

        // 일정포함 버튼 리스너
        btnList_include_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Click.class);
                intent.putExtra("resID", String.valueOf(resID));
                intent.putExtra("type", type);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });



        // SupportMapFragment을 통해 레이아웃에 만든 fragment의 ID를 참조하고 구글맵을 호출한다.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //getMapAsync must be called on the main thread.
    }

    @Override //구글맵을 띄울준비가 됬으면 자동호출된다.
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //지도타입 - 일반
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        oneMarker();
        // manyMarker();
    }

    //마커하나찍는 기본 예제
    public void oneMarker() {
        // 서울 여의도에 대한 위치 설정
        LatLng seoul = new LatLng(Lat, Lng);

        // 구글 맵에 표시할 마커에 대한 옵션 설정  (알파는 좌표의 투명도이다.)
        MarkerOptions makerOptions = new MarkerOptions();
        makerOptions
                .position(seoul)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .alpha(0.8f);

        // 마커를 생성한다. showInfoWindow를 쓰면 처음부터 마커에 상세정보가 뜨게한다. (안쓰면 마커눌러야뜸)
        mMap.addMarker(makerOptions); //.showInfoWindow();


        //정보창 클릭 리스너
        GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String markerId = marker.getId();
                //Toast.makeText(MainPage_3.this, "정보창 클릭 Marker ID : " + markerId, Toast.LENGTH_SHORT).show();
            }
        };

        //마커 클릭 리스너
        GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String markerId = marker.getId();
                //선택한 타겟위치
                LatLng location = marker.getPosition();
                //Toast.makeText(MainPage_3.this, "마커 클릭 Marker ID : " + markerId + "(" + location.latitude + " " + location.longitude + ")", Toast.LENGTH_SHORT).show();
                return false;
            }
        };

        //정보창 클릭 리스너
        mMap.setOnInfoWindowClickListener(infoWindowClickListener);

        //마커 클릭 리스너
        mMap.setOnMarkerClickListener(markerClickListener);

        //처음 줌 레벨 설정 (해당좌표=>서울, 줌레벨을 매개변수로 넣으면 된다.)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 18));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //Toast.makeText(MainPage_3.this, "눌렀습니다!!", Toast.LENGTH_LONG);
                return false;
            }
        });
    }

    private void init_main3() {
        if(wb != null) {
            Sheet sheet = wb.getSheet(0);   // 시트 불러오기
            if(sheet != null) {
                int colTotal = sheet.getColumns();    // 전체 컬럼
                int row  = 0;
                if (position != null){
                    row = Integer.parseInt(position);                  // row 인덱스 시작
                }else if (realName != null){
                    row = Integer.parseInt(realName)-1;                  // row 인덱스 시작
                }

                String kor_title = "";
                String kor_dist = "";

                for(int col=0;col<colTotal;col++) {
                    String contents = sheet.getCell(col, row).getContents();

                    if (col == 0)
                        type = contents;
                    else if(col == 1)
                        kor_title = contents;
                    else if(col == 3)
//                        kor_dist = contents.substring(0, 10) + "...";
                          kor_dist = contents;
                    else if(col == 4)
                        Lat = Double.parseDouble(contents);
                    else if(col == 5)
                        Lng = Double.parseDouble(contents);
                    else if(col == colTotal - 1) {
                        thumbnail.setBackgroundResource(getResources().getIdentifier("@drawable/pic_"+(row+1), "id", this.getPackageName()));
                        title.setText(kor_title);
                        dist.setText(kor_dist);
                        resID = getResources().getIdentifier("pic_"+(row+1), "drawable", this.getPackageName());
                        Log.d("resID", String.valueOf(resID));
                    }
                }
            }
        }
    }
}