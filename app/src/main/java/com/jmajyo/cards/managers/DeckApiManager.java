package com.jmajyo.cards.managers;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jmajyo.cards.model.Deck;

import java.io.Reader;
import java.io.StringReader;

public class DeckApiManager {

    public interface DeckApimanagerNewDeckListener {
        public void onNewDeck(Deck deck);
    }

    private DeckApimanagerNewDeckListener listener;

    public void setOnNewDeckListener(DeckApimanagerNewDeckListener listener) {
        this.listener = listener;
    }

    private static final String NEW_DECK_QEQUEST = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";

    public void newDeck(Context context){
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(NEW_DECK_QEQUEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE",response);
                parseJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("HORROR", "Connection went to shit to the tracks");
            }
        });

        queue.add(request);//La cola es la que va sacando la petición
    }
    private void parseJSON(String response) {
        Reader reader= new StringReader(response);
        Gson gson = new GsonBuilder().create();

        DeckEntity deckEntity = gson.fromJson(reader, DeckEntity.class);

        Deck deck = new Deck();
        deck.setId(deckEntity.getDeck_id());
        deck.setRemaining(deckEntity.getRemaining());

        if(listener !=null){
            listener.onNewDeck(deck);
        }
    }
}
