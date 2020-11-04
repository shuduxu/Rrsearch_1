package com.example.administrator.rrsearch__1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.rresearch__1.MainActivity.dbHelper;
import static com.example.administrator.rresearch__1.fajue_activity.wenwu;

public class zhanlan_activity extends AppCompatActivity implements View.OnClickListener{

    Button buttonback,zhanlan_button,chaxun;
    Button chezhan;
    Spinner spinner;
    EditText editText1,editText2;
    List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.zhanlan_activity);
        buttonback=(Button)findViewById(R.id.button_backfffff);
        chaxun=(Button)findViewById(R.id.z_chaxun);
        zhanlan_button=(Button)findViewById(R.id.zbutton_zhanlan);
        chezhan=(Button)findViewById(R.id.zbutton_chezhan);
        spinner=(Spinner)findViewById(R.id.zspinner);
        editText1=(EditText)findViewById(R.id.zeditText_1);
        editText2=(EditText)findViewById(R.id.zeditText_2);
        buttonback.setOnClickListener(this);
        chaxun.setOnClickListener(this);
        zhanlan_button.setOnClickListener(this);
        chezhan.setOnClickListener(this);
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
                " where Situation='zhanlan'";
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
        if(v.getId()==R.id.button_backfffff)
        {
            Intent intent =new Intent(zhanlan_activity.this,third_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            zhanlan_activity.this.finish();
        }
        else if(v.getId()==R.id.z_chaxun)
        {
            if(spinner.getSelectedItem()==null)
            {
                Toast.makeText(this, "數據为空！", Toast.LENGTH_SHORT).show();
            }
            else
            {
                wenwu=spinner.getSelectedItem().toString();
                Intent intent =new Intent(zhanlan_activity.this,fajue2_activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
                zhanlan_activity.this.finish();
            }
        }
        else if(v.getId()==R.id.zbutton_zhanlan)
        {
            SQLiteDatabase sdb= dbHelper.getWritableDatabase();
            String a=editText1.getText().toString();
            String b=editText2.getText().toString();
            String c=null;
            String sql_1="select tag from  Wenwu_Book where Wenwu_Id=?";
            String sql="update Wenwu_Book set Situation='zhanlan',Worker_ID=?,Bumen_ID='10002' where Wenwu_Id=?";
            String sql1="select Worker_ID from Worker_Book where Worker_name=? and Bumen_ID='10003'";
            Cursor cursor=sdb.rawQuery(sql1,new String[]{b});
            Cursor cursor1=sdb.rawQuery(sql_1,new String[]{a});
            if(a.isEmpty()||b.isEmpty())
            {
                Toast.makeText(this, "請輸入完整數據！", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(cursor1.moveToFirst()==true)
                {
                    c=cursor1.getString(cursor1.getColumnIndex("tag"));
                    if(c.equals("no"))
                    {
                        Toast.makeText(this, "文物未修复，无法展览！", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(cursor.moveToFirst()==true)
                        {
                            c=cursor.getString(cursor.getColumnIndex("Worker_ID"));
                            sdb.execSQL(sql,new String[]{c,a});
                            Toast.makeText(this, "展览數據已更新！", Toast.LENGTH_SHORT).show();
                            cursor.close();
                        }
                        else
                        {
                            Toast.makeText(this, "查無此人！", Toast.LENGTH_SHORT).show();
                        }
                    }
                    cursor1.close();
                }
                else
                {
                    Toast.makeText(this, "文物數據有誤！", Toast.LENGTH_SHORT).show();
                }

            }
        }
        else if(v.getId()==R.id.zbutton_chezhan)
        {
            String sql="update Wenwu_Book set Situation='cunchu',Bumen_ID='10001' where Wenwu_name=?";
            SQLiteDatabase sdb= dbHelper.getReadableDatabase();
            sdb.execSQL(sql,new String[]{spinner.getSelectedItem().toString()});
            Toast.makeText(this, "展览完毕！", Toast.LENGTH_SHORT).show();
        }

    }
}