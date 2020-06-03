package com.example.lora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GetData extends AppCompatActivity
{
    public TextView Data;

    private ServerSocket serverSocket;
    Handler updateConversationHandler;
    Thread serverThread = null;
    private TextView text;
    public static final int SERVERPORT = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);

        Switch sw = (Switch) findViewById(R.id.SwitchListen);
        final TextView Info_on = findViewById(R.id.InfoMesageOn);
        final TextView Info_off = findViewById(R.id.InfoMesageOff);

        this.serverThread = new Thread(new ServerThread());
        this.serverThread.start();

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    Info_off.setVisibility(View.INVISIBLE);
                    Info_on.setVisibility(View.VISIBLE);
                }
                else
                {
                    Info_off.setVisibility(View.VISIBLE);
                    Info_on.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ServerThread implements Runnable {

        public void run() {
            Socket socket = null;
            try {
                serverSocket = new ServerSocket(SERVERPORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!Thread.currentThread().isInterrupted()) {

                try {

                    socket = serverSocket.accept();

                    CommunicationThread commThread = new CommunicationThread(socket);
                    new Thread(commThread).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class CommunicationThread implements Runnable {

        private BufferedReader input;

        public CommunicationThread(Socket clientSocket) {

            try {

                this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {

            while (!Thread.currentThread().isInterrupted()) {

                try {

                    String read = input.readLine();

                    Handler updateConversationHandler = null;
                    updateConversationHandler.post(new updateUIThread(read));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class updateUIThread implements Runnable {
        private String msg;

        public updateUIThread(String str) {
            this.msg = str;
        }

        @Override
        public void run() {
            text.setText(text.getText().toString()+"Client Says: "+ msg + "\n");
        }
    }
}
