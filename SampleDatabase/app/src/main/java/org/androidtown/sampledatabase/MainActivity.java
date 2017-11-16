package org.androidtown.sampledatabase;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et,et2;
    TextView tv;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        tv=(TextView)findViewById(R.id.textView);
    }

    public void onClick(View v){
        String dbname=et.getText().toString();
        tv.setText("DB 생성\n");
        db=openOrCreateDatabase(dbname,MODE_PRIVATE,null);
    }

    public void onClick2(View v){
        String tableName=et2.getText().toString();
        tv.append("Table 생성\n");
        db.execSQL("create table "+tableName+"("+
                "_id integer primary key autoincrement," +
                "name text," +
                "age integer," +
                "phone text);");

        db.execSQL("insert into employee(name,age,phone) values ('홍길동',20,'010-1111-1111');");
        db.execSQL("insert into employee(name,age,phone) values ('김길동',30,'010-2222-2222');");
        db.execSQL("insert into employee(name,age,phone) values ('최길동',40,'010-3333-3333');");
        tv.append("3개 데이터 추가\n");
    }
}
