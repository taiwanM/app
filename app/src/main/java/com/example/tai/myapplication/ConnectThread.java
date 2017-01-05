package com.example.tai.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by tai on 02/01/2017.
 */

public class ConnectThread extends Thread {
    private final BluetoothSocket mmSocket;
    private BluetoothAdapter mBluetoothAdapter;
    private String sUUID = "332b974e-d39b-11e6-bf26-cec0c932ce01";
    private UUID MY_UUID = UUID.fromString(sUUID);

    public ConnectThread(BluetoothDevice device){
        BluetoothSocket tmp = null;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        try{
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
        }catch (IOException e){}
        mmSocket = tmp;
    }

    public void run(){
        mBluetoothAdapter.cancelDiscovery();
        try{
            mmSocket.connect();

        } catch (IOException e) {
            try{
                mmSocket.close();
            } catch (IOException e1) {
                return;
            }
            manageConnectedSocket(mmSocket);
        }
    }

    private void manageConnectedSocket(BluetoothSocket mmSocket) {
    }

    public void cancel() {
        try{
            mmSocket.close();
        } catch (IOException e) {}

    }



}
