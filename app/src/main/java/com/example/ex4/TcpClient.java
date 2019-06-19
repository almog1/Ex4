package com.example.ex4;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

//class for the connection client
public class TcpClient {
    private static TcpClient INSTANCE = null;

    public static TcpClient getInstance(String ip, int port) {
        if (INSTANCE == null) {
            INSTANCE = new TcpClient(ip, port);
        }
        return (INSTANCE);
    }

    //create new tcp client object
    private TcpClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        isConnect = false;
    }

    public String ip;
    public int port;
    boolean isConnect;

    Socket socket;
    // used to send messages
    private PrintWriter mBufferOut;

    /**
     * Sends the message entered by client to the server
     *
     * @param message text entered by client
     */
    public void sendMessage(final String message) {
       // Runnable runnable = new Runnable() {
        final Thread thread = new Thread(){
        @Override
            public void run() {
                if (mBufferOut != null) {
                //    mBufferOut.write("HELLO",0,5);
                    try {
                        String msg =  message+" \r\n";
                        mBufferOut.print(msg);
                        mBufferOut.flush();
                    }catch (Exception e){
                        System.out.println("FAIL");
                    }
                    //   mBufferOut.println("HELOO CHECK");
                }
            }
        };
        thread.start();
    }

    /**
     * Close the connection and release the members
     */
    public void stopClient() {
        try {
            socket.close();
        }catch (Exception e){}
        if (mBufferOut != null) {
            mBufferOut.flush();
            mBufferOut.close();
        }
        mBufferOut = null;
    }

    /**
     * Run the client - connect to the server
     */
    public void runClient() {
        final Thread thread = new Thread(){
            //coonect to the server
            public void run() {
                try {
                    //here you must put your computer's IP address.
                    InetAddress serverAddr = InetAddress.getByName("10.0.2.2");
                    //create a socket to make the connection with the server
//                    socket = new Socket(serverAddr, 4008);
                    socket = new Socket("10.0.2.2", 4010);
                    try {
                        isConnect = true;
                        mBufferOut = new PrintWriter(socket.getOutputStream(),true);
                       // while (true){
                        //}
                        //crete buffer
                    //    mBufferOut = new PrintWriter(socket.getOutputStream(),true);

                    } catch (Exception e) {
                        System.out.println("ERROR");
                    }
                } catch (Exception e) {
                    System.out.println("ERROR 2");
                    Log.e("TCP", "C: Error", e);
                }

            }
        };
        thread.start();

    }
}
