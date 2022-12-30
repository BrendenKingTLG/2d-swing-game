import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Panel extends JPanel implements Runnable{
    //screen settings
    final int gameTileSize = 16;
    final int scaleRatio = 3;
    final int finalTileSize = gameTileSize * scaleRatio; // 48
    final int maxWindowCol = 20;
    final int maxWindowRow = 16;
    final int screenWidth = finalTileSize * maxWindowCol; // 960
    final int screenHeight = finalTileSize * maxWindowRow; // 768

    //world settings
    final int maxWorldCol = 66;
    final int maxWorldRow = 50;
    final int worldHeight = finalTileSize * maxWindowCol;
    final int worldWidth = finalTileSize * maxWorldRow;

    //composition of game peaces
    KeyEventHandler keyX = new KeyEventHandler(); // handles key events
    Thread gameThread; // create thread for game
    public Player player = new Player(this, keyX);
    TileManager tileManager = new TileManager(this);
    CollisionChecker collisionChecker = new CollisionChecker(this);

    //set frame rate
    double framesPerSecond = 60;

    public Panel() throws IOException {    //layout for game panel
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

        double drawInterval = 1_000_000_000/framesPerSecond; // a billion nanoseconds is one second, this line sets "draw" rate
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
        try {
            tileManager.draw(graphics2D);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player.draw(graphics2D);
        graphics2D.dispose();
    }
}
