
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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

                new bgFrame().setVisible(true);
                new Thread(()->{
                    PlayMP3FromResource("/images/mikumikubeam.mp3");
                    try {
                        Thread.sleep(10000);
                        for (int i = 0; i < 20; i++) {
                            new Thread(() -> {
                                try {
                                    new ProcessBuilder("cmd.exe", "/c", "start", "Watch LOTM", "/max", "cmd.exe", "/k", "echo MIKU MIKU BEAMMMMMMMM").start();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }).start();


                            try {
                                new ProcessBuilder("cmd.exe", "/k", "echo. > \"%userprofile%\\Desktop\\watch lotm.txt").start();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }




                        }
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }).start();



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


