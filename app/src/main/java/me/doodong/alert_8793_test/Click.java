package me.doodong.alert_8793_test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;


public class Click extends AppCompatActivity {
    //TextView txtText;
    LinearLayout layout;
    ArrayAdapter<CharSequence> click1, click2;
    String position, day, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_click);

        //UI 객체생성
        //txtText = (TextView)findViewById(R.id.txtText);

        //데이터 가져오기
        Intent intent = getIntent();
        position = intent.getStringExtra("position");
       /* String data = intent.getStringExtra("data");
        txtText.setText(data);*/

        spinner_click1();
        spinner_click2();


    }

    public void spinner_click1(){
        final AppCompatSpinner spinner = findViewById(R.id.click_spinner1);
        click1 = ArrayAdapter.createFromResource(this, R.array.click_spinner1, android.R.layout.simple_spinner_dropdown_item);
        click1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(click1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setSelected(true);
                ((TextView) adapterView.getChildAt(0)).setTextSize(10);
                ((TextView) adapterView.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                ((TextView) adapterView.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                day = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void spinner_click2(){
        AppCompatSpinner spinner = findViewById(R.id.click_spinner2);
        click2 = ArrayAdapter.createFromResource(this, R.array.click_spinner2, android.R.layout.simple_spinner_dropdown_item);
        click2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(click2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setSelected(true);
                ((TextView) adapterView.getChildAt(0)).setTextSize(10);
                ((TextView) adapterView.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
                ((TextView) adapterView.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                time = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //확인 버튼 클릭
    public void click_OK(View v){
        //데이터 전달하기
        Intent intent = new Intent(getApplicationContext(), my_page.class);
        intent.putExtra("result", "Close Popup");
        intent.putExtra("position", position);
        intent.putExtra("day", day);
        intent.putExtra("time", time);
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    //취소 버튼 클릭
    public void click_Cancel(View v){

        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}
