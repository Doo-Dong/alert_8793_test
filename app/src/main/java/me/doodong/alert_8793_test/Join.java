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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class Join extends AppCompatActivity {

    TextView tv_gender, tv_age;
    ImageButton btn_free, btn_backpacking, btn_pkg, btn_alone, btn_together;
    Button btn_join;
    ArrayAdapter<CharSequence> gender, age;

    private boolean freetrip, backpacking, pkg, alone, together;
    Workbook wb;
    EditText editText_id, editText_pw, editText_pwck, editText_email;
    private boolean validate = false;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_test);

        Toolbar toolbar = findViewById(R.id.toolbar);

        btn_free= findViewById(R.id.btn_free);
        btn_backpacking= findViewById(R.id.btn_backpacking);
        btn_pkg= findViewById(R.id.btn_pkg);
        btn_alone= findViewById(R.id.btn_alone);
        btn_together= findViewById(R.id.btn_together);
        btn_join= findViewById(R.id.btn_join);

        Intent intent = getIntent();

        spinner_age();
        spinner_gender();

        btn_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(freetrip){//true 일 때
                   btn_free.setBackgroundResource(R.drawable.freetrip_color);
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
                    btn_backpacking.setBackgroundResource(R.drawable.backpacking_color);
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
                    btn_pkg.setBackgroundResource(R.drawable.pkg_icon_color);
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
                    btn_alone.setBackgroundResource(R.drawable.alone_color);
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
                    btn_together.setBackgroundResource(R.drawable.together_color);
                    together = true;
                }
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
                ((TextView) adapterView.getChildAt(0)).setTextSize(12);
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
                ((TextView) adapterView.getChildAt(0)).setTextSize(12);
                ((TextView) adapterView.getChildAt(0)).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void join(View view){
        editText_id = (EditText)findViewById(R.id.editText_id);
        editText_pw = (EditText)findViewById(R.id.editText_pw);
        editText_pwck = (EditText)findViewById(R.id.editText_pwck);
        editText_email = (EditText)findViewById(R.id.editText_email);
        tv_gender = findViewById(R.id.tv_gender);
        tv_age = findViewById(R.id.tv_age);


        try {
            InputStream is = getBaseContext().getResources().getAssets().open("alert_join_us.xls");
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
                        Log.i("xls_join", sb.toString());

                        //for login
                        int col = 0;
                        String contents = sheet.getCell(col, row).getContents();
                        if (editText_id.getText().toString().equals(contents)) {
                            validate = true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

        if (editText_id.getText().toString().length()==0){
            Toast.makeText(Join.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
            editText_id.requestFocus();
            return;
        }else if (editText_pw.getText().toString().length()==0){
            Toast.makeText(Join.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            editText_pw.requestFocus();
            return;
        }else if (editText_pwck.getText().toString().length()==0){
            Toast.makeText(Join.this, "비밀번호 확인을 입력하세요.", Toast.LENGTH_SHORT).show();
            editText_pwck.requestFocus();
            return;
        }else if (editText_email.getText().toString().length()==0){
            Toast.makeText(Join.this, "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
            editText_email.requestFocus();
            return;
        }else if(!editText_pw.getText().toString().equals(editText_pwck.getText().toString())){
            Toast.makeText(Join.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            editText_pw.requestFocus();
            return;
        }else if(validate){
            Toast.makeText(Join.this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
            validate = false;

        }else {


            Intent intent = new Intent(getApplicationContext(), AfterLoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();

        }

    }

}

