package com.example.abhishek.pc_control;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.*;
import android.widget.Toast;


public class Connect extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    SQLiteDatabase db;
    EditText ip;
    EditText port;
    String ipt;
    public Connect() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootview=inflater.inflate(R.layout.fragment_connect, container, false);

        ip=(EditText)rootview.findViewById(R.id.ipAddress);
        port=(EditText)rootview.findViewById(R.id.portNumber);
        port.setText("9999");
        port.setClickable(false);
        port.setFocusable(false);
        port.setEnabled(false);
        ipt=ip.getText().toString();
        //Creating Database to store the ip addr

        db=getActivity().openOrCreateDatabase("ipaddress",0,null);
        //Creating Table named user
        db.execSQL("create table if not exists user(id int , ip varchar)");

        Cursor c=db.rawQuery("select * from user",null);
        c.moveToFirst();
        //checking if database is empty
        if(!c.moveToNext())
        {
            db.execSQL("insert into user values('0','192.168.0.100')");
        }

            ip.setText(c.getString(1));

            Button ctn=(Button)rootview.findViewById(R.id.connectButton);
            ctn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(v.getId()==R.id.connectButton){
                    ipt=ip.getText().toString();
                    db.execSQL("update user set ip ='"+ipt+"' ");
                    Intent i=new Intent(getContext(),Holder.class);
                    i.putExtra("ip",ipt);
                    i.putExtra("id","T");
                    startActivity(i);
                }

            }
        });



       return rootview;

    }





}