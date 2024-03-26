package Object_Oriented_Design.BlackJack;

import java.util.*;

public class Deck {
    private static final Random random = new Random();
    private final Card[] cards = new Card[52];
    private int dealtIndex = 0;

    public Deck() {
        int index = 0;
        for (int i = 1; i <= 13; i++) {
            for (Suit suit : Suit.values()) {
                cards[index++] = new Card(i, suit);
            }
        }
    }

    public void shuffle() {
        for (int i = cards.length - 1; i >= 0; i--) {
            int j = random.nextInt(i + 1);
            swap(cards, i, j);
        }
    }

    private int remainingCards() {
        return cards.length - dealtIndex;
    }

    public Card dealCard() {
        return remainingCards() == 0 ? null : cards[dealtIndex++];
    }

    private void swap(Card[] cards, int i, int j) {
        Card temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
    }
}
