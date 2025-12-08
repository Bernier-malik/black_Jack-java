package projet;

import java.util.concurrent.TimeUnit;

public class Croupier extends Player {
    private int scoreArrest;

    public Croupier() {
        super("Croupier");
        this.scoreArrest = 17;
    }
    
    public int getScoreArrest() {
        return this.scoreArrest;
    }

    public int getCroupierScore() {
        int[] s = super.getScore();
        return Math.max(s[0], s[1]);
    }

    @Override
    public String scoreToString() {
        int[] s = this.getScore();
        int low = s[0];
        int high = s[1];

        // si on veut cacher le score du croupier quand il n'a que 2 cartes (interface côté joueur)
        if (this.getHand().length == 2) {
            return "?";
        }

        if (low == high && low <= 21) {
            return String.valueOf(low);
        } else {
            if (high == 21 && this.getHand().length == 2) {
                return "BJ";
            } else if (high > 21 && low > 21) {
                return String.valueOf(low);
            } else if (high > 21) {
                return String.valueOf(low);
            } else if (low > 21) {
                return String.valueOf(high);
            } else {
                return String.valueOf(Math.max(low, high));
            }
        }
    }

    public void setScoreArrest(int scoreArrest) {
        this.scoreArrest = scoreArrest;
    }

    public String startHandToString() {
        String hidden = "🂠";
        if (this.getHand().length > 0) {
            return this.getHand()[0].toString() + " " + hidden;
        }
        return hidden;
    }

    @Override
    public void play(packet deck) {
        while (true) {
            int[] s = this.getScore();
            int low = s[0];
            int high = s[1];

            int best;
            if (high <= 21) best = high;
            else if (low <= 21) best = low;
            else {
                // both bust -> stop
                System.out.println("Croupier's hand: " + this.handToString() + " " + this.scoreToString());
                break;
            }

            if (best >= this.scoreArrest) {
                System.out.println("Croupier's hand: " + this.handToString() + " " + this.scoreToString());
                break;
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            carte drawn = deck.tirerCarte();
            if (drawn == null) {
                System.out.println("Deck empty, stopping.");
                break;
            }

            this.addCarte(drawn);
            this.calculateScore();
            System.out.println("Croupier draws: " + drawn + " -> " + this.handToString() + " " + this.scoreToString());
        }
    }
}
