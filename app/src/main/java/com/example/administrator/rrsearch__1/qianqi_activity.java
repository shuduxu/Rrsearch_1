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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.edit;
import static com.example.administrator.rresearch__1.MainActivity.dbHelper;

public class qianqi_activity extends AppCompatActivity implements View.OnClickListener{

    private Button button_back1,button_chaxun1,button_pizhun;
    private Button buttonqueren,buttonqvxiao;
    private Spinner spinner;
    private EditText edit_id,edit_name,edit_jieshao,edit_budget;
    public static String kaogunamme;
    List <String>list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.qianqi_activity);
        button_back1=(Button)findViewById(R.id.button_back1);
        button_chaxun1=(Button)findViewById(R.id.button_chaxun);
        buttonqueren=(Button)findViewById(R.id.button_queren);
        buttonqvxiao=(Button)findViewById(R.id.button_quxiao);
        button_pizhun=(Button)findViewById(R.id.button_pizhun1);
        edit_id=(EditText)findViewById(R.id.editText_id);
        edit_budget=(EditText)findViewById(R.id.editText_budget);
        edit_name=(EditText)findViewById(R.id.editText_name);
        edit_jieshao=(EditText)findViewById(R.id.editText_jieshao);
        spinner=(Spinner)findViewById(R.id.spinner);
        button_back1.setOnClickListener(this);
        button_chaxun1.setOnClickListener(this);
        buttonqueren.setOnClickListener(this);
        buttonqvxiao.setOnClickListener(this);
        button_pizhun.setOnClickListener(this);
        queryData();
        MyAdapter adapter = new MyAdapter(this);
        spinner.setAdapter(adapter);
        adapter.setDatas(list);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TextView tv = (TextView)view;
                //tv.setTextSize(20.0f);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void queryData()
    {
        String sql_query="select Kaogudi_name from Kaogudi_Book" +
                " where Kaogudi_name is not null";
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        Cursor cursor =sdb.rawQuery(sql_query,null);
        if(cursor!=null&&cursor.moveToFirst())
        {
            do{
                String kaogudi_name=cursor.getString(cursor.getColumnIndex("Kaogudi_name"));
                if (!kaogudi_name.isEmpty())
                {
                    list.add(kaogudi_name);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_back1)
        {
            Intent intent=new Intent(qianqi_activity.this,third_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            qianqi_activity.this.finish();
        }
        else if(v.getId()==R.id.button_chaxun)
        {
            kaogunamme=spinner.getSelectedItem().toString();
            Intent intent=new Intent(qianqi_activity.this,qianqi1_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            qianqi_activity.this.finish();
        }
        else if(v.getId()==R.id.button_quxiao)
        {
            Intent intent=new Intent(qianqi_activity.this,third_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            qianqi_activity.this.finish();
        }
        else if(v.getId()==R.id.button_queren)
        {
            String editid=edit_id.getText().toString();
            String editname=edit_name.getText().toString();
            String editbudget=edit_budget.getText().toString();
            String editjieshao=edit_jieshao.getText().toString();
            if(editid.isEmpty())
            {
                Toast.makeText(this, "請輸入考古地點的編號！", Toast.LENGTH_SHORT).show();
            }
            else
            {
                SQLiteDatabase sdb=dbHelper.getWritableDatabase();
                String sql="select Kaogudi_ID from Kaogudi_Book where Kaogudi_ID= ? ";
                Cursor cursor = sdb.rawQuery(sql,new String[]{editid});
                if(cursor.moveToFirst()==true&&editname.isEmpty()&&editjieshao.isEmpty()&&editbudget.isEmpty())
                {
                    Toast.makeText(this, "編號已經存在！", Toast.LENGTH_SHORT).show();
                    cursor.close();
                }
                else if(cursor.moveToFirst()==true&&!editbudget.isEmpty())
                {
                    String sql2="update Kaogudi_Book set Budget=?" +
                            "where Kaogudi_ID=?";
                    if(!editname.isEmpty())
                    {
                        String sql3 ="update Kaogudi_Book set Kaogudi_name=?" +
                                "where Kaogudi_ID=?";
                        sdb.execSQL(sql3,new String[]{editname,editid});
                    }
                    sdb.execSQL(sql2,new String[]{editbudget,editid});
                    Toast.makeText(this, "考古地數據已更新！", Toast.LENGTH_SHORT).show();
                    cursor.close();
                }
                else if(cursor.moveToFirst()==false)
                {
                    String sql1="insert into Kaogudi_Book (Kaogudi_ID,Kaogudi_name,Budget,Kaogudi_jieshao) values" +
                            "(?,?,?,?)";
                    sdb.execSQL(sql1,new String[]{editid,editname,editbudget,editjieshao});
                    Toast.makeText(this, "成功插入考古地數據！", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "錯誤的修改/更新方法！", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(v.getId()==R.id.button_pizhun1)
        {
            SQLiteDatabase sdb=dbHelper.getWritableDatabase();
            String ifa = spinner.getSelectedItem().toString();
            String sql3="update Kaogudi_Book set ifagreed='yes'" +
                    "where Kaogudi_name=?";
            sdb.execSQL(sql3,new String[]{ifa});
            Toast.makeText(this, ifa+"已被允許發掘！", Toast.LENGTH_SHORT).show();
        }

    }
}
