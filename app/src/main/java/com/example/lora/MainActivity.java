package com.example.lora;
import android.webkit.WebResourceRequest;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.security.ProtectionDomain;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{
    public TextView SendData;
    public TextView Data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SendData = findViewById(R.id.DataSend);
        Data = findViewById(R.id.Data);
    }

    public void Click(View view)
    {
        SendData.setVisibility(View.VISIBLE);
        String EnteredData ="";
        EnteredData = Data.getText().toString();
        OkHttpClient client = new OkHttpClient();
        String url = "192.168.0.*"; // Нужно узнать нужный урл в сети
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace(); // Вывод сообщения об ошибке
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                if (response.isSuccessful())
                {
                    String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            //mTextViewResult.setText(myResponse);
                        }
                    });
                }
            }
        });

    }
}