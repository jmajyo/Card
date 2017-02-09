package com.jmajyo.cards.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmajyo.cards.R;
import com.jmajyo.cards.managers.CardApiManager;
import com.jmajyo.cards.managers.DeckApiManager;
import com.jmajyo.cards.model.Card;
import com.jmajyo.cards.model.Deck;

public class MainActivity extends AppCompatActivity {
    private TextView numberOfCardInDeck;
    private ImageView cardView;

    private Deck deck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOfCardInDeck = (TextView) findViewById(R.id.acttivity_main___cards_left_text);
        cardView = (ImageView) findViewById(R.id.activity_main___cards_image);

        DeckApiManager apiManager = new DeckApiManager();
        apiManager.setOnNewDeckListener(new DeckApiManager.DeckApimanagerNewDeckListener() {
            @Override
            public void onNewDeck(Deck deckFromJson) {
                numberOfCardInDeck.setText("" + deckFromJson.getRemaining());
                deck = deckFromJson;
            }
        });
        apiManager.newDeck(this);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardApiManager cardApiManager = new CardApiManager();
                cardApiManager.setListener(new CardApiManager.CardApiManagerNewCardListener() {
                    @Override
                    public void onNewCard(Card card) {

                    }
                });
                cardApiManager.newCard(v.getContext(), deck);
            }
        });


    }

}
//https://deckofcardsapi.com/