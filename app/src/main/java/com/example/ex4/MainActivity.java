package com.example.ex4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    TcpClient myTcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Welcome");
    }

    /**
     * Send to the joystick acrtivity
     * and send all paramters needed
     * @param view
     */
    public void connect(View view) {
        EditText ip, port;
        String ipStr, portStr;
        int realPort;

        // Create an Intent to start the second activity
         Intent joystickIntent = new Intent(this, JoystickActivity.class);
        // Start the new activity.
        ip = (EditText) findViewById(R.id.ipTextUser);
        port = (EditText) findViewById(R.id.portTextUser);

        ipStr = ip.getText().toString();
        portStr = port.getText().toString();
        realPort = Integer.parseInt(portStr);

        joystickIntent.putExtra("IP_ADDRESS", ipStr);
        joystickIntent.putExtra("PORT", realPort);

        //myTcpClient.stopClient();
        startActivity(joystickIntent);

    }
}
