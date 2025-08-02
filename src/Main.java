
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");

        }

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);



        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("images/mikuicon.png", "Miku Beam Icon"));
        final SystemTray tray = SystemTray.getSystemTray();

        trayIcon.setImageAutoSize(true);

        MenuItem beambutton = new MenuItem("Ready?");
        Menu displayMenu = new Menu("Settings");

        popup.add(beambutton);
        popup.addSeparator();
        popup.add(displayMenu);


        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }

        beambutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                PlayMP3FromResource("/images/mikumikubeam.mp3");
                try {
                    thread.sleep(10000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                ProcessBuilder pb = new ProcessBuilder();
                try{
                    pb.start();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }





    protected static Image createImage(String path, String description) {
        URL imageURL = Main.class.getResource("/" + path);
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    private static void PlayMP3FromResource(String resourcePath) {
        new Thread(() -> {
            try {
                var resource = Main.class.getResourceAsStream(resourcePath);
                if (resource == null) {
                    System.err.println("File not found in resources: " + resourcePath);
                    return;
                }
                Player player = new Player(resource);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }



}


