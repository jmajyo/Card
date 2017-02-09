package com.jmajyo.cards.managers;

import com.google.gson.annotations.SerializedName;

public class DeckEntity {
    @SerializedName("remaining") private int remaining;
    @SerializedName("deck_id") private String deck_id;
    @SerializedName("success") private boolean success;
    @SerializedName("shuffled") private boolean shuffled;

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }
}
