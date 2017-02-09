package com.jmajyo.cards.managers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CardEntity {
    @SerializedName("remaining") private int remaining;
    @SerializedName("success") private boolean success;
    @SerializedName("deck_id") private String deck_id;
    @SerializedName("cards") private List<GsonCard> cards;

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public List<GsonCard> getCards() {
        return cards;
    }

    public void setCards(List<GsonCard> cards) {
        this.cards = cards;
    }

    public class GsonCard{
        @SerializedName("suit") private String suit;
        @SerializedName("image") private String image;
        @SerializedName("images") private Img images;
        @SerializedName("code") private String code;
        @SerializedName("value") private String value;

        public String getSuit() {
            return suit;
        }

        public void setSuit(String suit) {
            this.suit = suit;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Img getImages() {
            return images;
        }

        public void setImages(Img images) {
            this.images = images;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    public class Img{
        @SerializedName("svg") private String svg;
        @SerializedName("png") private String png;

        public String getSvg() {
            return svg;
        }

        public void setSvg(String svg) {
            this.svg = svg;
        }

        public String getPng() {
            return png;
        }

        public void setPng(String png) {
            this.png = png;
        }
    }
}
