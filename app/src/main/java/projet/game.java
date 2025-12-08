package projet;

public class game {
    private Player players;
    private Croupier croupier;
    private packet deck;

    public game(String playerName) {
        this.players = new Player(playerName);
        this.croupier = new Croupier();
        this.deck = new packet();
    }

    public void start() {
        for (int i = 0; i < 2; i++) {
            players.addCarte(deck.tirerCarte());
            croupier.addCarte(deck.tirerCarte());
        }
        players.calculateScore();
        croupier.calculateScore();
    }

    public packet getDeck() {
        return deck;
    }

    public Player getPlayers() {
        return players;
    }

    public Croupier getCroupier() {
        return croupier;
    }

    public String getWinner() {
        int playerScore = Math.max(players.getScore()[0], players.getScore()[1]);
        int croupierScore = croupier.getCroupierScore();

        if ((playerScore > croupierScore && playerScore <= 21) || (croupierScore > 21 && playerScore <= 21)) {
            return players.getName() + "  a gagné";
        } else if (playerScore == croupierScore) {
            return "Push";
        } else {
            return "le Croupier a gagné";
        }
    }

    public void reset() {
        this.players = new Player(this.players.getName());
        this.croupier = new Croupier();
        this.deck = new packet();
        this.start();
    }

    public static void main(String[] args) {
        game blackjackGame = new game("Malik");
        blackjackGame.start();
        System.out.println("Croupier's hand: " + blackjackGame.croupier.startHandToString());
        blackjackGame.players.play(blackjackGame.deck);
        blackjackGame.croupier.play(blackjackGame.deck);
        System.out.println(blackjackGame.getWinner());
    }
}
