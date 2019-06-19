package com.example.ex4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        myTcpClient = TcpClient.getInstance("10.0.2.2", 4012);
        myTcpClient.runClient();
        int count = 0;
       // while (myTcpClient.isConnect == false) {
         //   myTcpClient.sendMessage("HELLO SERVER " + count);
           // count++;
       // }
        //myTcpClient.stopClient();
    }
}
