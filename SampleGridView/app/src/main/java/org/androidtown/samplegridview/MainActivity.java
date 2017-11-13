package org.androidtown.samplegridview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    GridView gridView;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.editText);

        gridView=(GridView)findViewById(R.id.gridView);
        adapter=new SingerAdapter();
        adapter.addItem(new SingerItem("소녀시대","010-1111-1111",20,R.drawable.singer));
        adapter.addItem(new SingerItem("걸스데이","010-2222-2222",21,R.drawable.singer2));
        adapter.addItem(new SingerItem("여자친구","010-3333-3333",22,R.drawable.singer3));
        adapter.addItem(new SingerItem("티아라","010-4444-4444",23,R.drawable.singer4));
        adapter.addItem(new SingerItem("AOA","010-5555-5555",24,R.drawable.singer5));
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingerItem item=(SingerItem)adapter.getItem(i);

                Intent intent=new Intent(getApplicationContext(),SingerActivity.class);
                intent.putExtra("name",item.getName());
                intent.putExtra("mobile",item.getMobile());
                intent.putExtra("age",item.getAge()+"");
                intent.putExtra("resId",item.getResId()+"");
                startActivity(intent);

                Toast.makeText(getApplicationContext(),"선택:"+item.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick(View v){
        String str=editText.getText().toString();
        SingerItem sItem=new SingerItem(
                str,"010-0000-0000",20,R.drawable.singer);
        adapter.addItem(sItem);
        gridView.setAdapter(adapter);
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
            view.setMobile(item.getMobile());
            view.setAge(item.getAge());
            view.setImage(item.getResId());

            return view;
        }
    }
}


