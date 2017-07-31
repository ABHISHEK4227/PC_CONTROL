package com.example.abhishek.pc_control;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static java.security.AccessController.getContext;

/**
 * Created by Abhishek on 7/18/2017.
 */

public class sendTouchData extends AsyncTask<String,Void,String> {

    String ip;
    sendTouchData(String ip){
        this.ip=ip;
    }

    @Override
    protected String doInBackground(String... params) {

        String mssg=params[0];
        try {
            Socket s = new Socket(ip, 9999);

            OutputStreamWriter out=new OutputStreamWriter(s.getOutputStream());
            out.write(mssg);
            out.close();

        }catch(Exception e){

        }
        return ip;
    }

    @Override
    protected void onPostExecute(String s) {

    }
}
