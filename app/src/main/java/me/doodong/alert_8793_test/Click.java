package me.doodong.alert_8793_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


public class Click extends AppCompatActivity {
    //TextView txtText;
    LinearLayout layout;

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
       /* String data = intent.getStringExtra("data");
        txtText.setText(data);*/

    }

    //확인 버튼 클릭
    public void click_OK(View v){
        //데이터 전달하기
        Intent intent = new Intent(getApplicationContext(), my_page.class);
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        startActivity(intent);
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
