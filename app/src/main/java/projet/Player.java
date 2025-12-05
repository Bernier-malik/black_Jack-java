package projet;

import javax.smartcardio.Card;

public class Player {
    private String name;
    private int[] score;
    private carte[] hand;

    public Player(String name) {
        this.name = name;
        this.score = new int[]{0, 0};
        this.hand = new carte[0];
    }

    public String getName() {
        return name;
    }

    public int[] getScore() {
        return score;
    }

    public void addCarte(carte card) {
        this.hand = java.util.Arrays.copyOf(this.hand, this.hand.length + 1);
        this.hand[this.hand.length - 1] = card;
    }

    public Boolean haveAs() {
        for (carte card : hand) {
            if (card.getValue() == 1) {
                return true;
            }
        }
        return false;
    }

    public void calculateScore() {
        int[] total = {0, 0};
        for (carte card : hand) {
            int value = card.getValue();
            if (value >= 10) {
                total[0] += 10;
                total[1] += 10;
            } else if (value == 1) {
                total[0] += 1;
                total[1] += 11;
            } else {
                total[0] += value;
                total[1] += value;
            }
        }
        this.score = total;
    }

    public String scoreToString() {
        if (score[0] == score[1]) {
            return score[0] + "";
        } else {
            if (score[1] > 21) {
                return score[0] + " Perdu";
            } else if (score[1] == 21 && hand.length == 2) {
                return "BJ";
            } else {
                return score[0] + "/" + score[1];
            }
        }
    }

    public String handToString() {
        String result = "";
        for (carte card : hand) {
            result += card.toString() + " ";
        }
        return result;
    }   
    
    public static void main(String[] args) {
        Player player = new Player("Malik");
        packet deck = new packet();
        player.addCarte(deck.tirerCarte());
        player.addCarte(deck.tirerCarte());
        player.calculateScore();
        System.out.println("Drew card: " + player.handToString());
        System.out.println("Scores: " + player.scoreToString());
    }
}
