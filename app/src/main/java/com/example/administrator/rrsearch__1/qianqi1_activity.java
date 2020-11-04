package com.example.administrator.rrsearch__1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import static android.R.id.list;
import static com.example.administrator.rresearch__1.MainActivity.dbHelper;
import static com.example.administrator.rresearch__1.R.id.textView;
import static com.example.administrator.rresearch__1.qianqi_activity.kaogunamme;

public class qianqi1_activity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
    String kaogudiid,kaogudiname,kaogudijieshao=null,budget=null,ifagreed="no",juesuan=null,jindu="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.qianqi1_activity);
        button1=(Button)findViewById(R.id.button_back2);
        textView1=(TextView)findViewById(R.id.Text_3);
        textView2=(TextView)findViewById(R.id.Text_4);
        textView3=(TextView)findViewById(R.id.Text_5);
        textView4=(TextView)findViewById(R.id.Text_6);
        textView5=(TextView)findViewById(R.id.Text_7);
        textView6=(TextView)findViewById(R.id.Text_8);
        textView7=(TextView)findViewById(R.id.Text_9);
        button1.setOnClickListener(this);
        String sql="select * from Kaogudi_Book where Kaogudi_name=?";
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        Cursor cursor=sdb.rawQuery(sql,new String[]{kaogunamme});
        if(cursor.moveToFirst()==true)
        {
            kaogudiid=cursor.getString(cursor.getColumnIndex("Kaogudi_ID"));
            kaogudiname =kaogunamme;
            kaogudijieshao=cursor.getString(cursor.getColumnIndex("Kaogudi_jieshao"));
            budget=cursor.getString(cursor.getColumnIndex("Budget"));
            ifagreed=cursor.getString(cursor.getColumnIndex("ifagreed"));
            juesuan =cursor.getString(cursor.getColumnIndex("Finalaccounts"));
            jindu =cursor.getString(cursor.getColumnIndex("kaogujindu"));
        }
        textView1.setText(kaogudiid);
        textView2.setText(kaogudiname);
        if(ifagreed.equals("no"))
        {
            textView3.setText("否");
        }
        else textView3.setText("是");
        textView4.setText(jindu);
        if(budget==null||budget.isEmpty())
        {
            textView5.setText("未預算");
        }
        else textView5.setText(budget);
        if(juesuan==null||juesuan.isEmpty())
        {
            textView6.setText("未決算");
        }
        else textView6.setText(juesuan);
        if(kaogudijieshao==null||kaogudijieshao.isEmpty())
        {
            textView7.setText("无");
        }
        else textView7.setText(kaogudijieshao);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_back2)
        {
            Intent intent =new Intent(qianqi1_activity.this,qianqi_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            qianqi1_activity.this.finish();
        }
    }
}