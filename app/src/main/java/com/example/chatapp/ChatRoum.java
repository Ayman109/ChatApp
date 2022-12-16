package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;


public class ChatRoum extends AppCompatActivity {
    EditText send ;
    Button buSend ;
    RecyclerView recyclerView ;
    ServerSocket serverSocket ;
    String m="" ;
    MessageAdapter adapter;
    ArrayList<Message> messages ;
    Intent intent ;
    LocalData localData ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_roum);
        intent = getIntent();

        localData = new LocalData("Discussion"+intent.getStringExtra("name"),getApplicationContext());
        Thread thread ;
        send = (EditText) findViewById(R.id.editTextMessage);
        buSend = (Button) findViewById(R.id.button);
        RecyclerView list = (RecyclerView) findViewById(R.id.list_messages);
        messages=new ArrayList<Message>();
        messages=localData.getMessages();
        list.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        adapter= new MessageAdapter(getApplicationContext(),messages);
        list.setAdapter(adapter);


        buSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                           Socket socket = new Socket(intent.getStringExtra("host"),8080);
                            OutputStream os = socket.getOutputStream();
                            PrintWriter pw = new PrintWriter(os,true);
                            pw.println(send.getText().toString());
                            messages.add(new Message(new Date().toString(),"Me",send.getText().toString()));
                            localData.insertMessage(messages.get(messages.size()-1));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyItemChanged(messages.size());
                                    adapter.notifyItemRangeChanged(messages.size(),adapter.getItemCount());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
                t.start();



            }
        });

        Server serve = new Server();
        serve.start();




    }

    private class Server extends Thread {

        @Override
        public void run(){
            try {
                serverSocket = new ServerSocket(8080);

                while(true){
                    Socket socket = serverSocket.accept();
                    InputStream is = socket.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    m = br.readLine();
                    messages.add(new Message(new Date().toString(),intent.getStringExtra("name"),m));
                    localData.insertMessage(messages.get(messages.size()-1));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyItemChanged(messages.size());
                            adapter.notifyItemRangeChanged(messages.size(),adapter.getItemCount());
                        }
                    });

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }




}