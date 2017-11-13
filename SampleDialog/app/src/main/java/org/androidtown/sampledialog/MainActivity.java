package org.androidtown.sampledialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.textView);
    }

    public void onClick(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("안내")
                .setMessage("종료하시겠습니까?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv.setText("예 선택");
                        finish();
                    }
                })
                .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv.setText("취소 선택");
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv.setText("아니오 선택");
                    }
                });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
