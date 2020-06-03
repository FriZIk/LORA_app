package com.example.lora;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    public TextView IP_Address;
    public TextView Data;

    public Button SendButton;
    public Button GetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener GetDataHandler = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, GetData.class);
                //Intent intent1 = new Intent(MainActivity.this, Test.class);
                startActivity(intent);
            }
        };

        View.OnClickListener SendDataHandler = new View.OnClickListener()
        {
          public void onClick(View v)
          {
              Intent intent = new Intent(MainActivity.this, SendData.class);
              startActivity(intent);
          }
        };

        Button SendData = (Button)findViewById(R.id.SendDataButton);
        SendData.setOnClickListener(SendDataHandler);

        Button GetData = (Button)findViewById(R.id.GetDataButton);
        GetData.setOnClickListener(GetDataHandler);
    }
}
