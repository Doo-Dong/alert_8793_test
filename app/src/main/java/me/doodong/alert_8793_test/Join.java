package me.doodong.alert_8793_test;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Join extends AppCompatActivity {
    EditText editText_id, editText_pw, editText_pwck, editText_email;
    TextView tv_gender, tv_age;
    Button btn_free, btn_backpacking, btn_pkg, btn_alone, btn_together;
    Button btn_join;
    ArrayAdapter<CharSequence> gender, age;

    private boolean freetrip, backpacking, pkg, alone, together;
    Workbook wb;

    String join_id = "";
    String join_name = "";
    String join_pw = "";
    String join_pwck = "";
    String join_email = "";
    String join_phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_test);

        Toolbar toolbar = findViewById(R.id.toolbar);

        editText_id = findViewById(R.id.editText_id);
        editText_pw = findViewById(R.id.editText_pw);
        editText_pwck = findViewById(R.id.editText_pwck);
        editText_email = findViewById(R.id.editText_email);
        tv_gender = findViewById(R.id.tv_gender);
        tv_age = findViewById(R.id.tv_age);


        btn_free= findViewById(R.id.btn_free);
        btn_backpacking= findViewById(R.id.btn_backpacking);
        btn_pkg= findViewById(R.id.btn_pkg);
        btn_alone= findViewById(R.id.btn_alone);
        btn_together= findViewById(R.id.btn_together);
        btn_join= findViewById(R.id.btn_join);

        Intent intent = getIntent();

        join_id = editText_id.getText().toString();
        join_pw = editText_pw.getText().toString();
        join_pwck = editText_pwck.getText().toString();
        join_email = editText_email.getText().toString();

        try {
            InputStream is = getBaseContext().getResources().getAssets().open("alert_join_us.xls");
            wb = Workbook.getWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

        excel_load();
        spinner_age();
        spinner_gender();

        btn_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(freetrip){//true 일 때
                   btn_free.setBackgroundResource(R.drawable.freetrip);
                   freetrip = false;
               }else {//false 일 때
                   btn_free.setBackgroundResource(R.drawable.freetrip_click);
                   freetrip = true;
               }
            }
        });

        btn_backpacking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(backpacking){//true 일 때
                    btn_backpacking.setBackgroundResource(R.drawable.backpacking);
                    backpacking = false;
                }else {//false 일 때
                    btn_backpacking.setBackgroundResource(R.drawable.backpacking_click);
                    backpacking = true;
                }
            }
        });
        btn_pkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pkg){//true 일 때
                    btn_pkg.setBackgroundResource(R.drawable.pkg_icon);
                    pkg = false;
                }else {//false 일 때
                    btn_pkg.setBackgroundResource(R.drawable.pkg_click);
                    pkg = true;
                }
            }
        });
        btn_alone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alone){//true 일 때
                    btn_alone.setBackgroundResource(R.drawable.alone);
                    alone = false;
                }else {//false 일 때
                    btn_alone.setBackgroundResource(R.drawable.alone_click);
                    alone = true;
                }
            }
        });
        btn_together.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(together){//true 일 때
                    btn_together.setBackgroundResource(R.drawable.together);
                    together = false;
                }else {//false 일 때
                    btn_together.setBackgroundResource(R.drawable.together_click);
                    together = true;
                }
            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), AfterLoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();

            }
        });
    }

    public void spinner_gender(){
        final AppCompatSpinner spinner = findViewById(R.id.spinner_gender);
        gender = ArrayAdapter.createFromResource(this, R.array.gender_spinner, android.R.layout.simple_spinner_dropdown_item);
        gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(gender);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextSize(15);
                ((TextView) adapterView.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void spinner_age(){
        final AppCompatSpinner spinner = findViewById(R.id.spinner_age);
        age = ArrayAdapter.createFromResource(this, R.array.age_spinner, android.R.layout.simple_spinner_dropdown_item);
        age.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(age);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextSize(15);
                ((TextView) adapterView.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void excel_load(){

        if(wb != null) {
            Sheet sheet = wb.getSheet(0);   // 시트 불러오기
            if(sheet != null) {
                int colTotal = sheet.getColumns();    // 전체 컬럼
                int rowIndexStart = 2;                  // row 인덱스 시작
                int rowTotal = sheet.getColumn(colTotal-1).length;

                String id = "";
                String name = "";
                String email = "";
                String phone = "";

                StringBuilder sb;

                for(int row=rowIndexStart;row<rowTotal;row++) {
                    sb = new StringBuilder();
                    for(int col=0;col<colTotal;col++) {
                        String contents = sheet.getCell(col, row).getContents();
                        sb.append("col"+col+" : "+contents+" , ");
                        //col0:id, col1:name, col2:email, col3:phone
                        if(col == 0) {
                            id = contents;

                        }
                        else if(col == 1) {
                            name = contents;
                        }
                        else if(col == 2){
                            email = contents;

                        }

                        else if(col == 3){
                            phone = contents;

                        }

                    }
                    Log.i("xls_log", sb.toString());
                }
            }
        }


    }



}

