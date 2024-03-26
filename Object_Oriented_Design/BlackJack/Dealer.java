package Object_Oriented_Design.BlackJack;

public class Dealer extends Player {
    private static final int DEALER_MUST_HIT = 17;
    private Player player;

    public Dealer(Player player) {
        this.player = player;
    }

    @Override
    public Action action(Deck d) {
        if (score() < DEALER_MUST_HIT) {
            System.out.println("Dealer must hit under 17");
            printStatus();
            hit(d);
            return Action.Hit;
        } else {
            if (score() > this.player.score()) {
                stand();
                return Action.Stand;
            } else {
                return super.action(d);
            }
        }
    }
}
