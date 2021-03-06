package org.androidtown.samplereceiver3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("aaa","onReceive() 호출됨.");

        Bundle bundle=intent.getExtras();
        SmsMessage[] messages=parseSmsMessage(bundle);

        if(messages!=null && messages.length>0){
            String sender=messages[0].getOriginatingAddress();
            Log.e("aaa","SMS sender:"+sender);

            String contents=messages[0].getMessageBody().toString();
            Log.e("aaa","SMS contents:"+contents);

            Date receivedDate=new Date(messages[0].getTimestampMillis());
            Log.e("aaa","SMS received date:"+receivedDate.toString());
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs=(Object[])bundle.get("puds");
        SmsMessage[] messages=new SmsMessage[objs.length];

        int smsCount=objs.length;
        for(int i=0;i<smsCount;i++){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                String format=bundle.getString("format");
                messages[i]=SmsMessage.createFromPdu((byte[])objs[i],format);
            }else{
                messages[i]=SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }
        return messages;
    }
}
