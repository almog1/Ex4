package com.example.ex4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class JoystickActivity extends AppCompatActivity implements JoystickListener {
    //client for connect to server
    TcpClient tcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JoystickView joystick = new JoystickView(this);
        setContentView(joystick);

        String ip = getIntent().getStringExtra("IP_ADDRESS");
        int port = getIntent().getIntExtra("PORT", 0);

        tcpClient = TcpClient.getInstance(ip, port);
        tcpClient.runClient();

        //wait until the client connected
        while (tcpClient.isConnect == false) {

        }
    }

    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int source) {
        String aileron, elevator;
        aileron = "set " + " /controls/flight/aileron " + xPercent + " \r\n";
        elevator = "set " + "/controls/flight/elevator " + yPercent + " \r\n";
        //x - aileron
        //y - elevator
        tcpClient.sendMessage(aileron);
        tcpClient.sendMessage(elevator);
        //here we sent the mess to the simulator to change params

    }

    @Override
    protected void onDestroy() {
        //stop the client
        this.tcpClient.stopClient();
        super.onDestroy();
    }
}
