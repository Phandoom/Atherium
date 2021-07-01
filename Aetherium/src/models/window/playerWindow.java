package models.window;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class playerWindow {

    static ImageIcon image1 = new ImageIcon("C:\\Users\\burak\\OneDrive\\Desktop\\Minecraft Modding\\ItsDefinitelyNotWOW\\src\\resources\\Player.png");
    static JLabel player = new JLabel(image1);

    public static void createWindow(Game game) {


        JFrame window = new JFrame("Aetherium");
        window.setVisible(true);
        window.setResizable(true);
        window.setIconImage(image1.getImage());
        window.setSize(800, 600);  //800, 600
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.add(game);
    }
}
