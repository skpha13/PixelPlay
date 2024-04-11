package cardGames.cardDeck;

import java.util.ArrayList;

public class Card {

    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(Card card) {
        return this.suit.equals(card.suit) && this.value.equals(card.value);
    }

    public boolean equalsSuit(Card card) {
        return this.suit.equals(card.suit);
    }

    public boolean equalsValue(Card card) {
        return this.value.equals(card.value);
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    @Override
    public int hashCode() {
        return suit.hashCode() + value.hashCode();
    }

}
