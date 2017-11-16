package org.androidtown.sampledatabasequery;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String dbName,dbTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText)findViewById(R.id.editText);
        tv=(TextView)findViewById(R.id.textView);
    }

    public void onClick(View v){
        dbName=et.getText().toString();
        dbHelper=new DatabaseHelper(this);
        db=dbHelper.getWritableDatabase();

        executeQuery();
    }

    private void executeQuery() {
        tv.append("데이터 쿼리\n");
        String sql="select name,age,phone from employee where age > ?";
        String[] args={"10"};

        Cursor c1=db.rawQuery(sql,args);
        int recordCount=c1.getCount();
        tv.append("레코드 갯수:"+recordCount);

        for(int i=0;i<recordCount;i++){
            c1.moveToNext();
            String name=c1.getString(0);
            int age=c1.getInt(1);
            String phone=c1.getString(2);

            tv.append("데이터 "+i+":"+name+", "+age+", "+phone+"\n");
        }
        c1.close();
    }

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context) {
            super(context, dbName, null, 2);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            tv.append("Table 생성\n");
            try{
                db.execSQL("drop table if exists employee");
            }catch (Exception e){
                Log.e("aaa","테이블 삭제 안함");
            }

            try{
                db.execSQL("create table employee ("+
                        "_id integer primary key autoincrement," +
                        "name text," +
                        "age integer," +
                        "phone text);");
            }catch (Exception e){
                Log.e("aaa","테이블 생성 안함");
            }

            try {
                db.execSQL("insert into employee(name,age,phone) values ('홍길동',20,'010-1111-1111');");
                db.execSQL("insert into employee(name,age,phone) values ('김길동',30,'010-2222-2222');");
                db.execSQL("insert into employee(name,age,phone) values ('최길동',40,'010-3333-3333');");
                tv.append("3개 데이터 추가\n");
            }catch (Exception e){
                Log.e("aaa","데이터 추가 안함");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        }
    }
}
