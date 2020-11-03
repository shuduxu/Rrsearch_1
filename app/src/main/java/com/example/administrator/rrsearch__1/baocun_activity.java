package com.example.administrator.rrsearch__1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.rrsearch__1.MainActivity.dbHelper;
import static com.example.administrator.rrsearch__1.fajue_activity.wenwu;

public class baocun_activity extends AppCompatActivity implements View.OnClickListener{

    Button buttonback,baocun_button,chaxun;
    Spinner spinner;
    EditText editText1,editText2;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.baocun_activity);
        buttonback=(Button)findViewById(R.id.button_backfff);
        chaxun=(Button)findViewById(R.id.b_chaxun);
        baocun_button=(Button)findViewById(R.id.bbutton_baocun);
        spinner=(Spinner)findViewById(R.id.bspinner);
        editText1=(EditText)findViewById(R.id.beditText_1);
        editText2=(EditText)findViewById(R.id.beditText_2);
        buttonback.setOnClickListener(this);
        chaxun.setOnClickListener(this);
        baocun_button.setOnClickListener(this);
        querywenwu();
        MyAdapter adapter = new MyAdapter(this);
        spinner.setAdapter(adapter);
        adapter.setDatas(list);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void querywenwu()
    {
        String sql_query="select Wenwu_name from Wenwu_Book" +
                " where Situation='cunchu'";
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        Cursor cursor =sdb.rawQuery(sql_query,null);
        if(cursor!=null&&cursor.moveToFirst())
        {
            do{
                String wenwu_name=cursor.getString(cursor.getColumnIndex("Wenwu_name"));
                if (!wenwu_name.isEmpty())
                {
                    list.add(wenwu_name);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_backfff)
        {
            Intent intent =new Intent(baocun_activity.this,third_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            baocun_activity.this.finish();
        }
        else if(v.getId()==R.id.b_chaxun)
        {
            if(spinner.getSelectedItem()==null)
            {
                Toast.makeText(this, "數據为空！", Toast.LENGTH_SHORT).show();
            }
            else
            {
                wenwu=spinner.getSelectedItem().toString();
                Intent intent =new Intent(baocun_activity.this,fajue2_activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
                baocun_activity.this.finish();
            }
        }
        else if(v.getId()==R.id.bbutton_baocun)
        {
            SQLiteDatabase sdb= dbHelper.getWritableDatabase();
            String a=editText1.getText().toString();
            String b=editText2.getText().toString();
            String c=null;
            String sql="update Wenwu_Book set Situation='cunchu',Worker_ID=?,Bumen_ID='10001' where Wenwu_Id=?";
            String sql1="select Worker_ID from Worker_Book where Worker_name=? and Bumen_ID='10001'";
            Cursor cursor=sdb.rawQuery(sql1,new String[]{b});
            if(a.isEmpty()||b.isEmpty())
            {
                Toast.makeText(this, "請輸入完整數據！", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(cursor.moveToFirst()==true)
                {
                    c=cursor.getString(cursor.getColumnIndex("Worker_ID"));
                    sdb.execSQL(sql,new String[]{c,a});
                    Toast.makeText(this, "保存數據已更新！", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "查無此人！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}