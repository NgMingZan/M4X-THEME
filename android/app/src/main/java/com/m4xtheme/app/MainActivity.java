package com.m4xtheme.app;

import android.app.*;
import android.os.*;
import android.webkit.*;
import android.content.*;
import android.net.Uri;
import android.view.*;
import android.widget.Toast;
import android.app.DownloadManager;

public class MainActivity extends Activity {
  private WebView web;
  @Override public void onCreate(Bundle b){
    super.onCreate(b);
    web=new WebView(this);
    setContentView(web);
    WebSettings s=web.getSettings();
    s.setJavaScriptEnabled(true);
    s.setDomStorageEnabled(true);
    s.setAllowFileAccess(true);
    s.setAllowContentAccess(true);
    web.addJavascriptInterface(new AndroidBridge(), "Android");
    web.setWebChromeClient(new WebChromeClient());
    web.setWebViewClient(new WebViewClient(){
      @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r){
        Uri u=r.getUrl();
        if("file".equals(u.getScheme()) || "about".equals(u.getScheme())) return false;
        return false;
      }
    });
    web.loadUrl("file:///android_asset/index.html");
  }

  public class AndroidBridge {
    @JavascriptInterface public void openExternal(String url){
      runOnUiThread(() -> {
        try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); }
        catch(Exception e){ Toast.makeText(MainActivity.this,"Không mở được liên kết",Toast.LENGTH_SHORT).show(); }
      });
    }

    @JavascriptInterface public void download(String url, String filename){
      runOnUiThread(() -> {
        try{
          DownloadManager.Request req=new DownloadManager.Request(Uri.parse(url));
          req.setTitle(filename);
          req.setDescription("Đang tải theme từ M4X THEME");
          req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
          req.setDestinationInExternalPublicDir(android.os.Environment.DIRECTORY_DOWNLOADS, filename);
          req.setAllowedOverMetered(true);
          req.setAllowedOverRoaming(true);
          ((DownloadManager)getSystemService(DOWNLOAD_SERVICE)).enqueue(req);
          Toast.makeText(MainActivity.this,"Đã bắt đầu tải xuống",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
          Toast.makeText(MainActivity.this,"Không thể tải file",Toast.LENGTH_SHORT).show();
        }
      });
    }
  }

  @Override public void onBackPressed(){
    if(findViewById(android.R.id.content)!=null){
      web.evaluateJavascript("document.getElementById('detail').classList.contains('open')", value -> {
        if("true".equals(value)){ web.evaluateJavascript("closeDetail()",null); }
        else if(web.canGoBack()) web.goBack(); else super.onBackPressed();
      });
    } else super.onBackPressed();
  }
}