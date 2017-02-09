package com.jmajyo.cards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jmajyo.cards.managers.DeckApiManager;
import com.jmajyo.cards.model.Deck;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DeckApiManager apiManager = new DeckApiManager();
        apiManager.setOnNewDeckListener(new DeckApiManager.DeckApimanagerNewDeckListener() {
            @Override
            public void onNewDeck(Deck deck) {

            }
        });
        apiManager.newDeck(this);
    }
}
//https://deckofcardsapi.com/