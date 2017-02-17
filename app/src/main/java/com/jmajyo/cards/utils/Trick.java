package com.jmajyo.cards.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jmajyo.cards.model.Card;

import java.util.HashMap;
import java.util.Map;

public class Trick {
        private static final String BASEURL = "http://";
        private static final String FINURL = "/api/card";
        private static final String dirIp ="192.168.0.121:800";

    public Trick() {
    }

    public static void sendPost(Card card, Context context, String ip){
        final String image;
        String URL;
        if(ip==null || ip=="")
        {
            URL=BASEURL+dirIp+FINURL;
        }else{
            URL=BASEURL+ip+FINURL;
        }

        image = card.getImage();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Trick", "Todo ha ido bien");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Trick", "Algo ha ido mal" + error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                //params.put("Content-Type","application/json");
                params.put("image", image);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Log.d("Trick", stringRequest.toString());
    }
}
