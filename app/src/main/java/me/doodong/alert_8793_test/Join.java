package me.doodong.alert_8793_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;


public class Join extends AppCompatActivity {
    EditText editText_id, editText_pw, editText_pwck, editText_email, editText_gender, editText_age;
    AppCompatImageButton btn_free, btn_backpacking, btn_pkg, btn_alone, btn_together;
    Button btn_join;
    int CHECK_NUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Toolbar toolbar = findViewById(R.id.toolbar);


        editText_id = findViewById(R.id.editText_id);
        editText_pw = findViewById(R.id.editText_pw);
        editText_pwck = findViewById(R.id.editText_pwck);
        editText_email = findViewById(R.id.editText_email);
        editText_gender = findViewById(R.id.editText_gender);
        editText_age = findViewById(R.id.editText_age);


        btn_free= findViewById(R.id.btn_free);
        btn_backpacking= findViewById(R.id.btn_backpacking);
        btn_pkg= findViewById(R.id.btn_pkg);
        btn_alone= findViewById(R.id.btn_alone);
        btn_together= findViewById(R.id.btn_together);
        btn_join= findViewById(R.id.btn_join);

        Intent intent = getIntent();

        btn_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CHECK_NUM == 0) {
                    btn_free.setImageResource(R.drawable.freetrip_click);
                    CHECK_NUM = 1; // 다음에 누르면 색이 변하도록 값을 변경.
                }else{//CHECK_NUM 0이 아니면 setSelected를 false로 줘서 .
                    btn_free.setImageResource(R.drawable.freetrip);
                    CHECK_NUM = 0; //다음에 누르면 색이 변하도록 값을 변경
                }
            }
        });

        btn_backpacking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CHECK_NUM == 0) {
                    btn_backpacking.setImageResource(R.drawable.backpacking_click);
                    CHECK_NUM = 1; // 다음에 누르면 색이 변하도록 값을 변경.
                }else{//CHECK_NUM 0이 아니면 setSelected를 false로 줘서 .
                    btn_backpacking.setImageResource(R.drawable.backpacking);
                    CHECK_NUM = 0; //다음에 누르면 색이 변하도록 값을 변경
                }
            }
        });

        btn_pkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CHECK_NUM == 0) {
                    btn_pkg.setImageResource(R.drawable.pkg_click);
                    CHECK_NUM = 1; // 다음에 누르면 색이 변하도록 값을 변경.
                }else{//CHECK_NUM 0이 아니면 setSelected를 false로 줘서 .
                    btn_pkg.setImageResource(R.drawable.pkg_icon);
                    CHECK_NUM = 0; //다음에 누르면 색이 변하도록 값을 변경
                }
            }
        });

        btn_alone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CHECK_NUM == 0) {
                    btn_alone.setImageResource(R.drawable.alone_click);
                    CHECK_NUM = 1; // 다음에 누르면 색이 변하도록 값을 변경.
                }else{//CHECK_NUM 0이 아니면 setSelected를 false로 줘서 .
                    btn_alone.setImageResource(R.drawable.alone);
                    CHECK_NUM = 0; //다음에 누르면 색이 변하도록 값을 변경
                }
            }
        });

        btn_together.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CHECK_NUM == 0) {
                    btn_together.setImageResource(R.drawable.together_click);
                    CHECK_NUM = 1; // 다음에 누르면 색이 변하도록 값을 변경.
                }else{//CHECK_NUM 0이 아니면 setSelected를 false로 줘서 .
                    btn_together.setImageResource(R.drawable.together);
                    CHECK_NUM = 0; //다음에 누르면 색이 변하도록 값을 변경
                }
            }
        });

    }

    public void onBackPressed(View view) {
        finish();
    }

     public void  onClick_realJoin(View view) {
        Intent intent = new Intent(getApplicationContext(), AfterLoginActivity.class);
        startActivity(intent);
    }
}

