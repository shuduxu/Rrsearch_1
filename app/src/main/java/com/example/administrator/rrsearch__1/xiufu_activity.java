package com.example.administrator.rrsearch__1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.rresearch__1.MainActivity.dbHelper;
import static com.example.administrator.rresearch__1.fajue_activity.wenwu;

public class xiufu_activity extends AppCompatActivity implements View.OnClickListener{

    Button buttonback,xiufu_button,chaxun;
    Button wancheng;
    Spinner spinner;
    EditText editText1,editText2;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.xiufu_activity);
        buttonback=(Button)findViewById(R.id.button_backffff);
        chaxun=(Button)findViewById(R.id.x_chaxun);
        xiufu_button=(Button)findViewById(R.id.cbutton_xiufu);
        wancheng=(Button)findViewById(R.id.cbutton_wancheng);
        spinner=(Spinner)findViewById(R.id.xspinner);
        editText1=(EditText)findViewById(R.id.xeditText_1);
        editText2=(EditText)findViewById(R.id.xeditText_2);
        buttonback.setOnClickListener(this);
        chaxun.setOnClickListener(this);
        xiufu_button.setOnClickListener(this);
        wancheng.setOnClickListener(this);
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
                " where Situation='xiufu'";
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
        if(v.getId()==R.id.button_backffff)
        {
            Intent intent =new Intent(xiufu_activity.this,third_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            xiufu_activity.this.finish();
        }
        else if(v.getId()==R.id.x_chaxun)
        {

            if(spinner.getSelectedItem()==null)
            {
                Toast.makeText(this, "數據为空！", Toast.LENGTH_SHORT).show();
            }
            else
            {
                wenwu=spinner.getSelectedItem().toString();
                Intent intent =new Intent(xiufu_activity.this,fajue2_activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
                xiufu_activity.this.finish();
            }
        }
        else if(v.getId()==R.id.cbutton_xiufu)
        {
            SQLiteDatabase sdb= dbHelper.getWritableDatabase();
            String a=editText1.getText().toString();
            String b=editText2.getText().toString();
            String c=null;
            String sql="update Wenwu_Book set Situation='xiufu',Worker_ID=?,Bumen_ID='10000',tag='yes' where Wenwu_Id=?";
            String sql1="select Worker_ID from Worker_Book where Worker_name=? and Bumen_ID='10002'";
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
                    Toast.makeText(this, "修復數據已更新！", Toast.LENGTH_SHORT).show();
                    cursor.close();
                }
                else
                {
                    Toast.makeText(this, "查無此人！", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(v.getId()==R.id.cbutton_wancheng)
        {
            String sql="update Wenwu_Book set Situation='cunchu',Bumen_ID='10001' where Wenwu_name=?";
            SQLiteDatabase sdb= dbHelper.getReadableDatabase();
            sdb.execSQL(sql,new String[]{spinner.getSelectedItem().toString()});
            Toast.makeText(this, "修復完成！", Toast.LENGTH_SHORT).show();

        }

    }
}