package com.example.tai.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by tai on 02/01/2017.
 */

public class AcceptThread extends Thread {
    private static final String NAME = "Connect BT";
    private final BluetoothServerSocket mmServerSocket;
    private BluetoothAdapter mBluetoothAdapter;
    private String sUUID = "332b974e-d39b-11e6-bf26-cec0c932ce01";
    private UUID MY_UUID = UUID.fromString(sUUID);

    public  AcceptThread(){
        BluetoothServerSocket tmp = null;
        try {
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
        } catch (IOException e){}
        mmServerSocket = tmp;
    }
    public void run() {
        BluetoothSocket socket = null;
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                break;
            }
            if (socket != null) {
                manageConnectedSocket(socket);
                try {
                    mmServerSocket.close();
                }catch (IOException e){}
                break;
            }
        }
    }

    private void manageConnectedSocket(BluetoothSocket socket) {
    }

    public void cancel(){
            try{
                mmServerSocket.close();
            }catch (IOException e) {}
    }

}


