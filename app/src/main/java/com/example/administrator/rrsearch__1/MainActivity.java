package com.example.administrator.rrsearch__1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_denglu;
    private Button button_xiugai;
    private EditText edit_zhanghao;
    private EditText edit_mima;
    public static MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new MyDatabaseHelper(this, "Bookstore.db", null, 1);//新建一个数据库
        SQLiteStudioService.instance().start(this);//SQLiteStudio可视化工具
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_main);
        button_denglu=(Button)findViewById(R.id.button_denglu);
        button_xiugai=(Button)findViewById(R.id.button_xiugai);
        edit_zhanghao=(EditText)findViewById(R.id.Text_1);
        edit_mima=(EditText)findViewById(R.id.Text_2);
        button_denglu.setOnClickListener(this);
        button_xiugai.setOnClickListener(this);
        dbHelper.getWritableDatabase();//打开可修改的数据库
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_denglu)
        {
            String Username=edit_zhanghao.getText().toString();
            String Mima=edit_mima.getText().toString();
            if(login(Username,Mima)){
                Intent intent=new Intent(MainActivity.this,third_activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
                MainActivity.this.finish();
            }
            else {
                if(Username.isEmpty()||Mima.isEmpty())
                {
                    Toast.makeText(this,"請輸入工作號和密碼！", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this,"工作號或密碼錯誤！",Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(v.getId()==R.id.button_xiugai)
        {
            Intent intent=new Intent(MainActivity.this,second_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            MainActivity.this.finish();
        }
    }
    public boolean login(String username,String passward)
    {
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from denglu_Book where Worker_ID=? and mima=?";
        Cursor cursor=sdb.rawQuery(sql,new String[]{username,passward});
        if(cursor.moveToFirst()==true)
        {
            cursor.close();
            return true;
        }
        else
            return false;
    }
}
