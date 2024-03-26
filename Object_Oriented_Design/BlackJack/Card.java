package Object_Oriented_Design.BlackJack;

public class Card implements Comparable<Card> {
    private final int faceValue; // 1 for Ace, 11, 12, 13 for J,Q,K
    private final Suit suit;

    public Card(int val, Suit s) {
        faceValue = val;
        suit = s;
    }

    public int value() {
        return faceValue;
    }

    public Suit suit() {
        return suit;
    }

    public boolean isAce() {
        return faceValue == 1;
    }

    public boolean isFace() {
        return faceValue > 10;
    }

    public String num() {
        switch (faceValue) {
            case 1:
                return "Ace";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return faceValue + "";
        }
    }

    @Override
    public String toString() {
        return "Card { " + suit +
            " " + num() + " }";
    }

    @Override
    public int compareTo(Card c) {
        return this.faceValue - c.faceValue;
    }
}
