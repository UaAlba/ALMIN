package com.example.ualba.almin;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class JoinMemberWebview extends AppCompatActivity {
    private WebView webView;
    private TextView result;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_member_webview);

        result = (TextView)findViewById( R.id.result);
        init_WebView();
        handler = new Handler();
    }

    public void init_WebView(){
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.addJavascriptInterface(new AndroidBridge(), "TestApp");
        // web client 를 chrome 으로 설정
        webView.setWebChromeClient(new WebChromeClient());
        // webview url load
//        webView.loadUrl("file:///android_asset/search_address.html");
        webView.loadUrl("http://codeman77.ivyro.net/getAddress.php");

    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(JoinMemberWebview.this, arg1+arg2+arg3, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(WebViewActivity.this, arg1, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(WebViewActivity.this, arg1, Toast.LENGTH_SHORT).show();

                    result.setText(String.format("(%s) %s %s", arg1, arg2, arg3));
                    // WebView를 초기화 하지않으면 재사용할 수 없음
                    init_WebView();
                    //onPause();

                    Intent intent=new Intent(JoinMemberWebview.this, JoinMemberFirst.class);

                    String addr = arg1+arg2+arg3;
//                    Toast.makeText(WebViewActivity.this, addr, Toast.LENGTH_SHORT).show();
                    intent.putExtra("addr", addr);
                    startActivity(intent);
                    finish();
                }
            });

        }
    }
}
