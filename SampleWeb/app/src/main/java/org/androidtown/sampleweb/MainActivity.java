package org.androidtown.sampleweb;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    Handler handler=new Handler();
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView=(WebView)findViewById(R.id.webView);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebBrowserClient());
        webView.addJavascriptInterface(new JavaScriptMethods(),"sample");
        webView.loadUrl("file:///android_asset/www/sample.html");

        et=(EditText)findViewById(R.id.editText);
    }

    public void onClick(View v){
        webView.loadUrl(et.getText().toString());
    }

    final class JavaScriptMethods {
        JavaScriptMethods(){}

        @android.webkit.JavascriptInterface
        public void clickOnFace(){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:changeFace()");;
                }
            });
        }
    }

    final class WebBrowserClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();

            return true;
        }
    }


}
