package com.kkdev.mdbm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.kkdev.mdbm.R;
import com.kkdev.mdbm.service.MovieDataService;
import com.kkdev.mdbm.service.RetrofitInstance;
import com.kkdev.mdbm.service.StreamInstance;
import com.kkdev.mdbm.service.TicketInstance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebViewActivity extends AppCompatActivity {
    private String ticket;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_view);
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        //Toast.makeText(this, ip, Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        if (intent.hasExtra("id")){
            final String id = Integer.toString(intent.getIntExtra("id",0));
          //  Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
            MovieDataService movieDataService = TicketInstance.getService();
            Call<String> call = movieDataService.getTicket("O1xhN8oqRad0xwt6","buuiguij2smcys6mvmw3vq0q4yt67a",id,ip);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    ticket= response.body();
                    showWebView("O1xhN8oqRad0xwt6",id,ticket,1);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                }
            });
        }


    }

    private void showWebView(String key, String video_id, String ticket, int tmdb) {
        MovieDataService movieDataService = StreamInstance.getService();
        Call<Void> call = movieDataService.getVideo(key,video_id,ticket,tmdb);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                String res = response.body().toString();
                Toast.makeText(WebViewActivity.this, res, Toast.LENGTH_SHORT).show();
                webView.loadData(res, "text/html", "UTF-8");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(WebViewActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
