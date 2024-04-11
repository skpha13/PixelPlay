package cardGames.cardDeck;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int size;

    public Deck() {
        this.size = 52;
        this.cards = new ArrayList<>();
        for (String suit : new String[] {"Hearts", "Diamonds", "Clubs", "Spades"}) {
            for (String value : new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"}) {
                this.cards.add(new Card(suit, value));
            }
        }

    }

    public int getSize() {
        return this.size;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void shuffle() {
        for (int i = 0; i < this.size; i++) {
            int randomIndex = (int) (Math.random() * this.size);
            Card temp = this.cards.get(i);
            this.cards.set(i, this.cards.get(randomIndex));
            this.cards.set(randomIndex, temp);
        }
    }

    public Card draw() {
        if (this.size == 0) {
            return null;
        }
        this.size--;
        return this.cards.get(this.size);
    }


    @Override
    public String toString() {
        String result = "";
        for (Card card : this.cards) {
            result += card.toString() + "\n";
        }
        return result;
    }

    @Override
    public int hashCode() {
        return this.cards.hashCode() + this.size;
    }
}
