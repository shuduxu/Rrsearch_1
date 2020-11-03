package com.example.administrator.rrsearch__1;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Camera;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.R.id.edit;
import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.administrator.rrsearch__1.MainActivity.dbHelper;
import static com.example.administrator.rrsearch__1.R.id.checkbox;
import static com.example.administrator.rrsearch__1.R.id.fajue_activity;
import static com.example.administrator.rrsearch__1.R.id.spinner;
import static com.example.administrator.rrsearch__1.R.id.third_activity;

public class fajue_activity extends AppCompatActivity implements View.OnClickListener{

    Button buttonf,button_cha3,button_camera;
    Button button_cha1,button_cha2;
    Button button_quxiao,button_queren;
    CheckBox checkBox;
    Spinner spinner1,spinner2,spinner3;
    EditText editText11,editText12,editText13;
    List<String>list1=new ArrayList<>();
    List<String>list2=new ArrayList<>();
    List<String>list3=new ArrayList<>();
    public static String wenwu;
    public static boolean check_linshi=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.fajue_activity);
        querykaogudi();
        querybumen();
        querywenwu();
        buttonf=(Button)findViewById(R.id.button_backf);
        button_cha1=(Button)findViewById(R.id.button_cha1);
        button_cha2=(Button)findViewById(R.id.button_cha2);
        button_cha3=(Button)findViewById(R.id.button_cha3);
        button_camera=(Button)findViewById(R.id.button_camera);
        button_quxiao=(Button)findViewById(R.id.button_quxiao1);
        button_queren=(Button)findViewById(R.id.button_queren1);
        checkBox=(CheckBox)findViewById(R.id.checkBox2);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        spinner3=(Spinner)findViewById(R.id.spinner3);
        editText11=(EditText)findViewById(R.id.editText_id11);
        editText12=(EditText)findViewById(R.id.editText_id12);
        editText13=(EditText)findViewById(R.id.editText_id13);
        buttonf.setOnClickListener(this);
        button_cha1.setOnClickListener(this);
        button_cha2.setOnClickListener(this);
        button_cha3.setOnClickListener(this);
        button_camera.setOnClickListener(this);
        button_queren.setOnClickListener(this);
        button_quxiao.setOnClickListener(this);
        MyAdapter adapter1 = new MyAdapter(this);
        MyAdapter adapter2 = new MyAdapter(this);
        MyAdapter adapter3 = new MyAdapter(this);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        adapter1.setDatas(list1);
        adapter2.setDatas(list2);
        adapter3.setDatas(list3);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    check_linshi=true;
                    Toast.makeText(fajue_activity.this,"臨時人員已選擇！", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    check_linshi=false;
                }
            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public boolean shenhe()
    {
        String kaogudi_=spinner1.getSelectedItem().toString();
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select ifagreed from Kaogudi_Book where Kaogudi_name=?";
        Cursor cursor =sdb.rawQuery(sql,new String[]{kaogudi_});
        if(cursor.moveToFirst()==true)
        {
            String a="yes";
            String b=cursor.getString(cursor.getColumnIndex("ifagreed"));
            if(b.equals(a))
            {
                return true;
            }
            cursor.close();
        }

        return false;
    }
    public void querykaogudi()
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
                    list1.add(kaogudi_name);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
    public void querybumen()
    {
        String sql_query="select Bumen_name from Bumen_Book" +
                " where Bumen_name is not null";
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        Cursor cursor =sdb.rawQuery(sql_query,null);
        if(cursor!=null&&cursor.moveToFirst())
        {
            do{
                String bumen_name=cursor.getString(cursor.getColumnIndex("Bumen_name"));
                if (!bumen_name.isEmpty())
                {
                    list2.add(bumen_name);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }
    public void querywenwu()
    {
        String sql_query="select Wenwu_name from Wenwu_Book" +
                " where Wenwu_name is not null";
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        Cursor cursor =sdb.rawQuery(sql_query,null);
        if(cursor!=null&&cursor.moveToFirst())
        {
            do{
                String wenwu_name=cursor.getString(cursor.getColumnIndex("Wenwu_name"));
                if (!wenwu_name.isEmpty())
                {
                    list3.add(wenwu_name);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_backf)
        {
            Intent intent =new Intent(fajue_activity.this,third_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            fajue_activity.this.finish();
        }
        else if(v.getId()==R.id.button_cha2)
        {
            Toast.makeText(this, "部門已確認！", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.button_cha3)
        {
            wenwu=spinner3.getSelectedItem().toString();
            Intent intent =new Intent(fajue_activity.this,fajue2_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            fajue_activity.this.finish();
        }
        else if(v.getId()==R.id.button_camera)
        {

        }
        else if(v.getId()==R.id.button_queren1&&shenhe())
        {
            String editid=editText11.getText().toString();
            String editfuze=editText12.getText().toString();
            String editwenwu=editText13.getText().toString();
            if(editid.isEmpty())
            {
                Toast.makeText(this, "請輸入考古文物的編號！", Toast.LENGTH_SHORT).show();
            }
            else
            {
                String bumen =spinner2.getSelectedItem().toString();
                String kaogudi=spinner1.getSelectedItem().toString();
                SQLiteDatabase sdb=dbHelper.getWritableDatabase();
                String sql="select Wenwu_Id from Wenwu_Book where Wenwu_Id= ? ";
                Cursor cursor = sdb.rawQuery(sql,new String[]{editid});
                String id="";String bd="";
                if(!editfuze.isEmpty())
                {
                    String sql1="select Worker_ID,Bumen_ID from Worker_Book where Worker_name =?";
                    Cursor cursor1 =sdb.rawQuery(sql1,new String[]{editfuze});
                    if(cursor1.moveToFirst()==true)
                    {
                        id = cursor1.getString(cursor1.getColumnIndex("Worker_ID"));
                        bd=  cursor1.getString(cursor1.getColumnIndex("Bumen_ID"));
                        cursor1.close();
                    }
                    else
                    {
                        Toast.makeText(this, "查無此人！", Toast.LENGTH_SHORT).show();
                    }
                }
                if(cursor.moveToFirst()==true&&editfuze.isEmpty())
                {
                    Toast.makeText(this, "編號已經存在！", Toast.LENGTH_SHORT).show();
                    cursor.close();
                }
                else if(cursor.moveToFirst()==true&&!editfuze.isEmpty())
                {
                    String sql2="update Wenwu_Book set Worker_ID=?,Bumen_ID=?" +
                            " where Wenwu_Id=?";
                    sdb.execSQL(sql2,new String[]{id,bd,editid});
                    Toast.makeText(this,"考古文物數據已更新！", Toast.LENGTH_SHORT).show();
                    cursor.close();
                }
                else if(cursor.moveToFirst()==false)
                {
                    String sql_1="select Bumen_ID from Bumen_Book where Bumen_name=?";
                    String sql_2="select Kaogudi_ID from Kaogudi_Book where Kaogudi_name=?";
                    String bumenid=null, kaogudiid=null;
                    Cursor cursor_1=sdb.rawQuery(sql_1,new String[]{bumen});
                    Cursor cursor_2=sdb.rawQuery(sql_2,new String[]{kaogudi});
                    if(cursor_1.moveToFirst()==true)
                    {
                        bumenid=cursor_1.getString(cursor_1.getColumnIndex("Bumen_ID"));
                    }
                    if(cursor_2.moveToFirst()==true)
                    {
                        kaogudiid=cursor_2.getString(cursor_2.getColumnIndex("Kaogudi_ID"));
                    }
                    String sql1="insert into Wenwu_Book (Wenwu_Id,Wenwu_name,Worker_ID,Kaogudi_ID,Bumen_ID) values" +
                            "(?,?,?,?,?)";
                    if(bumenid.equals(bd))
                    {
                        sdb.execSQL(sql1,new String[]{editid,editwenwu,id,kaogudiid,bumenid});
                        Toast.makeText(this, "成功插入文物數據！", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "工作人員與部門不壹致！", Toast.LENGTH_SHORT).show();
                    }
                    cursor_1.close();
                    cursor_2.close();
                }
            }
        }
        else if(v.getId()==R.id.button_quxiao1)
        {
            Intent intent =new Intent(fajue_activity.this,third_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            fajue_activity.this.finish();
        }
        else if(v.getId()==R.id.button_queren1&&shenhe()==false)
        {
            Toast.makeText(this, spinner1.getSelectedItem().toString()+"未審核發掘！", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.button_cha1&&shenhe())
        {
            Toast.makeText(this, spinner1.getSelectedItem().toString()+"已經審核發掘！", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId()==R.id.button_cha1&&!shenhe())
        {
            Toast.makeText(this, spinner1.getSelectedItem().toString()+"未審核發掘！", Toast.LENGTH_SHORT).show();
        }
    }
}
