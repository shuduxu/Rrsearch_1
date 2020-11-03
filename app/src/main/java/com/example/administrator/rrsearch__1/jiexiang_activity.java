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

import static com.example.administrator.rrsearch__1.MainActivity.dbHelper;
import static com.example.administrator.rrsearch__1.R.id.spinner1;
import static com.example.administrator.rrsearch__1.fajue_activity.check_linshi;

public class jiexiang_activity extends AppCompatActivity implements View.OnClickListener{

    Button button_backff;
    Button juesuan_button,didian_button,shichang_button;
    EditText editText1,editText2,editText3,editText4,editText5;
    Spinner spinner;
    String kaogudi_;
    List<String> list1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.jiexiang_activity);
        button_backff=(Button)findViewById(R.id.button_backff);
        juesuan_button=(Button)findViewById(R.id.but_3);
        didian_button=(Button)findViewById(R.id.but_1);
        shichang_button=(Button)findViewById(R.id.but_2);
        spinner=(Spinner) findViewById(R.id.spinner_b1);
        editText1=(EditText)findViewById(R.id.editText_id23);
        editText2=(EditText)findViewById(R.id.editText_id24);
        editText3=(EditText)findViewById(R.id.editText_id25);
        editText4=(EditText)findViewById(R.id.editText_id26);
        editText5=(EditText)findViewById(R.id.editText_id27);
        button_backff.setOnClickListener(this);
        juesuan_button.setOnClickListener(this);
        didian_button.setOnClickListener(this);
        shichang_button.setOnClickListener(this);
        querykaogudi();
        MyAdapter adapter = new MyAdapter(this);
        spinner.setAdapter(adapter);
        adapter.setDatas(list1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        kaogudi_=spinner.getSelectedItem().toString();
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
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_backff)
        {
            Intent intent =new Intent(jiexiang_activity.this,third_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            jiexiang_activity.this.finish();
        }
        else if(v.getId()==R.id.but_1)
        {
            if(shenhe())
            {
                Toast.makeText(this, spinner.getSelectedItem().toString()+"開始結算！", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, spinner.getSelectedItem().toString()+"未審核發掘！", Toast.LENGTH_SHORT).show();
            }

        }
        else if(v.getId()==R.id.but_2)
        {
            if(editText1.getText().toString().isEmpty())
            {
                Toast.makeText(this, "请输入工作时长！", Toast.LENGTH_SHORT).show();
            }
            else
            {
                SQLiteDatabase sdb=dbHelper.getWritableDatabase();
                String counttime=editText1.getText().toString();
                double count_time=Double.parseDouble(counttime);
                String sql="update Gongzi_Book set gongzi=80*?" +
                        "where Gongzi_ID=(select Gongzi_ID from Linshi_Book) ";
                if(check_linshi)
                {
                    sdb.execSQL(sql,new Double[]{count_time});
                    String sql1="select sum(gongzi) sum_gongzi from Gongzi_Book natural join Linshi_Book";
                    Cursor cursor=sdb.rawQuery(sql1,null);
                    if(cursor.moveToFirst())
                    {
                        String count_account=cursor.getString(cursor.getColumnIndex("sum_gongzi"));
                        String sql3="update Kaogudi_Book set Finalaccounts=? where Kaogudi_name=?";
                        sdb.execSQL(sql3,new String[]{count_account,kaogudi_});
                    }
                    Toast.makeText(this,"臨時工工資已更新！", Toast.LENGTH_SHORT).show();
                    cursor.close();
                }
                else
                {
                    Toast.makeText(this,"沒有額外工作人員！", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(v.getId()==R.id.but_3&&shenhe())
        {
            String a=editText2.getText().toString();
            String b=editText3.getText().toString();
            String c=editText4.getText().toString();
            String d=editText5.getText().toString();
            String kaogudiid=null;
            String sql1="select Kaogudi_ID from Kaogudi_Book where Kaogudi_name=?";
            String sql_1="select Wuzi_ID from Wuzi_Book where Wuzi_ID=?";
            SQLiteDatabase sdb=dbHelper.getWritableDatabase();
            Cursor cursor =sdb.rawQuery(sql1,new String[]{kaogudi_});
            Cursor cursor_1=sdb.rawQuery(sql_1,new String[]{a});
            if(cursor_1.moveToFirst()==true)
            {
                Toast.makeText(this,"物資編號已存在！", Toast.LENGTH_SHORT).show();
            }
            else if(!a.isEmpty()&&!b.isEmpty()&&!c.isEmpty()&&!d.isEmpty())
            {
                if(cursor.moveToFirst()==true)
                {
                    kaogudiid=cursor.getString(cursor.getColumnIndex("Kaogudi_ID"));
                }
                String sql="insert into Wuzi_Book(Wuzi_ID,danjia,Wuzi_name,number,Kaogudi_ID)values (?,?,?,?,?)";
                sdb.execSQL(sql,new String[]{a,b,c,d,kaogudiid});
                double number=Double.parseDouble(d);
                double danjia=Double.parseDouble(b);
                String a1=String.valueOf(number*danjia);
                String sql2="update Wuzi_Book set zongjia= ? * ?";
                String sql3="update Kaogudi_Book set Finalaccounts=Finalaccounts+? where Kaogudi_name=?";
                String sql4="update Kaogudi_Book set kaogujindu='100' where Kaogudi_name=?";
                sdb.execSQL(sql2,new Double[]{number,danjia});
                sdb.execSQL(sql3,new String[]{a1,kaogudi_});
                sdb.execSQL(sql4,new String[]{kaogudi_});
                Toast.makeText(this,"壹鍵決算成功！", Toast.LENGTH_SHORT).show();
                cursor.close();
            }
            else
            {
                Toast.makeText(this,"請輸入足夠信息！", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId()==R.id.but_3&&!shenhe())
        {
            Toast.makeText(this, spinner.getSelectedItem().toString()+"未審核發掘！", Toast.LENGTH_SHORT).show();
        }
    }
}