package Object_Oriented_Design.BlackJack;

import java.util.*;

public class Player {
    private static final Random random = new Random();
    private static final double hitRatio = 0.5;

    protected final List<Card> cards = new ArrayList<>();

    public Action action (Deck d) {
        if(random.nextDouble() < hitRatio) {
            hit(d);
            return Action.Hit;
        } else {
            stand();
            return Action.Stand;
        }
    }

    public int score() {
        Collections.sort(cards, Collections.reverseOrder());
        int score = 0;
        int i = 0;
        while (i < cards.size()) {
            Card c = cards.get(i);
            if (c.isFace()) {
                score += 10;
            } else if (!c.isAce()) {
                score += c.value();
            } else {
                break;
            }
            i++;
        }

        int numAces = cards.size() - i;
        if (numAces == 0) {
            return score <= 21 ? score : 0;
        }
        int max = numAces + 10;
        int min = numAces;
        if (score + min > 21) {
            return 0;
        } else if (score + max > 21) {
            return score + min;
        } else {
            return score + max;
        }
    }

    public boolean isBusted() {
        return score() == 0;
    }

    public void hit(Deck d) {
        cards.add(d.dealCard());
    }

    public void stand() {}

    public boolean isBlackJack() {
        if (cards.size() != 2) {
            return false;
        }
        Card first = cards.get(0);
        Card second = cards.get(1);
        return (first.isAce() && second.isFace()) || (first.isFace() && second.isAce());
    }

    public void printStatus() {
        System.out.println("Score: " + score());
        for (Card c : cards) {
            System.out.println(cards.toString());
        }
    }
}
