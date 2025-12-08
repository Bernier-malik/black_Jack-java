import javax.swing.*;
import java.awt.*;

public class Exemple {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GridLayout");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridLayout(3, 3));
        GridBagConstraints c = new GridBagConstraints();

        JLabel l1 = new JLabel("Label du haut");
        JLabel l2 = new JLabel("Label du bas");
        JLabel l3 = new JLabel("Label du milieu");

        c.gridx = 2;   // colonne
        c.gridy = 2;   // ligne
        frame.add(l1,c);

        c.gridx = 1;   // colonne
        c.gridy = 1;   // ligne
        frame.add(l3,c);
        c.gridx = 2;   // colonne
        c.gridy = 3;   // ligne
        frame.add(l2,c);

        frame.setVisible(true);

    }
}
