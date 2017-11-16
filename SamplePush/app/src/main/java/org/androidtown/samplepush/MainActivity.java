package org.androidtown.samplepush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText messageInput;
    TextView messageOutput;
    TextView log;

    String regId;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageInput=(EditText)findViewById(R.id.messageInput);
        messageOutput=(TextView)findViewById(R.id.messageOutput);
        log=(TextView)findViewById(R.id.log);

        queue= Volley.newRequestQueue(getApplicationContext());
        getRegistrationId();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        log.append("onNewIntent() 호출됨");

        if(intent!=null){
            processIntent(intent);
        }
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        String from=intent.getStringExtra("from");
        if(from==null){
            log.append("from is null");
            return;
        }
        String contents=intent.getStringExtra("contents");
        log.append("DATA:"+from+", "+contents);
        messageOutput.setText("["+from+"]로부터 수신한 데이터:"+contents);
    }

    private void getRegistrationId() {
        log.append("getRegistrationId() 호출됨\n");

        regId= FirebaseInstanceId.getInstance().getToken();
        log.append("regId:"+regId);
    }

    public void onClick(View v){
        String input=messageInput.getText().toString();
        send(input);
    }

    private void send(String input) {
        JSONObject requestData=new JSONObject();

        try {
            requestData.put("priority","high");

            JSONObject dataObj=new JSONObject();
            dataObj.put("contents",input);
            requestData.put("data",dataObj);

            JSONArray idArray=new JSONArray();
            idArray.put(0,regId);
            requestData.put("registration_ids",idArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sendData(requestData,new SendResponseListener(){

            @Override
            public void onRequestStarted() {
                log.append("onRequestStarted() 호출됨.\n");
            }

            @Override
            public void onRequestCompleted() {
                log.append("onRequestCompleted() 호출됨.\n");
            }

            @Override
            public void onRequestWithError(VolleyError error) {
                log.append("onRequestWithError() 호출됨.\n");
            }
        });
    }

    private void sendData(JSONObject requestData, final SendResponseListener listener) {
        JsonObjectRequest request=new JsonObjectRequest(
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestCompleted();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onRequestWithError(error);
                    }
                }){
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers=new HashMap<>();
                headers.put("Authorization","key=AAAADRnyPbY:APA91bHuPnFtuZaID7qN_xE2BGiNbsMiBlT1pwoqqz6xRlKsEQFRPAW2LhYx3P_MoN2mIkrp7YcpnAJAjMNDJk4geEP7eahqErT3rN1xiUb4mji2Ls3trYWQZMftU2Ex56_8Jywbpo9X");

                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                return params;
            }
        };

        request.setShouldCache(false);
        listener.onRequestStarted();
        queue.add(request);
    }

    public interface SendResponseListener{
        public void onRequestStarted();
        public void onRequestCompleted();
        public void onRequestWithError(VolleyError error);
    }
}
