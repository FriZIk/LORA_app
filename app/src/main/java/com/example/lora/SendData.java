package com.example.lora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SendData extends AppCompatActivity
{

    public TextView IP_Address;
    public TextView Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);

        IP_Address = findViewById(R.id.IP);
        Data = findViewById(R.id.Data);

        View.OnClickListener myOnlyhandler = new View.OnClickListener() {
            public void onClick(View v) {
                TextView Info = findViewById(R.id.InfoMesageOn);
                Info.setVisibility(View.VISIBLE);
                String baseurl = IP_Address.getText().toString();
                String data = Data.getText().toString();
                String port = "80";
                new SendData.pingit().execute(baseurl + ":" + port, data);
            }
        };

        Button power = (Button) findViewById(R.id.SendDataButton);
        power.setOnClickListener(myOnlyhandler);
    }

    class pingit extends AsyncTask<String, String, Void>
    {
        @Override
        protected Void doInBackground(String[] strings)
        {
            Socket soc = null;
            String url = strings[0];
            String DataString = strings[1];
            //Log.i("Данные:", Data);
            String IP = url.split(":")[0];
            int Port = Integer.parseInt(url.split(":")[1]);
            try {
                soc = new Socket(IP,Port);
                InputStream Input = soc.getInputStream();
                OutputStream Output = soc.getOutputStream();

                //byte[] BytesArray = new byte[]{1,2,3};
                byte[] BytesArray = DataString.getBytes();

                Output.write(BytesArray);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
