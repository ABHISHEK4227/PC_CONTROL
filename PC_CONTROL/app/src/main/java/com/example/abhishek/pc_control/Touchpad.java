package com.example.abhishek.pc_control;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.net.Socket;


public class Touchpad extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView mousepad;
    Button Left;
    Button Right;
    Button Up;
    Button Down;
    float initX;
    float initY;
    float disX;
    float disY;

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
    View RootView=inflater.inflate(R.layout.fragment_touchpad, container, false);
    mousepad=(TextView)RootView.findViewById(R.id.touchPadView);

    Left=(Button)RootView.findViewById(R.id.leftClick);
    Right=(Button)RootView.findViewById(R.id.rightClick);
    Up=(Button)RootView.findViewById(R.id.Scrollup);
    Down=(Button)RootView.findViewById(R.id.Scrolldown);


   final  String ip=getArguments().getString("ip");
        Toast.makeText(getContext(),ip,Toast.LENGTH_SHORT).show();

        //Handling click events for left click
        Left.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               new sendTouchData(ip).execute("L 0 0");
            }
        });

        //Handling click events for right click
        Right.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                new sendTouchData(ip).execute("R 0 0");
            }
        });

        Up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                new sendTouchData(ip).execute("U 0 0");
            }

        });

        Down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                new sendTouchData(ip).execute("D 0 0");
            }

        });





      mousepad.setOnTouchListener(new View.OnTouchListener() {

          @Override
          public boolean onTouch(View v, MotionEvent event) {

              switch (event.getAction() & MotionEvent.ACTION_MASK) {
                  case MotionEvent.ACTION_DOWN:
                      //save X and Y positions when user touches the TextView
                      initX = (int) event.getX();
                      initY = (int) event.getY();

                      break;
                  case MotionEvent.ACTION_MOVE:

                      disX = (int) event.getX() - initX; //Mouse movement in x direction
                      disY = (int) event.getY() - initY; //Mouse movement in y direction
                                /*set init to new position so that continuous mouse movement
                                is captured*/
                      initX = (int) event.getX();
                      initY = (int) event.getY();
                      if (disX != 0 || disY != 0) {
                          new sendTouchData(ip).execute("T " + disX + " " + disY);
                      }


                      break;

              }
              return true;
          }


        } );

        //new sendTouchData().execute(ip);
        return RootView;

    }




}
