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
        return Math.max(super.getScore()[0], super.getScore()[1]);
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

    public void play(Packet deck) {
        while (true) {
            if (this.getScore()[0] < this.scoreArrest || this.getScore()[1] < this.scoreArrest) {

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.addCarte(deck.tirerCarte());
                this.calculateScore();
                System.out.println("Croupier's hand: " + this.handToString()+ " " + this.scoreToString());
                
            } else {

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                if (this.getHand().length == 2) {
                    System.out.println("Croupier's hand: " + this.handToString()+ " " + this.scoreToString());
                }
                break;
            }
        }
    }
}
