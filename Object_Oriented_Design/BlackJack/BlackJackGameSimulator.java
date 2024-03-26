package Object_Oriented_Design.BlackJack;

public class BlackJackGameSimulator {
    private Deck deck;
    private Player player;
    private Dealer dealer;

    public BlackJackGameSimulator() {
        deck = new Deck();
        deck.shuffle();
        player = new Player();
        dealer = new Dealer(player);

        // initialization : each players gets 2 cards
        player.hit(deck);
        dealer.hit(deck);
        player.hit(deck);
        dealer.hit(deck);
    }

    private void printStatus() {
        player.printStatus();
        dealer.printStatus();
    }

    public void simulate() {
        if (player.isBlackJack()){
            if (dealer.isBlackJack()) {
                System.out.println("-- Draw --");
            } else {
                System.out.println("-- Player Black Jack Win! --");
            }
            return ;
        }

        if (dealer.isBlackJack()) {
            System.out.println("-- Dealer Black Jack Win! --");
            printStatus();
            return ;
        }
        
        System.out.println("-- Player round --");
        while (player.action(this.deck) == Action.Hit) {
            if (player.isBusted()) {
                System.out.println("-- Dealer wins! --");
                printStatus();
                return ;
            }
        }

        System.out.println("-- Dealer Round --");
        while (dealer.action(this.deck) == Action.Hit) {
            if (dealer.isBusted()) {
                System.out.println("-- Player wins! --");
                printStatus();
                return ;
            }
        }

        if (player.score() > dealer.score()) {
            System.out.println("-- Player wins! --");
        } else if (player.score() < dealer.score()) {
            System.out.println("-- Dealer wins! --");
        } else {
            System.out.println("-- Draw --");
        }
        printStatus();
    }

    public static void main(String[] args) {
        BlackJackGameSimulator simulator = new BlackJackGameSimulator();
        simulator.simulate();
    }
}
