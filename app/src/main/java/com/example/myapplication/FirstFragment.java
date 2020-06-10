package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FirstFragment extends Fragment {

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

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPacket("http://192.168.1.34:8080/postplain/", "led_status", "on");
                sendPacket("http://192.168.1.35:8080/postplain/", "houselight_status", "off");
                sendPacket("http://192.168.1.23:8080/postplain/", "lock_status", "locked");
                sendPacket("http://192.168.1.22:8080/postplain/", "ottoman_status", "created");
                sendPacket("http://192.168.1.20:8080/postplain/", "ass_status", "poppin");

            }
        });

        view.findViewById(R.id.button_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPacket("http://192.168.1.34:8080/postplain/", "led_status", "off");
                sendPacket("http://192.168.1.35:8080/postplain/", "houselight_status", "on");
                sendPacket("http://192.168.1.23:8080/postplain/", "lock_status", "locked");
                sendPacket("http://192.168.1.22:8080/postplain/", "ottoman_status", "destroyed");
                sendPacket("http://192.168.1.20:8080/postplain/", "ass_status", "not_poppin");
            }
        });

        view.findViewById(R.id.button_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPacket("http://192.168.1.34:8080/postplain/", "led_status", "on"); //light up blue
                sendPacket("http://192.168.1.35:8080/postplain/", "houselight_status", "off");
                sendPacket("http://192.168.1.23:8080/postplain/", "lock_status", "unlocked");
                sendPacket("http://192.168.1.22:8080/postplain/", "ottoman_status", "destroyed");
                sendPacket("http://192.168.1.20:8080/postplain/", "ass_status", "not_poppin"); //light up some other color (green?)
            }
        });

        view.findViewById(R.id.button_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPacket("http://192.168.1.34:8080/postplain/", "led_status", "on");
                sendPacket("http://192.168.1.35:8080/postplain/", "houselight_status", "on");
                sendPacket("http://192.168.1.23:8080/postplain/", "lock_status", "locked");
                sendPacket("http://192.168.1.22:8080/postplain/", "ottoman_status", "created");
                sendPacket("http://192.168.1.20:8080/postplain/", "ass_status", "poppin");
            }
        });

        view.findViewById(R.id.button_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPacket("http://192.168.1.34:8080/postplain/", "led_status", "off");
                sendPacket("http://192.168.1.35:8080/postplain/", "houselight_status", "off");
                sendPacket("http://192.168.1.23:8080/postplain/", "lock_status", "locked");
                sendPacket("http://192.168.1.22:8080/postplain/", "ottoman_status", "destroyed");
                sendPacket("http://192.168.1.20:8080/postplain/", "ass_status", "not_poppin");
            }
        });

        view.findViewById(R.id.button_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPacket("http://192.168.1.34:8080/postplain/", "led_status", "on");
                sendPacket("http://192.168.1.35:8080/postplain/", "houselight_status", "off");
                sendPacket("http://192.168.1.23:8080/postplain/", "lock_status", "locked");
                sendPacket("http://192.168.1.22:8080/postplain/", "ottoman_status", "created");
                sendPacket("http://192.168.1.20:8080/postplain/", "ass_status", "poppin");
            }
        });
    }
}