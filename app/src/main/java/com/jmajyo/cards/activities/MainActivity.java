package com.jmajyo.cards.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmajyo.cards.R;
import com.jmajyo.cards.managers.CardApiManager;
import com.jmajyo.cards.managers.DeckApiManager;
import com.jmajyo.cards.model.Card;
import com.jmajyo.cards.model.Deck;
import com.jmajyo.cards.utils.Temblator;
import com.jmajyo.cards.utils.Trick;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private TextView numberOfCardInDeck;
    private ImageView cardView;
    private Button newDeck;
    private ImageView finalCard;

    private Button oneButtonInv;
    private Button twoButtonInv;
    private EditText ipText;
    private Button saveIP;

    private Deck deck;

    String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOfCardInDeck = (TextView) findViewById(R.id.acttivity_main___cards_left_text);
        cardView = (ImageView) findViewById(R.id.activity_main___cards_image);
        newDeck = (Button) findViewById(R.id.activity_main___newDeck_button);
        finalCard = (ImageView) findViewById(R.id.activity_main___cards_image_final);

        oneButtonInv = (Button) findViewById(R.id.activity_main___1_button_invisible);
        twoButtonInv = (Button) findViewById(R.id.activity_main___2_button_invisible);
        ipText = (EditText) findViewById(R.id.activity_main___text_ip);
        saveIP = (Button) findViewById(R.id.activity_main___button_ip);


        newDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Temblator.tremble(v.getContext(),50);
                DeckApiManager apiManager = new DeckApiManager();
                apiManager.setOnNewDeckListener(new DeckApiManager.DeckApimanagerNewDeckListener() {
                    @Override
                    public void onNewDeck(Deck deckFromJson) {
                        numberOfCardInDeck.setText("" + deckFromJson.getRemaining());
                        deck = deckFromJson;
                        cardView.setVisibility(View.VISIBLE);
                    }
                });
                apiManager.newDeck(v.getContext());
                finalCard.setVisibility(View.INVISIBLE);
                newDeck.setVisibility(View.INVISIBLE);
                cardView.setImageResource(R.drawable.card_back_blue);

            }
        });


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Temblator.tremble(v.getContext(),50);
                twoButtonInv.setVisibility(View.INVISIBLE);
                ipText.setVisibility(View.INVISIBLE);
                saveIP.setVisibility(View.INVISIBLE);
                CardApiManager cardApiManager = new CardApiManager();
                cardApiManager.setListener(new CardApiManager.CardApiManagerNewCardListener() {
                    @Override
                    public void onNewCard(Card card) {
                        Picasso.with(MainActivity.this).load(card.getImage()).placeholder(R.drawable.card_back_blue).into(cardView);
                        numberOfCardInDeck.setText("" + card.getRemains());
                        //petición para el truco
                        Trick.sendPost(card,v.getContext(),ip);
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

        oneButtonInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoButtonInv.setVisibility(View.VISIBLE);
            }
        });
        twoButtonInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ipText.setVisibility(View.VISIBLE);
                saveIP.setVisibility(View.VISIBLE);
            }
        });
        saveIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Temblator.tremble(view.getContext(),50);
                ip = ipText.getText().toString();
                ipText.setVisibility(View.INVISIBLE);
                saveIP.setVisibility(View.INVISIBLE);
                twoButtonInv.setVisibility(View.INVISIBLE);
            }
        });

    }

}
//https://deckofcardsapi.com/