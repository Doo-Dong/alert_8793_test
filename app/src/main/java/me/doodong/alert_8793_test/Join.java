package me.doodong.alert_8793_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;


public class Join extends AppCompatActivity {

    TextView tv_gender, tv_age;
    ImageButton btn_free, btn_backpacking, btn_pkg, btn_alone, btn_together;
    Button btn_join;
    ArrayAdapter<CharSequence> gender, age;

    //private boolean freetrip, backpacking, pkg, alone, together;
    boolean[] bl = new boolean[]{
      true, true, true, true, true
    };
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
               if(bl[0]){//true 일 때
                   btn_free.setBackgroundResource(R.drawable.freetrip_click);
                   bl[0] = false;
               } else if (!double_btn()) {//false 일 때
                   btn_free.setBackgroundResource(R.drawable.freetrip_color);
                   bl[0] = true;
               }
            }
        });

        btn_backpacking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bl[1]){//true 일 때
                    btn_backpacking.setBackgroundResource(R.drawable.backpacking);
                    bl[1] = false;
                }else if (!double_btn()) {//false 일 때
                    btn_backpacking.setBackgroundResource(R.drawable.backpacking_color);
                    bl[1] = true;
                }
            }
        });
        btn_pkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bl[2]){//true 일 때
                    btn_pkg.setBackgroundResource(R.drawable.pkg_icon);
                    bl[2] = false;
                }else if (!double_btn()) {//false 일 때
                    btn_pkg.setBackgroundResource(R.drawable.pkg_icon_color);
                    bl[2] = true;
                }
            }
        });
        btn_alone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bl[3]){//true 일 때
                    btn_alone.setBackgroundResource(R.drawable.alone);
                    bl[3] = false;
                }else if (!double_btn()) {//false 일 때
                    btn_alone.setBackgroundResource(R.drawable.alone_color);
                    bl[3] = true;
                }
            }
        });
        btn_together.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bl[4]){//true 일 때
                    btn_together.setBackgroundResource(R.drawable.together);
                    bl[4] = false;
                }else if (!double_btn()) {//false 일 때
                    btn_together.setBackgroundResource(R.drawable.together_color);
                    bl[4] = true;
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

    public boolean double_btn() {
        int bool_cnt = 0;

        for (boolean b : bl) {
            if (!b) {
                bool_cnt++;
            }
        }

        return (bool_cnt < 4) ? true : false;
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

        } else {
            // 로그인이 성공했을때
            try {
                FileInputStream fis = new FileInputStream("/data/data/me.doodong.alert_8793_test/cache/copied_alert_join_us.xls");
                Workbook wb = Workbook.getWorkbook(fis);

                // 외장메모리에 엑셀파일을 저장하기 위한 디렉토리
                String strDir = "/data/data/me.doodong.alert_8793_test/cache";
                // 새로 생성하는 엑셀파일명
                String strFileName = "copied_alert_join_us.xls";

                File file = new File(strDir, strFileName);
                if(!file.exists()) {
                    file.createNewFile();
                }

                WritableWorkbook wwb = Workbook.createWorkbook(file, wb);

                String[] list;

                list = new String[] {
                        editText_id.getText().toString(),
                        editText_id.getText().toString(),
                        editText_email.getText().toString(),
                        ""
                };

                if(wb != null) {
                    WritableSheet sheet = wwb.getSheet(0);   // 시트 불러오기
                    if(sheet != null) {
                        int colTotal = sheet.getColumns();    // 전체 컬럼
                        //int rowIndexStart = 1;                  // row 인덱스 시작
                        int rowTotal = sheet.getColumn(colTotal-1).length;

                        for(int col=0;col<colTotal;col++) {
                            jxl.write.Label label = new Label(col, rowTotal,
                                    list[col]
                                    );
                            sheet.addCell(label);
                            Log.i("XLS SAVE : " + rowTotal + " / " + col + "||", list[col]);
                        }
                        wwb.write();
                        wwb.close();
                        wb.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }

    }

}

