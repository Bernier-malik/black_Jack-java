package projet;

import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;

public class interfac {
    public static void main(String[] args) {
        //init la fenetre
        JFrame frame = new JFrame("BlackJack");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //init le jeu
        final packet deck = new packet();
        game blackjackGame = new game("Malik");
        blackjackGame.start();

        //partie bar du menu
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Fichier");
        JMenuItem cartes = new JMenuItem("Afficher cartes");
        JMenuItem restart = new JMenuItem("Restart");
        menu.add(cartes);
        menu.add(restart);
        bar.add(menu);
        frame.setJMenuBar(bar);
    
        //main croupier
        JLabel croupier = new JLabel("" + blackjackGame.getCroupier().startHandToString());
        croupier.setFont(new Font("Serif", Font.BOLD, 80));
        croupier.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(croupier, BorderLayout.NORTH);

        //compte le score du croupier
        JLabel scoreCroupier = new JLabel("" + blackjackGame.getCroupier().scoreToString());
        scoreCroupier.setFont(new Font("Serif", Font.BOLD, 40));
        scoreCroupier.setHorizontalAlignment(SwingConstants.CENTER);
        scoreCroupier.setVerticalAlignment(SwingConstants.TOP);
        scoreCroupier.setVisible(false);
        frame.add(scoreCroupier, BorderLayout.WEST);

        //main joueur 
        JLabel player = new JLabel("" + blackjackGame.getPlayers().handToString());
        player.setFont(new Font("Serif", Font.BOLD, 80));
        player.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(player, BorderLayout.CENTER);

        //compte le score du joueur
        JLabel scorePlayer = new JLabel("" + blackjackGame.getPlayers().scoreToString());
        scorePlayer.setFont(new Font("Serif", Font.BOLD, 40));
        scorePlayer.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(scorePlayer, BorderLayout.EAST);

        //bouttons pour tirer une carte ou rester
        JPanel panel = new JPanel();
        JButton tirer = new JButton("Tirer");
        JButton rester = new JButton("Rester");
        panel.add(tirer);
        panel.add(rester);
        frame.add(panel, BorderLayout.SOUTH);

        tirer.addActionListener(e -> {
            blackjackGame.getPlayers().addCarte(blackjackGame.getDeck().tirerCarte());
            blackjackGame.getPlayers().calculateScore();
            player.setText(blackjackGame.getPlayers().handToString());
            scorePlayer.setText(blackjackGame.getPlayers().scoreToString());
            frame.revalidate();
            frame.repaint();

            if (blackjackGame.getPlayers().getScore()[0] > 21 && blackjackGame.getPlayers().getScore()[1] > 21) {
                String winner = blackjackGame.getWinner();
                JOptionPane.showMessageDialog(frame, winner);
                blackjackGame.reset();
                blackjackGame.getPlayers().getHand();
                blackjackGame.getCroupier().getHand();
                croupier.setText("" + blackjackGame.getCroupier().startHandToString());
                scoreCroupier.setText("" + blackjackGame.getCroupier().scoreToString());
                player.setText("" + blackjackGame.getPlayers().handToString());
                scorePlayer.setText("" + blackjackGame.getPlayers().scoreToString());
                frame.revalidate();
                frame.repaint();
            }
        });

        //fait jouer le croupier quand le joueur clique sur rester
        rester.addActionListener(e -> {
            scoreCroupier.setVisible(true);
            frame.revalidate();
            frame.repaint();
            croupier.setText(blackjackGame.getCroupier().handToString());
            scoreCroupier.setText(blackjackGame.getCroupier().scoreToString());
            blackjackGame.getCroupier().play(blackjackGame.getDeck());
            croupier.setText(blackjackGame.getCroupier().handToString());
            scoreCroupier.setText(blackjackGame.getCroupier().scoreToString());
            frame.revalidate();
            frame.repaint();
            String winner = blackjackGame.getWinner();
            JOptionPane.showMessageDialog(frame, winner);

            blackjackGame.reset();
            scoreCroupier.setVisible(false);
            blackjackGame.getPlayers().getHand();
            blackjackGame.getCroupier().getHand();
            croupier.setText("" + blackjackGame.getCroupier().startHandToString());
            scoreCroupier.setText("" + blackjackGame.getCroupier().scoreToString());
            player.setText("" + blackjackGame.getPlayers().handToString());
            scorePlayer.setText("" + blackjackGame.getPlayers().scoreToString());
            frame.revalidate();
            frame.repaint();
        });

        //action du bouton afficher cartes qui va nous servir de debug 
        restart.addActionListener(e -> {
            blackjackGame.reset();
            blackjackGame.getPlayers().getHand();
            blackjackGame.getCroupier().getHand();
            croupier.setText("" + blackjackGame.getCroupier().startHandToString());
            scoreCroupier.setText("" + blackjackGame.getCroupier().scoreToString());
            player.setText("" + blackjackGame.getPlayers().handToString());
            scorePlayer.setText("" + blackjackGame.getPlayers().scoreToString());
            frame.revalidate();
            frame.repaint();
        });
        
        cartes.addActionListener(e -> JOptionPane.showMessageDialog(frame, deck.toString()));

        frame.setVisible(true);
    }
}