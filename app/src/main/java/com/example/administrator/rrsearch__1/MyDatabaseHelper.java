package com.example.administrator.rrsearch__1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.R.attr.tag;



public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String Kaogudi_Book="create table Kaogudi_Book("+
            "Kaogudi_ID integer(10) not null primary key," +
            "Kaogudi_name varchar(40)," +
            "Kaogudi_jieshao text,"+
            "ifagreed varchar(10) default ('no'),"+
            "kaogujindu integer(3) default ('0'),"+
            "Budget numeric(12,2),Finalaccounts  numeric(12,2))";
    public static final String Wenwu_Book="create table Wenwu_Book("+
            "Wenwu_Id integer(10) not null primary key,"+
            "Wenwu_name varchar(20),"+
            "Kaogudi_ID integer(10),"+
            "Worker_ID integer(10),"+
            "Bumen_ID integer(10),"+
            "tag varchar(20) default ('no')," +
            "Situation varchar(10) default ('chutu'),"+
            "foreign key(Kaogudi_ID) references Kaogudi_Book(Kaogudi_ID)," +
            "foreign key(Worker_ID) references Worker_Book(Worker_ID)," +
            "foreign key(Bumen_ID) references Bumen_Book(Bumen_ID))";
    public static final String Bumen_Book="create table Bumen_Book("+
            "Bumen_ID integer(10) not null primary key,"+
            "Bumen_name varchar(20),"+
            "Bumen_zhineng varchar(10))";
    public static final String Worker_Book="create table Worker_Book("+
            "Worker_ID integer(10) not null primary key,"+
            "Worker_name varchar(20),"+
            "Worker_gongling integer(3),"+
            "Worker_phone integer(11),"+
            "Bumen_ID integer(10),"+
            "Worker_zhiwu varchar(20)," +
            "Gongzi_ID integer(10)," +
            "foreign key(Bumen_ID) references Bumen_Book(Bumen_ID)," +
            "foreign key(Gongzi_ID) references Gongzi_Book(Gongzi_ID))";
    public static final String Linshi_Book="create table Linshi_Book("+
            "Linshi_ID integer(10) not null primary key,"+
            "Linshi_name varchar(20),"+
            "Kaogudi_ID integer(10)," +
            "Gongzi_ID integer(10)," +
            "foreign key(Gongzi_ID) references Gongzi_Book(Gongzi_ID)," +
            "foreign key(Kaogudi_ID) references Kaogudi_Book(Kaogudi_ID))";
    public static final String Gongzi_Book="create table Gongzi_Book("+
            "Gongzi_ID integer(10) not null primary key,"+
            "per numeric(7,2),"+
            "times numeric(7,2)," +
            "gongzi numeric(10,2))";
    public static final String Wuzi_Book="create table Wuzi_Book("+
            "Wuzi_ID integer(10) not null primary key,"+
            "Wuzi_name  varchar(20),"+
            "danjia numeric(7,2)," +
            "number numeric(7,2)," +
            "zongjia numeric(7,2)," +
            "Worker_ID integer(10)," +
            "Kaogudi_ID integer(10)," +
            "foreign key(Kaogudi_ID) references Kaogudi_Book(Kaogudi_ID)" +
            "foreign key(Worker_ID) references Worker_Book(Worker_ID))";
    public static final String denglu_Book="create table denglu_Book(" +
            "Worker_ID integer(10) not null primary key," +
            "mima varchar(20) default('123456')," +
            "foreign key(Worker_ID) references Worker_Book(Worker_ID))";
    public static final String bumen_1="insert into Bumen_Book values" +
            "('10000','勘察部门','勘察')," +
            "('10002','修复部门','修复')," +
            "('10001','存储部门','存储')," +
            "('10003','展览部门','展览')" ;
    public static final String wenwu_1="insert into Wenwu_Book(Wenwu_ID,Wenwu_name,Bumen_ID," +
            "Kaogudi_ID,Worker_ID,tag,Situation) values" +
            "('20000','素纱单衣','10002','60000','30000','yes','xiufu')," +
            "('20001','帛书'    ,'10001','60000','30001','no','cunchu')," +
            "('20002','汉简'    ,'10003','60001','30002','yes','zhanlan')," +
            "('20003','马蹄金'  ,'10000','60001','30003','no','chutu')";
    public static final String worker_1="insert into Worker_Book values" +
            "('30000','李修复','2','13531269487','10002','文物修复','40000')," +
            "('30001','李保管','3','13531269488','10001','文物保管','40001')," +
            "('30002','李展览','4','13531269489','10003','文物展览','40002')," +
            "('30003','李勘察','5','13531269480','10000','文物勘察','40003')";
    public static final String gongzi_1="insert into Gongzi_Book(Gongzi_ID,per) values" +
            "('40000','100')," +
            "('40001','100')," +
            "('40002','100')," +
            "('40003','100')," +
            "('40004','80')," +
            "('40005','80')";
    public static final String linshi_1="insert into Linshi_Book(Linshi_ID,Linshi_name,Gongzi_ID) values" +
            "('50000','刘七','40004')," +
            "('50001','徐八','40005')";
    public static final String kaogudi_1="insert into Kaogudi_Book(Kaogudi_ID,Kaogudi_name,Kaogudi_jieshao) values" +
            "('60000','长沙马王堆汉墓',?)," +
            "('60001','西汉海昏侯墓',?)";
    public static final String denglu_1="insert into denglu_Book(Worker_ID) values" +
            "('30000')," +
            "('30001')," +
            "('30002')," +
            "('30003')";
    public String jieshao_1="马王堆汉墓是西汉初期长沙国丞相利苍及其家属的墓葬，位于湖南省长沙市。";
    public String jieshao_2="海昏侯墓是汉废帝刘贺的墓葬，位于江西省南昌市新建区大塘坪乡观西村，" +
            "是中国发现的面积最大、保存最好、内涵最丰富的汉代列侯等级墓葬，2015年入选中国十大考古新发现。";
    private Context mContext;

    //构造方法：第一个参数Context，第二个参数数据库名，第三个参数cursor允许我们在查询数据的时候返回一个自定义的光标位置，
    //一般传入的都是null，第四个参数表示目前库的版本号（用于对库进行升级）
    public  MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name ,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //调用SQLiteDatabase中的execSQL（）执行建表语句。
        db.execSQL(Bumen_Book);
        db.execSQL(Worker_Book);
        db.execSQL(Wenwu_Book);
        db.execSQL(Gongzi_Book);
        db.execSQL(Wuzi_Book);
        db.execSQL(Linshi_Book);
        db.execSQL(Kaogudi_Book);
        db.execSQL(denglu_Book);
        db.execSQL(bumen_1);
        db.execSQL(wenwu_1);
        db.execSQL(kaogudi_1,new String[]{jieshao_1,jieshao_2});
        db.execSQL(worker_1);
        db.execSQL(linshi_1);
        db.execSQL(gongzi_1);
        db.execSQL(denglu_1);
        //创建成功
        //Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

