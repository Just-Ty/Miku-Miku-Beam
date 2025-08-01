import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");

        }
    }
}