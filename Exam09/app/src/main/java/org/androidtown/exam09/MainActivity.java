package org.androidtown.exam09;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    SingerAdapter adapter;
    TextView txtPerson;
    EditText editName,editBirth,editTel;
    int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPerson=(TextView)findViewById(R.id.txtPerson);
        editName=(EditText)findViewById(R.id.editName);
        editBirth=(EditText)findViewById(R.id.editBirth);
        editTel=(EditText)findViewById(R.id.editTel);

        listView=(ListView)findViewById(R.id.listView);
        adapter=new SingerAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingerItem item=(SingerItem)adapter.getItem(i);

                Toast.makeText(getApplicationContext(),"선택:"+item.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick(View v){
        String name=editName.getText().toString();
        String birth=editBirth.getText().toString();
        String tel=editTel.getText().toString();

        adapter.addItem(new SingerItem(name,birth,tel,R.drawable.singer));
        listView.setAdapter(adapter);

        cnt++;
        txtPerson.setText(cnt+" 명");
        editName.setText("");
        editBirth.setText("");
        editTel.setText("");
    }

    public class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items=new ArrayList<>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            SingerItemView view=new SingerItemView(getApplicationContext());
            SingerItem item=items.get(i);
            view.setName(item.getName());
            view.setBrith(item.getBirth());
            view.setTel(item.getTel());
            view.setImage(item.getResId());

            return view;
        }
    }
}


