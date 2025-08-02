import javax.swing.*;
import java.awt.*;

public class bgFrame extends JFrame {
    public bgFrame() {
        super("Miku Miku Beam");

        ImageIcon bgMikuIcon = new ImageIcon(getClass().getResource("/images/mikuicon.png"));
        JLabel bgMikuIconLabel = new JLabel(bgMikuIcon);
        add(bgMikuIconLabel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);


    }




}
