package com.example.ex4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    TcpClient myTcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connect(View view) {
        // Create an Intent to start the second activity
        // Intent randomIntent = new Intent(this, JoystickActivity.class);
        // Start the new activity.
        //startActivity(randomIntent);
        myTcpClient = TcpClient.getInstance("10.0.2.2", 4010);
        myTcpClient.runClient();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        } ;
        myTcpClient.sendMessage("myMessage");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        } ;
        myTcpClient.sendMessage("Check message");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        } ;
        myTcpClient.sendMessage("myMessage2");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        } ;
        myTcpClient.sendMessage("myMessageEND");
        int count = 0;
        myTcpClient.stopClient();
        // while (myTcpClient.isConnect == false) {
        //   myTcpClient.sendMessage("HELLO SERVER " + count);
        // count++;
        // }
        //myTcpClient.stopClient();
    }
}
