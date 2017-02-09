package com.jmajyo.cards.model;

public class Card {

    public enum Suit{
        SPADES,
        HEARTS,
        DIAMONDS,
        CLUBS
    }
    private Suit suit;
    private String image;

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
