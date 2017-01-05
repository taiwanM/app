package com.example.tai.myapplication;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by tai on 04/01/2017.
 */

public class ConnectedThread extends Thread {
    private final int  MESSAGE_READ = 2;
    private final BluetoothSocket mmsocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private Handler mHandler;

    public ConnectedThread(BluetoothSocket socket){
        mmsocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }
    public void run(){
        byte[] buffer = new byte[1024];
        int bytes;

        while (true){
            try{
                bytes = mmInStream.read(buffer);
                mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
    public void write(byte[] bytes){
        try{
            mmOutStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void cancel(){
        try{
            mmsocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
