package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatapp.R;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Login extends AppCompatActivity {
    EditText host_name;
    TextView ip_adress , name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init()
    {
        host_name = (EditText) findViewById(R.id.host_name);
        ip_adress = (TextView) findViewById(R.id.ip_adress);
        name = (TextView)findViewById(R.id.name);
        ip_adress.setText("Your Ip : "+getLocalIpAddress(getApplicationContext()));
    }


    public void login(View view) {
        Intent intent = new Intent(getApplicationContext(),ChatRoum.class);
        intent.putExtra("host",host_name.getText().toString());
        intent.putExtra("name",name.getText().toString());
        startActivity(intent);
    }

    public  String getLocalIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        return  ipAddress ;
    }

}