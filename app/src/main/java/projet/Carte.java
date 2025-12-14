package projet;

import projet.Couleur;

public class Carte {
    private int value;
    private Couleur couleur;
    private int value_game;

    //https://fr.piliapp.com/symbol/card-suit/
    String[] pic_card =     {"🂡", "🂢", "🂣", "🂤", "🂥", "🂦", "🂧", "🂨", "🂩", "🂪", "🂫", "🂭", "🂮"};
    String[] coeur_card =   {"🂱", "🂲", "🂳", "🂴", "🂵", "🂶", "🂷", "🂸", "🂹", "🂺", "🂻", "🂽", "🂾"};
    String[] carreau_card = {"🃁", "🃂", "🃃", "🃄", "🃅", "🃆", "🃇", "🃈", "🃉", "🃊", "🃋", "🃍", "🃎"};
    String[] trefle_card =  {"🃑", "🃒", "🃓", "🃔", "🃕", "🃖", "🃗", "🃘", "🃙", "🃚", "🃛", "🃝", "🃞"};


    public Carte(int value, Couleur couleur) {
        this.value = value;
        this.couleur = couleur;
        if (value >= 10) {
            this.value_game = 10;
        } else {
            this.value_game = value;
        }
    }

    public int getValue() {
        return value;
    }

    public Couleur getCouleur() {
        return this.couleur; 
    }

    public int getValueGame() {
        return this.value_game;
    }

    public String toSymbole(Couleur couleur) {
        switch(couleur) {
            case COEUR:
                return "♥";
            case CARREAU:
                return "♦";
            case TREFLE:
                return "♣";
            case PIQUE:
                return "♠";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        if (this.couleur == Couleur.COEUR) {
            return coeur_card[this.value - 1];
        } else if (this.couleur == Couleur.CARREAU) {
            return carreau_card[this.value - 1];
        } else if (this.couleur == Couleur.TREFLE) {
            return trefle_card[this.value - 1];
        } else {
            return pic_card[this.value - 1];
        }
        
    }

    
    public static void main(String[] args) {
        Carte card = new Carte(10, Couleur.COEUR);
        System.out.println(card.toString());
    }

}
