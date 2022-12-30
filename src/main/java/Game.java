
import javax.swing.*;
import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame(); // create new window with jframe
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows window to be closable
        window.setTitle("Carnival Exile"); // set title on top of window
        window.setVisible(true); // allows user to see window
        window.setResizable(false);
        window.setLocationRelativeTo(null); //To center the code

        Panel panel = new Panel(); // create game instance
        window.add(panel);
        window.pack(); // The pack method sizes the frame so that all its contents are at or above their preferred size

        panel.startGame(); // start game
    }
}
