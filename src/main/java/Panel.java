import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable{
    //screen settings
    final int gameTileSize = 16;
    final int scaleRatio = 3;
    final int finalTileSize = gameTileSize * scaleRatio; // 48
    final int maxWindowCol = 20;
    final int maxWindowRow = 16;
    final int screenWidth = finalTileSize * maxWindowCol; // 960
    final int screenHeight = finalTileSize * maxWindowRow; // 768

    KeyEventHandler keyX = new KeyEventHandler(); // handles key events
    Thread gameThread; // create thread for game
    Player player = new Player(this, keyX);

    //player starting position
    int playerPositionX = 100;
    int playerPositionY = 100;
    int playerMovementSpeed = 3;

    double framesPerSecond = 60;

    public Panel() {    //layout for game panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyX);
        this.setFocusable(true);
    }

    public void startGame() {   //start the game
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // create game thread (while loop keeps game running, each iteration is a frame)

        double drawInterval = 1_000_000_000/framesPerSecond; // 0.016 seconds to "slow down" game frames
        double delta = 0;
        long previousTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime(); //get system time
            delta += (currentTime - previousTime) / drawInterval; // find difference in time and divide by frames
            previousTime = currentTime; //update time


            if (delta >= 1) {
                sync(); // update game objects position
                repaint(); // uses paint method
                delta--;
            }
        }
    }

    public void sync() {    //update user positions on each frame
        player.sync();
    }

    public void paint(Graphics graphics){   //create game character
        super.paint(graphics);
        //draw player
        Graphics2D graphics2D = (Graphics2D)graphics;
        player.paint(graphics2D);
        graphics2D.dispose();
    }
}
