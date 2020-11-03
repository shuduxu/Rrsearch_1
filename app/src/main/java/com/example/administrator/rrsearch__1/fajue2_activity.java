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
import android.widget.Toast;

import static com.example.administrator.rrsearch__1.MainActivity.dbHelper;
import static com.example.administrator.rrsearch__1.R.id.textView;
import static com.example.administrator.rrsearch__1.fajue_activity.wenwu;

public class fajue2_activity extends AppCompatActivity implements View.OnClickListener{

    Button buttonback;
    TextView textView1,textView2,textView3,textView4,textView5,textView6;
    String wenwuid,wenwuname,wenwufuze,xiufu,bumen,kaogudi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.fajue2_activity);
        buttonback=(Button)findViewById(R.id.button_backk);
        textView1=(TextView)findViewById(R.id.Text11);
        textView2=(TextView)findViewById(R.id.Text12);
        textView3=(TextView)findViewById(R.id.Text13);
        textView4=(TextView)findViewById(R.id.Text14);
        textView5=(TextView)findViewById(R.id.Text15);
        textView6=(TextView)findViewById(R.id.Text16);
        buttonback.setOnClickListener(this);
        SQLiteDatabase sdb= dbHelper.getReadableDatabase();
        String sql="select * from Wenwu_Book where Wenwu_name=?";
        String sql1="select Kaogudi_name from Kaogudi_Book natural join Wenwu_Book where Wenwu_name=?";
        String sql2="select Worker_name from Worker_Book natural join Wenwu_Book where Wenwu_name=?";
        String sql3="select Bumen_name from Bumen_Book natural join Wenwu_Book where Wenwu_name=?";
        Cursor cursor=sdb.rawQuery(sql,new String []{wenwu});
        Cursor cursor1=sdb.rawQuery(sql1,new String []{wenwu});
        Cursor cursor2=sdb.rawQuery(sql2,new String []{wenwu});
        Cursor cursor3=sdb.rawQuery(sql3,new String []{wenwu});
        if(cursor.moveToFirst()==true)
        {
            wenwuid=cursor.getString(cursor.getColumnIndex("Wenwu_Id"));
            wenwuname=cursor.getString(cursor.getColumnIndex("Wenwu_name"));
            xiufu=cursor.getString(cursor.getColumnIndex("tag"));
        }
        if(cursor2.moveToFirst()==true)
        {
            wenwufuze=cursor2.getString(cursor2.getColumnIndex("Worker_name"));
        }
        if(cursor3.moveToFirst()==true)
        {
            bumen=cursor3.getString(cursor3.getColumnIndex("Bumen_name"));
        }
        if(cursor1.moveToFirst()==true)
        {
            kaogudi=cursor1.getString(cursor1.getColumnIndex("Kaogudi_name"));
        }
        textView1.setText(wenwuid);
        textView2.setText(wenwuname);
        if(xiufu.equals("no"))
        {
            textView4.setText("未修復");
        }
        else
        {
            textView4.setText("已修復");
        }
        textView3.setText(kaogudi);
        textView5.setText(bumen);
        if(wenwufuze==null)
        {
            textView6.setText("無");
        }
        else
        {
            textView6.setText(wenwufuze);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_backk)
        {
            Intent intent =new Intent(fajue2_activity.this,fajue_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            fajue2_activity.this.finish();
        }
    }
}