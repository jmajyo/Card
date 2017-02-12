package com.jmajyo.cards.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jmajyo.cards.R;
import com.jmajyo.cards.managers.CardApiManager;
import com.jmajyo.cards.managers.DeckApiManager;
import com.jmajyo.cards.model.Card;
import com.jmajyo.cards.model.Deck;
import com.jmajyo.cards.utils.Trick;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private TextView numberOfCardInDeck;
    private ImageView cardView;
    private Button newDeck;
    private ImageView finalCard;

    private Deck deck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOfCardInDeck = (TextView) findViewById(R.id.acttivity_main___cards_left_text);
        cardView = (ImageView) findViewById(R.id.activity_main___cards_image);
        newDeck = (Button) findViewById(R.id.activity_main___newDeck_button);
        finalCard = (ImageView) findViewById(R.id.activity_main___cards_image_final);

        newDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeckApiManager apiManager = new DeckApiManager();
                apiManager.setOnNewDeckListener(new DeckApiManager.DeckApimanagerNewDeckListener() {
                    @Override
                    public void onNewDeck(Deck deckFromJson) {
                        numberOfCardInDeck.setText("" + deckFromJson.getRemaining());
                        deck = deckFromJson;
                    }
                });
                apiManager.newDeck(v.getContext());
                finalCard.setVisibility(View.INVISIBLE);//No hace esto porque??
                newDeck.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.VISIBLE);
                cardView.setImageResource(R.drawable.card_back_blue); //tengo que poner esto porque finalCard no se hace invisible

            }
        });


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                CardApiManager cardApiManager = new CardApiManager();
                cardApiManager.setListener(new CardApiManager.CardApiManagerNewCardListener() {
                    @Override
                    public void onNewCard(Card card) {
                        Picasso.with(MainActivity.this).load(card.getImage()).placeholder(R.drawable.card_back_blue).into(cardView);
                        numberOfCardInDeck.setText("" + card.getRemains());
                        //petición para el truco
                        Trick.sendPost(card,v.getContext());
                        if (card.getRemains()==0){
                            newDeck.setVisibility(View.VISIBLE);
                            cardView.setVisibility(View.INVISIBLE);
                            finalCard.setVisibility(View.VISIBLE);
                            Picasso.with(MainActivity.this).load(card.getImage()).placeholder(R.drawable.card_back_blue).into(finalCard);
                        }
                    }
                });
                cardApiManager.newCard(v.getContext(), deck);
            }
        });


    }

}
//https://deckofcardsapi.com/