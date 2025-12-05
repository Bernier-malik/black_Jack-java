package projet;

public class packet {
    private carte[] cards;

    public packet() {
        this.cards = new carte[52];
        for (int i = 0; i < 52; i++) {
            int value = (i % 13) + 1;
            Couleur couleur;
            if (i < 13) {
                couleur = Couleur.COEUR;
            } else if (i < 26) {
                couleur = Couleur.CARREAU;
            } else if (i < 39) {
                couleur = Couleur.TREFLE;
            } else {
                couleur = Couleur.PIQUE;
            }
            this.cards[i] = new carte(value, couleur);
        }
        this.melanger();
    }

    public carte[] getCards() {
        return cards;
    }

    public void melanger() {
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < cards.length; i++) {
            int randomIndexToSwap = rand.nextInt(cards.length);
            carte temp = cards[randomIndexToSwap];
            cards[randomIndexToSwap] = cards[i];
            cards[i] = temp;
        }
    }

    public carte tirerCarte() {
        if (cards.length == 0) {
            return null;
        }
        carte drawnCard = cards[0];
        carte[] newCards = new carte[cards.length - 1];
        System.arraycopy(cards, 1, newCards, 0, newCards.length);
        cards = newCards;
        return drawnCard;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < this.cards.length; i++) {
            
            if (i % 13 == 12) {
                result += cards[i].toString() + "\n";
            } else {
                result += cards[i].toString() + " ";
            }
            
        }
        return result;
    }

    public static void main(String[] args) {
        packet deck = new packet();
        System.out.println(deck.toString());
        System.out.println("Tirer une carte: " + deck.tirerCarte().toString());
        System.out.println("Tirer une carte: " + deck.tirerCarte().toString());
        System.out.println("Deck après avoir tiré une carte:");
        System.out.println(deck.toString());
    }
    
}
