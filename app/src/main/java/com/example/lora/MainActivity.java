package com.example.lora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.security.ProtectionDomain;

public class MainActivity extends AppCompatActivity
{
    public TextView SendData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SendData = findViewById(R.id.DataSend);
    }

    public void Click(View view)
    {
        SendData.setVisibility(View.VISIBLE);
    }
}
