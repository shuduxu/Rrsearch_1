package com.example.administrator.rrsearch__1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static com.example.administrator.rresearch__1.MainActivity.dbHelper;


public class second_activity extends AppCompatActivity implements View.OnClickListener {
    private Button button_fanhui;
    private Button button_xiugaimima;
    EditText editText_1,editText_2,editText_3,editText_4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.second_activity);
        button_fanhui=(Button)findViewById(R.id.button_back);
        button_xiugaimima=(Button)findViewById(R.id.button_xiugaimima);
        editText_1=(EditText)findViewById(R.id.worker_ID);
        editText_2=(EditText)findViewById(R.id.old_passwd);
        editText_3=(EditText)findViewById(R.id.new_passwd);
        editText_4=(EditText)findViewById(R.id.new1_passwd);
        button_xiugaimima.setOnClickListener(this);
        button_fanhui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_back)
        {
            Intent intent=new Intent(second_activity.this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            second_activity.this.finish();
        }
        if (v.getId()==R.id.button_xiugaimima)
        {
            String worker_id=editText_1.getText().toString();
            String old_passwd=editText_2.getText().toString();
            String new_passwd=editText_3.getText().toString();
            String new1_passwd=editText_4.getText().toString();
            int a = Editpasswd(worker_id,old_passwd,new_passwd,new1_passwd);
            if(a>0)
            {
                Toast.makeText(this,"修改成功！", Toast.LENGTH_SHORT).show();
            }
            else if(a==0)
            {
                if(new_passwd.isEmpty()||new1_passwd.isEmpty())
                {
                    Toast.makeText(this, "請輸入修改後的密碼並確認！", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "請重新修改密碼！", Toast.LENGTH_SHORT).show();
                }
            }
            else if (a==-1)
            {
                if(worker_id.isEmpty()||old_passwd.isEmpty())
                {
                    Toast.makeText(this,"請輸入工作號和原密碼！", Toast.LENGTH_SHORT).show();
                }
                else

                    Toast.makeText(this,"工作號或原密碼輸入錯誤！", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public int Editpasswd(String Worker_id,String old_passward,String New_passwd,String New1_passwd)
    {
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from denglu_Book where Worker_ID=? and mima=?";
        String sql1="update denglu_Book set mima=? where Worker_ID= ?";
        Cursor cursor=sdb.rawQuery(sql,new String[]{Worker_id,old_passward});
        if(cursor.moveToFirst()==true)
        {
            if(New1_passwd.equals(New_passwd)&&!New1_passwd.isEmpty()&&!New_passwd.isEmpty())
            {
                sdb.execSQL(sql1, new String[]{New_passwd,Worker_id});
                cursor.close();
                return 1;
            }
            else
            {
                //密码确认不一致
                cursor.close();
                return 0;
            }

        }
        else
            return -1;//旧密码错误
    }
}