package com.example.administrator.rrsearch__1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class third_activity extends AppCompatActivity implements View.OnClickListener{

    private Button button_qianqi,button_fajue,button_houqi,button_cunchu,button_xiufu,button_zhanlan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.third_activity);
        button_qianqi=(Button)findViewById(R.id.button_qianqi);
        button_fajue=(Button)findViewById(R.id.button_fajue);
        button_houqi=(Button)findViewById(R.id.button_jieshu);
        button_cunchu=(Button)findViewById(R.id.button_baocun);
        button_xiufu=(Button)findViewById(R.id.button_xiufu);
        button_zhanlan=(Button)findViewById(R.id.button_zhanlan);
        button_qianqi.setOnClickListener(this);
        button_fajue.setOnClickListener(this);
        button_houqi.setOnClickListener(this);
        button_cunchu.setOnClickListener(this);
        button_xiufu.setOnClickListener(this);
        button_zhanlan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_qianqi)
        {
            Intent intent=new Intent(third_activity.this,qianqi_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            third_activity.this.finish();
        }
        else if(v.getId()==R.id.button_fajue)
        {
            Intent intent=new Intent(third_activity.this,fajue_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            third_activity.this.finish();
        }
        else if(v.getId()==R.id.button_jieshu)
        {
            Intent intent=new Intent(third_activity.this,jiexiang_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            third_activity.this.finish();
        }
        else if(v.getId()==R.id.button_baocun)
        {
            Intent intent=new Intent(third_activity.this,baocun_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            third_activity.this.finish();
        }
        else if(v.getId()==R.id.button_xiufu)
        {
            Intent intent=new Intent(third_activity.this,xiufu_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            third_activity.this.finish();
        }
        else if(v.getId()==R.id.button_zhanlan)
        {
            Intent intent=new Intent(third_activity.this,zhanlan_activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.ap2, R.anim.ap1);// 淡出淡入动画效果
            third_activity.this.finish();
        }

    }
}