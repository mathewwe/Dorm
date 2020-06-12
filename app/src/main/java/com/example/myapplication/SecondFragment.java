package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SecondFragment extends Fragment {

    public void sendPacket(final String url, final String label, final String value){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        Toast.makeText(getActivity().getApplicationContext(), "Could not find node with IP " + url, Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                params.put(label, value);

                return params;
            }
        };
        queue.add(postRequest);
    }

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            Log.d("Progress 1: ", "" + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    SeekBar.OnSeekBarChangeListener seekBarChangeListener2 = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            Log.d("Progress 2: ", "" + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    SeekBar.OnSeekBarChangeListener seekBarChangeListener3 = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            Log.d("Progress 3: ", "" + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SeekBar seekBar = getView().findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        SeekBar seekBar2 = getView().findViewById(R.id.seekBar2);
        seekBar2.setOnSeekBarChangeListener(seekBarChangeListener2);

        SeekBar seekBar3 = getView().findViewById(R.id.seekBar3);
        seekBar3.setOnSeekBarChangeListener(seekBarChangeListener3);

        Switch smartLights = getView().findViewById(R.id.switch1);
        Switch door = getView().findViewById(R.id.switch2);
        Switch ottomanEmpire = getView().findViewById(R.id.switch3);
        Switch houseLights = getView().findViewById(R.id.switch4);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                Log.d("EVENT", "we clickin");
            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Log.d("EVENT", "beer baby");
            }
        });

        view.findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("EVENT", "spotify bby");
                Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.spotify.music");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });

        door.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked){
                    sendPacket("http://192.168.1.23:8080/postplain/", "lock_status", "locked");
                }
                else {
                    sendPacket("http://192.168.1.23:8080/postplain/", "lock_status", "" + "unlocked");
                }
            }
        });

        ottomanEmpire.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if(isChecked){
                    sendPacket("http://192.168.1.23:8080/postplain/", "ottoman_status", "created");
                }
                else {
                    sendPacket("http://192.168.1.23:8080/postplain/", "ottoman_status", "" + "destroyed");
                }
            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPacket("http://192.168.1.33:8080/postplain/", "beer_cannon", "launched"); // Mat fill in your other node for the beer cannon.
            }
        });

        smartLights.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sendPacket("http://192.168.1.34:8080/postplain/", "led_status", "on");
                }
                else{
                    sendPacket("http://192.168.1.34:8080/postplain/", "led_status", "off");
                }
            }
        });

        houseLights.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sendPacket("http://192.168.1.35:8080/postplain/", "houselight_status", "on");
                }
                else{
                    sendPacket("http://192.168.1.35:8080/postplain/", "houselight_status", "off");
                }
            }
        });

    }
}